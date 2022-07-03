## Lab 7

0- In this folder run:

```
docker-compose up -d
```

1- Modify the docker-compose file to have the following deployment:

- 5 brokers, and configure the racks such that they group 3/1/1

Ensure that the brokers have a reliable configuration at the broker level.

2 - Create 2 topics each with 1 partition with all the considerations that are topic dependant to reliability:

- One to have the most reliable configuration
- Another one to represent a less reliable configuration

This is a traditional setup where in general you may default to being reliable, but for some topics you can choose to prioritize performance over durability

3- For each topic create a Producer and Consumer that ensures the reliability of the whole system

4- Test the system. 

Although it is difficult to force the situation of putting down a broker **just** before the followers read the message, we will try to simulate some scenarios.

5- Set the producer to send messages constantly (logging set to DEBUG) and disconnect the leader for the non reliable topic by running `docker stop` on its container. Check the logs, what happened? What happened while the election was happening?

6- While the producer keeps running, stop another broker; wait for some time with sending messages, and bring the original leader up with `docker start`. What happened with the messages that had a divergence of offsets?

7- Stop the producer, configure it to produce to the reliable topic and stop two brokers. What is happening? Wait for 10 seconds and bring up one broker. Did you get to an unclean election?

8- Now bring up all brokers, set the producer running and simulate in your Consumer code that you manually commit but at some point you got a batch of messages for stop processing without committing (to force this you can add a sleep in the middle of processing and then kill the consumer from the command line, inevitably it would have been waiting). Start another consumer, forcing the rebalance, did you lose messages? How can you avoid this?

9- Shut down everything:

```
docker-compose down
```

