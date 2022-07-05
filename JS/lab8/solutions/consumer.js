const { Kafka } = require('kafkajs')
var net = require('net');

const kafka = new Kafka({
  brokers: ['localhost:9092', 'localhost:9093']
})

const topic = 'new-driver' 
const topicRetry = 'retry' 
const producer = kafka.producer()
const consumer = kafka.consumer({ groupId: `lab8-consumer-${topic}` })
const connectHost = (item) => {
    sock = new net.Socket();
    sock.setTimeout(2500);
    sock.on('connect', function() {
        console.log(item[0]+':'+item[1]+' is up.');
        sock.destroy();
        return true;
    }).on('error', function(e) {
        console.log(item[0]+':'+item[1]+' is down: ' + e.message);
        return false;
    }).on('timeout', function(e) {
        console.log(item[0]+':'+item[1]+' is down: timeout');
        return false;
    });
}
const run = async () => {
  await consumer.connect()
  await consumer.subscribe({ topic, fromBeginning: true })
  await consumer.run({
    eachMessage: async ({ topic, partition, message }) => {
      const prefix = `${topic}[${partition} | ${message.offset}] / ${message.timestamp}`
      console.log(`- ${prefix} - ${message.value}`)
      var connectPostgres = connectHost(["localhost", 5432])
      var connectMongo = connectHost(["localhost", 27017])
      if (connectPostgres && connectMongo) {
        console.log("Processed correctly message")
      } else {
          console.log("Retrying...")
          await producer.connect()
          await producer.send({
            topicRetry,
            messages: [{key: '1', value: '1'}],
            acks: 0
        })
      }
  },
})
}
run()