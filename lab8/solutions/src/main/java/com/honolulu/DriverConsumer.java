package com.honolulu;


import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetAddress;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class DriverConsumer {

    private static final Logger log = LoggerFactory.getLogger(DriverConsumer.class);
    private static final String TOPIC = "new-driver";
    private static final String RETRY_TOPIC = "new-driver-retry";

    public static void main(String[] args) throws InterruptedException {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "broker-1:9092,broker-2:9093");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "driver.consumer");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

        Thread haltedHook = new Thread(consumer::close);
        Runtime.getRuntime().addShutdownHook(haltedHook);

        consumer.subscribe(Collections.singletonList(TOPIC));

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> stringStringConsumerRecord : records.records(TOPIC)) {
                try {
                    log.info("Got message to add new driver, inserting...");
                    boolean transactionResult = processRecord(stringStringConsumerRecord);
                    if (!transactionResult) {
                        retryMessage(5);
                    }

                } catch (IOException e) {
                    System.out.println("IO exception happened, retrying...");
                    System.out.println(e.getMessage());
                    retryMessage(5);
                }
            }

        }
    }

    private static void retryMessage(int wait) throws InterruptedException {
        Thread.sleep(wait * 1000L);
        Producer retryProducer = new Producer();
        retryProducer.produceMessageToTopic(RETRY_TOPIC);
    }

    private static boolean processRecord(ConsumerRecord<String, String> record) throws IOException {
        boolean postgresReachable = IsRecheable("http://postgres:5432");
        boolean mongoReachable = IsRecheable("http://mongo:27017");
        return postgresReachable && mongoReachable;
    }

    public static boolean IsRecheable(String ip) throws IOException {
        InetAddress address = InetAddress.getByName(ip);
        return address.isReachable(10000);
    }

}
