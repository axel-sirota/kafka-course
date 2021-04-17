## Lab 2

0- In this folder run:

```
docker-compose up -d
```


1- Go to kafka folder and run kafka-topics
```
./bin/windows/kafka-topics.bat --create --zookeeper localhost:2181 \
--replication-factor 1 --partitions 3 --topic my_orders
```

2- Verify from the REST API that the topic was created.

3- Check in the REST API how the partitions are accomodated

4- Increase the replication by using the file `increase_replication.json`

```
./bin/windows/kafka-reassign-partitions.bat --zookeeper localhost:2181 \
--reassignment-json-file increase_replication.json --execute
```

5- Check again how the partitions are accomodated

6- Stop second broker and verify partitions reaccomodations. What happened with partition 2?

7- Restart second broker and verify how it is back online

8- Shut down everything:

```
docker-compose down
```

