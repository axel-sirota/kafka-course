package com.honolulu;

import io.confluent.kafka.serializers.KafkaJsonDeserializer;
import io.confluent.kafka.serializers.KafkaJsonDeserializerConfig;
import io.confluent.kafka.serializers.KafkaJsonSerializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;

import java.util.Map;
import java.util.Properties;

public class SimpleETL {

	public static void main(String[] args) {
		Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "weather.filter");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, 
        		Serdes.String().getClass());
		props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
		props.put("schema.registry.url", "http://localhost:8081");
		
		StreamsBuilder builder = new StreamsBuilder();
		KStream<String, DisasterValue> rawReadings = builder.stream("DisasterReadings", Consumed.with(
				Serdes.String(),
				createValuesSerde(DisasterValue.class)));
		KStream<String, DisasterValue> validatedReadings = rawReadings
				.filter((key, disaster) -> disaster.getIntensity().getMeasurement() > 7.5)
		.peek((k, disaster) -> {
			System.out.println("Typed job: " + k + " -> " + disaster);
		});
		validatedReadings.to("ValidatedDisasterReadings", Produced.with(
				Serdes.String(),
				createValuesSerde(DisasterValue.class)));
		
		Topology topo = builder.build();
		System.out.println(topo.describe());
		
		KafkaStreams streams = new KafkaStreams(topo, props);
		streams.cleanUp();
		streams.start();
		
		Runtime.getRuntime().addShutdownHook(new Thread(streams::close));
		
		System.out.println("Starting simple ETL");

	}
	private static <T> Serde<T> createValuesSerde(Class<T> valueType) {
		KafkaJsonSerializer<T> serializer = new KafkaJsonSerializer<T>();
		serializer.configure(Map.of(), false);

		KafkaJsonDeserializer<T> deserializer = new KafkaJsonDeserializer<T>();
		deserializer.configure(Map.of(
				KafkaJsonDeserializerConfig.JSON_VALUE_TYPE, valueType.getName()
		), false);

		return Serdes.serdeFrom(serializer, deserializer);
	}

}
