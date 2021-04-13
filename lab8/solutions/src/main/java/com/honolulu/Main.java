package com.honolulu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
	private static final Logger log = LoggerFactory.getLogger(Main.class);
	private static final String TOPIC = "new-driver";

	public static void main(String[] args) {

		Producer producer = new Producer();
		producer.produceMessageToTopic(TOPIC);
		}
}
