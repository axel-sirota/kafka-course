package com.honolulu;


import com.honolulu.model.DisasterValue;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;
import io.confluent.kafka.serializers.KafkaAvroSerializerConfig;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class DisasterConsumer {

    private static final Logger log = LoggerFactory.getLogger(DisasterConsumer.class);
    private static final String TOPIC = "disasters";

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092,localhost:9093");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class);
        props.put(KafkaAvroSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://localhost:8081");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "disaster.consumer");
        props.put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, true);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        KafkaConsumer<String, DisasterValue> consumer = new KafkaConsumer<>(props);

        Thread haltedHook = new Thread(consumer::close);
        Runtime.getRuntime().addShutdownHook(haltedHook);

        consumer.subscribe(Collections.singletonList(TOPIC));

        while (true) {
            ConsumerRecords<String, DisasterValue> records = consumer.poll(Duration.ofMillis(100));
            records.records(TOPIC)
                .forEach(record -> System.out.println(record.value()));

        }
    }
}
