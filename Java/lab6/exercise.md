## Lab 6

0- In this folder run:

```
docker-compose up -d
```

1- Copy the directory structure we had in the previous labs and the `pom.xml`

Now run `mvn clean compile`

2- Create a simple main application to use the KafkaAdmin

3- Create with the CLI a topic `deliveries`

4- Delete it with the KafkaAdmin client

5- Create again the topic with the client this time and describe it.

6- Insert some events and then delete them with the KafkaAdmin client.

7- Shut down everything:

```
docker-compose down
```

