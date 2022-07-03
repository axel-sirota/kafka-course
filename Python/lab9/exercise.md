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

4- Create a producer that uses confluent-kafka (notice the requirements.txt) AvroProducer to produce messages with AVRO. You will need to use confluent_kafka.avro utilities to load the key and value avro specifications to pass to the producer so it negotiates the schemas.

5- Create a normal consumer (not a AvroConsumer) that gets messages from the disasters topic and decodes them. Use the following decoder (which is difficult to get to):

```
schema = avro.schema.parse(open("DisasterValue.avsc").read())
reader = DatumReader(schema)

def decode(msg_value):
    message_bytes = io.BytesIO(msg_value)
    message_bytes.seek(5)
    decoder = BinaryDecoder(message_bytes)
    event_dict = reader.read(decoder)
    return event_dict
```


7- Execute both producer and consumer and test it works. Notice that under the hood the negotiation of the versions happened.

8- Shut down everything:

```
docker-compose down
```

