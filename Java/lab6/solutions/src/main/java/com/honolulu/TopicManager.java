package com.honolulu;

import org.apache.kafka.clients.admin.*;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class TopicManager {

    private static final String DELIVERIES_TOPIC = "deliveries";
    private static final Logger log = LoggerFactory.getLogger(TopicManager.class);

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Properties props = new Properties();
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092,localhost:9093");

        AdminClient adminClient = KafkaAdminClient.create(props);

        // Delete Topic
        // DeleteTopicsResult deleteTopicsResult = adminClient.deleteTopics(List.of(DELIVERIES_TOPIC));
        // deleteTopicsResult.all().get();
        // log.info("Successfully deleted the " + DELIVERIES_TOPIC + " topic!");

        // Create Topic
        int partitions = 2;
        short replicationFactor = 2;
        NewTopic deliveriesTopic = new NewTopic(DELIVERIES_TOPIC, partitions, replicationFactor);
        CreateTopicsResult topics = adminClient.createTopics(List.of(deliveriesTopic));
        topics.all().get();
        log.info("Successfully created the " + DELIVERIES_TOPIC + " topic!");

        // Describe Topic
        DescribeTopicsResult describeTopicsResult = adminClient.describeTopics(List.of(DELIVERIES_TOPIC));
        Map<String, TopicDescription> stringTopicDescriptionMap = describeTopicsResult.all().get();
        log.info("Topic description: " + stringTopicDescriptionMap);

        // Delete Records
        // TopicPartition topicPartition = new TopicPartition(DELIVERIES_TOPIC, 0);
        // RecordsToDelete recordsToDelete = RecordsToDelete.beforeOffset(123);
        // DeleteRecordsResult deleteRecordsResult = adminClient.deleteRecords(Map.of(topicPartition, recordsToDelete));
        // deleteRecordsResult.all().get();



    }

}
