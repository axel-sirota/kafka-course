package com.honolulu;

import com.honolulu.model.DisasterValue;
import com.honolulu.model.Intensity;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import io.confluent.kafka.serializers.KafkaAvroSerializerConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

public class DisasterProducer {
	private static final Logger log = LoggerFactory.getLogger(DisasterProducer.class);
	private static final String TOPIC = "disasters";

	public static void main(String[] args) {

		Properties props = new Properties();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "http://broker-1:9092,http://broker-2:9093");
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
		props.put(KafkaAvroSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://localhost:8081");

		Intensity intensity = new Intensity("meters", 5);

		DisasterValue value = DisasterValue.newBuilder()
				.setDisasterType("flood")
				.setIntensity(intensity)
				.setRecommendations(Arrays.asList(new String[]{"turn off lights", "evacuate"}))
				.build();

		KafkaProducer<String, DisasterValue> producer = new KafkaProducer<>(props);

		ProducerRecord<String, DisasterValue> producerRecord =
					new ProducerRecord<>(TOPIC, "producer", value);
		producer.send(producerRecord);

		producer.flush();
		producer.close();

		log.info("Successfully produced messages");

		}
}
