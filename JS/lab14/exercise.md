## Lab 11

0- In this folder run:

```
docker-compose up -d
```

1- Copy the directory structure we had in the previous labs and the `pom.xml` from this directory

Now run `mvn clean compile`

2- Use the same Producer as lab 12 and 13.

3- Create a Streams Application that continously writes in the console the amount of messages coming into the topic `RawTempReadings` every 2 seconds with a 1 seconds graceTime.

4- Run in seperate tabs the Producer, the Windowing Streams Application, and also run a consumer:

*Windows*

```
kafka-console-consumer.bat \
  --bootstrap-server localhost:9092 \
  --topic RawTempReadings --from-beginning \
  --key-deserializer org.apache.kafka.common.serialization.StringDeserializer \
  --value-deserializer org.apache.kafka.common.serialization.IntegerDeserializer \
  --property print.key=true \
  --property key.separator=,
```

*Unix*

```
kafka-console-consumer.sh \
  --bootstrap-server localhost:9092 \
  --topic RawTempReadings --from-beginning \
  --key-deserializer org.apache.kafka.common.serialization.StringDeserializer \
  --value-deserializer org.apache.kafka.common.serialization.IntegerDeserializer \
  --property print.key=true \
  --property key.separator=,
```

5- Shut down everything:

```
docker-compose down
```

