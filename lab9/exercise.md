## Lab 9

0- In this folder run:

```
docker-compose up -d
```

1- Copy the directory structure we had in the previous labs and the `pom.xml`

Now run `mvn clean compile`

2- Go to kafka folder and run kafka-topics
```
./bin/windows/kafka-topics.bat --create --zookeeper localhost:2181 \
--replication-factor 2 --partitions 3 --topic disasters
```

3- In the `src/main/avro` folder create a schema for `DisasterValue` that includes the following fields:

```
| Name of Field   | Type          |   |   |   |
|-----------------|---------------|---|---|---|
| disasterType    | string        |   |   |   |
| intensity       | Intensity     |   |   |   |
| recommendations | Array[String] |   |   |   |
```

Such that the Intensity Schema is:

```
| Name of Field | Type   |   |   |   |
|---------------|--------|---|---|---|
| scale         | string |   |   |   |
| measurement   | int    |   |   |   |
|               |        |   |   |   |
```

You can use the model on this same folder `DisasterValue.asvc`

4- Ensure that you generate a class from the avro by invoking `mvn generate-sources`. Verify that the classes are created by looking in the src/main/java/com/honolulu/model folder.

5- Create a DisasterProducer that sends DisasterValues into the disasters topic. Recall to add to the Properties object the SCHEMA_REGISTRY_URL_CONFIG property.

6- Create the neccesary Consumer. Include the following two properties:

```
props.put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, true);
props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
```

7- Execute both classes with mvn:exec as we have been doing and verify this sends actual objects serialized.

8- Shut down everything:

```
docker-compose down
```

