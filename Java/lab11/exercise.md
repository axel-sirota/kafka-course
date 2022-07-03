## Lab 8

0- In this folder run:

```
docker-compose up -d
```


1- Go to kafka folder and run kafka-topics

*Windows*

```
kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic kafka_connect_statuses --config cleanup.policy=compact

kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic kafka_connect_offsets --config cleanup.policy=compact

kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic kafka_connect_configs --config cleanup.policy=compact
```

*Unix*

```
kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic kafka_connect_statuses --config cleanup.policy=compact

kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic kafka_connect_offsets --config cleanup.policy=compact

kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic kafka_connect_configs --config cleanup.policy=compact
```

3- Create a Connect distributed instance:

*Windows*

```
bin/windows/connect-distributed.bat worker.properties
```

*Unix*

```
bin/connect-distributed.sh worker.properties
```

4- In a new tab change on the file `worker.properties` the `rest.port` and bring up a new Connect distributed instances

5- Deploy using the REST API a new File Connector with 4 tasks:

```
curl -i -X PUT -H "Accept:application/json" -H "Content-Type:application/json"  "http://localhost:8083/connectors/file-sink/config" -d '{
        "connector.class":"FileStreamSink",
        "tasks.max": "4",
        "file":"./file-log.txt",
        "topics":"connect-log"
}'
```

6- Check in the /status endpoint of the connector that the 4 tasks are running as seperate threads balanced across the two Distributed instances

7- Kill one instance with CTRL+C and verify later with `/status` that the tasks moved to the remaining instance

8- Bring up the instance again and verify the rebalancing

9- (optional) Create a Producer to verify that this effectively works as a connector.

10- Shut down everything:

```
docker-compose down
```

Homework: Test with the ElasticSearch connector and the following config:

```
  "connector.class": "io.confluent.connect.elasticsearch.ElasticsearchSinkConnector",
  "tasks.max": "4", 
  "topics": "honolulu-topic", 
  "key.ignore": "true", "schema.ignore": "true", 
  "connection.url": "http://elasticsearch:9200", 
  "type.name": "kafka-connect", 
  "name": "elasticsearch-connector"
```
  