package com.honolulu;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.honolulu.DisasterValue;
import com.honolulu.Intensity;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Producer {
	private static final Logger log = LoggerFactory.getLogger(Producer.class);
	private static final Properties props = new Properties();

	public static void main(String[] args) throws InterruptedException, JsonProcessingException {
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "http://localhost:9092,http://localhost:9093");
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

		KafkaProducer<String, String> producer = new KafkaProducer<>(props);
		String[] sensors = new String[]{"sensor_1", "sensor_2"};
		String[] disasters = new String[]{"flood", "hurricane"};
		String[] scales = new String[]{"meters", "km/s"};
		Runtime.getRuntime().addShutdownHook(new Thread(producer::close));
		while (true) {
			int idx = new Random().nextInt(sensors.length);
			String key = (sensors[idx]);
			int measurement = ThreadLocalRandom.current().nextInt(0, 10);

			Intensity intensity = new Intensity(scales[idx], measurement);

			DisasterValue value = DisasterValue.newBuilder()
					.setDisasterType(disasters[idx])
					.setIntensity(intensity);
			String disasterStr = toJsonString(value);
			System.out.println("Sending " + disasterStr);
			ProducerRecord<String, String> producerRecord =
						new ProducerRecord<>("DisasterReadings", key, disasterStr);

			producer.send(producerRecord);

			producer.flush();
			log.info("Successfully produced message from sensor " + key);
			Thread.sleep(200);
		}

	}
	private static String toJsonString(DisasterValue disaster) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(disaster);
	}
}
