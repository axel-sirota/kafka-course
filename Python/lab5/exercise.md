## Lab 5

0- In this folder run:

```
docker-compose up -d
```


1- SCopy the directory structure we had in the previous labs and the `pom.xml`

Now run `mvn clean compile`

2- Go to kafka folder and run kafka-topics

*Windows*

```
./bin/windows/kafka-topics.bat --create --bootstrap-server localhost:9092 \
--replication-factor 2 --partitions 3 --topic latency

./bin/windows/kafka-topics.bat --create --bootstrap-server localhost:9092 \
--replication-factor 2 --partitions 3 --topic throughput
```

*Unix*

```
./bin/kafka-topics.sh --create --bootstrap-server localhost:9092 \
--replication-factor 2 --partitions 3 --topic latency

./bin/kafka-topics.sh --create --bootstrap-server localhost:9092 \
--replication-factor 2 --partitions 3 --topic throughput
```

3- Create a HighThroughputProducer altering the properties to get the best throughput possible. Change the following properties to get this result:

* ACKS_CONFIG
* LINGER_MS_CONFIG
* BATCH_SIZE_CONFIG
* MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION

4- Discuss how these changes affect the throuput and create a LowThroughputProducer accordingly. You should get a difference of 5x.

5- Do accordingly with a LowLatency and HighLatency Producers. You may use the LatencyCalculator.java consumer as a helper to calculate the Latency. 

6- Shut down everything:

```
docker-compose down
```

