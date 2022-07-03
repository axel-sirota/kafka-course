## Lab 9

0- In this folder run:

```
docker-compose up -d
```

1- Copy the directory structure we had in the previous labs 

2- Go to kafka folder and run kafka-topics

*Windows*

```
./bin/windows/kafka-topics.bat --create --bootstrap-server localhost:9092 \
--replication-factor 2 --partitions 3 --topic disasters
```

*Unix*

```
./bin/kafka-topics.sh --create --bootstrap-server localhost:9092 \
--replication-factor 2 --partitions 3 --topic disasters
```

3- Create a schema for `DisasterValue` that includes the following fields:

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

4- Create a register_schema script that actually registers the schema into the schema registry. I recommend using @kafkajs/confluent-schema-registry. The subject will end up being `namespace.name`.
Validate by hitting its REST endpoint

5- Create a DisasterProducer that sends DisasterValues into the disasters topic. You can use KafkaAvro client that specifies the URL for the Schema Registry

6- Create the neccesary Consumer and set the auto offset reset to earliest

7- Execute both producer and consumer and test it works. Notice that under the hood the negotiation of the versions happened.

8- Shut down everything:

```
docker-compose down
```

