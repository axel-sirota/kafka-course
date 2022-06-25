package com.honolulu;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.DoubleSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.Random;

public class Producer {
	private static final Logger log = LoggerFactory.getLogger(Producer.class);

	public static void main(String[] args) {

		Properties props = new Properties();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "http://broker-1:9092,http://broker-2:9093");
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, DoubleSerializer.class.getName());
		KafkaProducer<String, Double> producer = new KafkaProducer<>(props);
		while (true) {
			double value = Math.floor(Math.random()*10+1);
			String[] disasters = {"flood", "hurricane"};
			int idx = new Random().nextInt(disasters.length);
			String key = (disasters[idx]);
			ProducerRecord<String, Double> producerRecord = new ProducerRecord<>(key, key, value);

			log.info("Sending message with key " + key + " to Kafka");

			producer.send(producerRecord, (metadata, e) -> {
				if (metadata != null) {
					System.out.println(producerRecord.key());
					System.out.println(producerRecord.value());
					System.out.println(producerRecord.partition());
					// System.out.println(producerRecord.offset());
				}
			});
			producer.flush();
			log.info("Successfully produced messages to " + key + " topic");
		}
		// producer.close();


	}
}
