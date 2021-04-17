## Lab 1

0- In this folder run:

```
docker-compose up -d
```

1- From the browser access the following URLs to verify the cluster is up using the REST api:

```
http://localhost:8082/brokers
http://localhost:8082/topics
```

Note: You can use POSTman or any other REST request facility tooling with the OpenAPI specification file in this same repo `rest_proxy.yaml`.

2- Go to kafka folder and run kafka-console-producer
```
    ./bin/windows/kafka-console-producer.bat \
    --broker-list localhost:9092 \
    --topic first_topic
```
    Note
    	This will create a topic of 1 replica and 1 partition

2'- Verify from the REST API that the topic was created.

3- You will be provided a prompt. Using these prompts, enter a word and hit ENTER, add some more words and hit ENTER

4- In another terminal window or tab, run kafka-console-consumer

```
    ./bin/windows/kafka-console-consumer.bat \
    --bootstrap-server localhost:9092 \
    --topic first_topic --from-beginning
```

5- Ensure that the messages that you entered in the first terminal show up in the second

6-Enter some more words in the producer, and view the messages in the terminal with the consumer

7- Go to the documentation for kafka-console-producer.bat and kafka-console-consumer.bat  and research how you can produce a key and value on the command line, and read that key and value on the consumer. You may need to shut down either kafka-console-producer, kafka-console-consumer.bat  or both with CTRL+C and try something different.

8- Shut down kafka-console-producer.bat and kafka-console-consumer.bat  using CTRL+C

