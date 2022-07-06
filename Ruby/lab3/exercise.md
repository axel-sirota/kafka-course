## Lab 3

0- In this folder run:

```
docker-compose up -d
```

1- Go to kafka folder and run kafka-topics

*Windows*

```
./bin/windows/kafka-topics.bat --create --bootstrap-server localhost:9092 \
--replication-factor 2 --partitions 3 --topic my_orders
```

*Unix*

```
./bin/kafka-topics.sh --create --bootstrap-server localhost:9092 \
--replication-factor 2 --partitions 3 --topic my_orders
```

3- Create a producer inside an `index.js` main file in the `exercise` folder (notice the package.json has already been set)

4-  Create a array of states in the United States, using the following String

```
String stateString =
       "AK,AL,AZ,AR,CA,CO,CT,DE,FL,GA," +
       "HI,ID,IL,IN,IA,KS,KY,LA,ME,MD," +
       "MA,MI,MN,MS,MO,MT,NE,NV,NH,NJ," +
       "NM,NY,NC,ND,OH,OK,OR,PA,RI,SC," +
       "SD,TN,TX,UT,VT,VA,WA,WV,WI,WY";
```

6- In the main method, create a loop of your choosing and send a message with a random state as the key, and a random value from 10 to 100000 for the cost of the total order. Using a Callback print the metadata, you can use a closure to enclose the key and value and print the content.

7- Have a sleep with a random amount of time from 5 to 30 seconds to represent some time between messages to mimic the real world

8- Run in your terminal by executing `node index.js`

9- Verify with the console consumer you got all messages (For simplicity notice we are assuming the value you send is also set as string, if you are not then change the class of the deserializer accordingly).

*Windows*

```
./bin/windows/kafka-console-consumer.bat \
  --bootstrap-server localhost:9092 \
  --topic my_orders --from-beginning \
  --key-deserializer org.apache.kafka.common.serialization.StringDeserializer \
  --value-deserializer org.apache.kafka.common.serialization.StringDeserializer \
  --property print.key=true \
  --property key.separator=,
```

*Unix*

```
./bin/kafka-console-consumer.sh \
  --bootstrap-server localhost:9092 \
  --topic my_orders --from-beginning \
  --key-deserializer org.apache.kafka.common.serialization.StringDeserializer \
  --value-deserializer org.apache.kafka.common.serialization.StringDeserializer \
  --property print.key=true \
  --property key.separator=,
```

10- Shut down everything:

```
docker-compose down
```

