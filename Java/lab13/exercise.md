## Lab 11

0- In this folder run:

```
docker-compose up -d
```

1- Copy the directory structure we had in the previous labs and the `pom.xml` from this directory

Now run `mvn clean compile`

2- Modify the last lab to produce Disasters from the AVRO lab (But instead of using AVRO, which is difficult to merge with Streams, use KafkaJSONSerializer)

3- Create a Streams app that filters the disasters such that the intensity is in the lower 75%.

4- Deploy a Kafka Connect standalone instance to read from the big disasters topic and records them in a database (you have at your dispose both Mongo as well as Postgres). If you prefer to write the writer with another Streams app is fine too

5- Notice now we can query about big disasters in the DB

6- Shut down everything:

```
docker-compose down
```

