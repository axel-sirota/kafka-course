## Lab 6

0- In this folder run:

```
docker-compose up -d
```


1- Copy the directory structure we had in the previous labs and the `pom.xml`

Now run `mvn clean compile`

2- Go to kafka folder and run kafka-topics
```
./bin/kafka-topics --create --zookeeper localhost:2181 \
--replication-factor 2 --partitions 3 --topic connect-log
```

3- Create a Producer sending Log data into the topic `connect-log`

4- Create a Connect standalone instance:

```
bin/connect-standalone.sh worker.properties filesink.properties
```

5- Execute the producer sending some log data. 

6- Verify the logs are effectively in the file specified

7- Shut down everything:

```
docker-compose down
```


  