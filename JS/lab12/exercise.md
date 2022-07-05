## Lab 11

0- In this folder run:

```
docker-compose up -d
```

1- Copy the directory structure we had in the previous labs and the `pom.xml` from this directory

Now run `mvn clean compile`

2- Copy the Producer from lab 4 and tweak it so that it sends messages continuously into a topic `RawTempReadings`.

3- Create a Streams application that ensures all readings are between -50 and 130 degrees and sends them into a new topic `ValidatedTempReadings`

4- Create a seperate console consumer into that topic `ValidatedTempReadings` to verify your work was succesful.

5 Think of a way to create a Stream Application that transforms the readings in F to C

6- Shut down everything:

```
docker-compose down
```

