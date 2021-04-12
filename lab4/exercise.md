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
```
./bin/kafka-topics --create --zookeeper localhost:2181 \
--replication-factor 2 --partitions 3 --topic hurricane

./bin/kafka-topics --create --zookeeper localhost:2181 \
--replication-factor 2 --partitions 3 --topic flood
```

3- Adapt the Producer to produce randomly to any of the two topics above with an intensity of the disaster between 1 and 10

4- Create a consumer called Consumer inside of a package com.honolulu that takes as argument the topic to consume to. Make each consumer in the same consumer group.

5- Start 2 consumer for hurricanes with the command:

```
mvn clean compile exec:java -Dexec.mainClass="com.honolulu.Consumer" -Dexec.cleanupDaemonThreads=false -Dexec.args="hurricane"
```

And 1 consumer for flood with the command

```
mvn clean compile exec:java -Dexec.mainClass="com.honolulu.Consumer" -Dexec.cleanupDaemonThreads=false -Dexec.args="flood"
```

These can be in 3 seperate tabs.

6- Instantiate the Producer: `mvn exec:java -Dexec.mainClass="com.honolulu.Producer" -Dexec.cleanupDaemonThreads=false`

7- Verify each correct consumer got the correct message.

8- Use the REST API to verify the consumer group

8- Shut down everything:

```
docker-compose down
```

