# murmur2 Partitioner

This JS-script calculates the partition based on a key and the amount of partitions for a JS Kafka producer in way consistent to the default Java Kafka client.

## Install it
```
npm install
```

## Test it
```
npm test
```

## Use it
```
const partitioner = require('path/to/index')
partitioner.partition('key', 5) // Second parameter being the total number of partitions
```
