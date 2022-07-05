package com.honolulu;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class Producer {
	private static final Logger log = LoggerFactory.getLogger(Producer.class);
	private final Properties props = new Properties();
	private KafkaProducer<String, String> producer;

	public Producer() {
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "http://localhost:9092,http://localhost:9093");
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		producer = new KafkaProducer<>(props);
	}

	public void produceMessageToTopic(String topic) {

//		At this point you could send in AVRO the driver information to decouple
		ProducerRecord<String, String> producerRecord =
					new ProducerRecord<>(topic, "driver", "new_driver");

		producer.send(producerRecord);

		producer.flush();
		producer.close();

		log.info("Successfully produced messages");

		}

	public void killMessage(String topic) {

		ProducerRecord<String, String> producerRecord =
				new ProducerRecord<>(topic, null, "new_driver");

		producer.send(producerRecord);

		producer.flush();
		producer.close();

		log.info("Killed message");

	}
}
