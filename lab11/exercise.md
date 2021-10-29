## Lab 11

0- In this folder run:

```
docker-compose up -d
```

1- Copy the directory structure we had in the previous labs and the `pom.xml` from this directory

Now run `mvn clean compile`

2- Copy the `Producer.java` class from this directory, that will send messages continuously into a topic `RawTempReadings`.

3- Create a Streams Application that continously writes in the console the amount of messages coming into the topic `RawTempReadings` every 2 seconds with a 1 seconds graceTime.

4- Run in seperate tabs the Producer, the Windowing Streams Application, and also run a consumer:

*Windows*

```
kafka-console-consumer.bat \
  --bootstrap-server broker-1:9092 \
  --topic RawTempReadings --from-beginning \
  --key-deserializer org.apache.kafka.common.serialization.StringDeserializer \
  --value-deserializer org.apache.kafka.common.serialization.IntegerDeserializer \
  --property print.key=true \
  --property key.separator=,
```

5- Create another Streams application that ensures all readings are between -50 and 130 degrees and sends them into a new topic `ValidatedTempReadings`

6- Create a seperate console consumer into that topic `ValidatedTempReadings` to verify your work was succesful.

7 Think of a way to create a Stream Application that transforms the readings in F to C

8- Shut down everything:

```
docker-compose down
```

