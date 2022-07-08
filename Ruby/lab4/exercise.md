## Lab 4

0- In this folder run:

```
docker-compose up -d
```


1- Start from the previous lab copying the exercise folder to the exercise folder in this lab

```
cp -R ../lab3/exercise exercise
```

Note: In Windows do it with `copy` instead of `cp` and `\` instead of `/`.

Now run `mvn clean compile`

2- Go to kafka folder and run kafka-topics

*Windows*

```
./bin/windows/kafka-topics.bat --create --bootstrap-server localhost:9092 \
--replication-factor 2 --partitions 3 --topic hurricane

./bin/windows/kafka-topics.bat --create --bootstrap-server localhost:9092 \
--replication-factor 2 --partitions 3 --topic flood
```

*Unix*

```
./bin/kafka-topics.sh --create --bootstrap-server localhost:9092 \
--replication-factor 2 --partitions 3 --topic hurricane

./bin/kafka-topics.sh --create --bootstrap-server localhost:9092 \
--replication-factor 2 --partitions 3 --topic flood
```

3- Adapt the Producer to produce randomly to any of the two topics above with an intensity of the disaster between 1 and 10 and key the topic as well.

4- Create a consumer called Consumer that takes as argument the topic to consume to. Make each consumer in the same consumer group.

5- Start 2 consumers for hurricanes and 1 consumer for flood

These can be in 3 seperate tabs.

6- Run the producer: `node producer.js`

7- Verify each correct consumer got the correct message. Did all consumer got the same amount of messages? What happened? How can we balance this?

8- Kill the Consumer on the hurricane topic and start another one on the flood topic. Check the logs for the partition rebalance.

9- Shut down everything:

```
docker-compose down
```

