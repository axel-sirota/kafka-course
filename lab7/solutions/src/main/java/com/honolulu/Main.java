package com.honolulu;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class Main {
	private static final Logger log = LoggerFactory.getLogger(Main.class);
	private static final String TOPIC = "connect-log";

	public static void main(String[] args) {

		Properties props = new Properties();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "http://broker-1:9092,http://broker-2:9093");
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

		KafkaProducer<String, String> producer = new KafkaProducer<>(props);
		ProducerRecord<String, String> producerRecordDebug = new ProducerRecord<>(TOPIC, "DEBUG", "I am sending a log message!");
		ProducerRecord<String, String> producerRecordInfo = new ProducerRecord<>(TOPIC, "INFO", "Lovely log!");
		producer.send(producerRecordDebug);
		producer.send(producerRecordInfo);

		producer.flush();
		producer.close();

		log.info("Successfully produced messages");

		}
}
