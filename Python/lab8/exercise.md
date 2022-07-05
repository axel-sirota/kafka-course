## Lab 10

0- In this folder run:

```
docker-compose up -d
```

1- Copy the directory structure we had in the previous labs and the `pom.xml`

Now run `mvn clean compile`

2- Go to kafka folder and run kafka-topics

*Windows*

```
./bin/windows/kafka-topics.bat --create --bootstrap-server localhost:9092 \
--replication-factor 2 --partitions 3 --topic new-driver

./bin/windows/kafka-topics.bat --create --bootstrap-server localhost:9092 \
--replication-factor 2 --partitions 3 --topic new-driver-retry

./bin/windows/kafka-topics.bat --create --bootstrap-server localhost:9092 \
--replication-factor 1 --partitions 1 --topic new-driver-dlq
```

*Unix*

```
./bin/kafka-topics.sh --create --bootstrap-server localhost:9092 \
--replication-factor 2 --partitions 3 --topic new-driver

./bin/kafka-topics.sh --create --bootstrap-server localhost:9092 \
--replication-factor 2 --partitions 3 --topic new-driver-retry

./bin/kafka-topics.sh --create --bootstrap-server localhost:9092 \
--replication-factor 1 --partitions 1 --topic new-driver-dlq
```

3- Create a Producer that sends a notification to insert a new driver. This notification should be read by a Consumer. We will simulate such transaction by checking if the postgres and mongo ports are recheable.

Note: You can check that with:

```
def is_port_in_use(port: int) -> bool:
    import socket
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
        return s.connect_ex(('localhost', port)) == 0

```

4- In case of failure, send to the retry topic after 5 seconds. This is key to simulate a transient error

5- Repeat on a new Consumer and in failure send to the DLQ topic.

6- Simulate different conditions to verify it's working.

7 Think for the group ways to incorporate this pattern into your own jobs.

8- Shut down everything:

```
docker-compose down
```

