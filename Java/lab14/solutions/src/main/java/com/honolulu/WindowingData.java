package com.honolulu;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Printed;
import org.apache.kafka.streams.kstream.TimeWindows;
import org.apache.kafka.streams.kstream.Windowed;

import java.time.Duration;
import java.util.Properties;

public class WindowingData {

	
	public static void main(String[] args) {
		Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "Weather_windowing");
        //Port 9092 is used because the docker compose file exposes that to our computer
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.Integer().getClass());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		
        Duration windowSize = Duration.ofSeconds(2);
        Duration graceSize = Duration.ofSeconds(1);
        
		StreamsBuilder builder = new StreamsBuilder();
		KStream<String, Integer> rawReadings = builder.stream("RawTempReadings");
		
		KTable<Windowed<String>, Long> countedReadings = rawReadings.groupByKey()
				.windowedBy(
						TimeWindows.of(windowSize).grace(graceSize))
				.count();
		
		countedReadings.toStream()
				.map((Windowed<String> key, Long count) -> new KeyValue<>
							(key.key(), key.window().startTime() +","+count))
				.print(Printed.toSysOut());
		
		Topology topo = builder.build();
		
		KafkaStreams streams = new KafkaStreams(topo, props);
		streams.cleanUp();
		streams.start();
		
		Runtime.getRuntime().addShutdownHook(new Thread(streams::close));
	}

}
