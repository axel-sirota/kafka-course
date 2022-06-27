const { Kafka } = require('kafkajs')

const kafka = new Kafka({
  brokers: ['localhost:9092', 'localhost:9093'],
  clientId: 'lab6-admin',
})
const admin = kafka.admin()
const producer = kafka.producer({maxInFlightRequests : 5})
const topic = 'deliveries';

const run = async () => {
  await admin.connect()
  await producer.connect()
  console.log('Starting up')
  await admin.createTopics({
    topics: [{
      topic: topic,
      numPartitions: 2,
      replicationFactor: 1
    }],
  }).then(console.log).catch(e => console.error(`[example/admin] ${e.message}`, e));

  await admin.fetchTopicMetadata({ topics: [topic] }).then(console.log).catch(e => console.error(`[example/admin] ${e.message}`, e));

  const sendMessage = () => {
    var messages = [];
    for (let i = 0; i < 1000; i++) {
      messages.push({key: '1', value: '1'})
    }
    return producer.send({
        topic,
        messages: messages,
        acks: 0
    })
  }

  await sendMessage()

  await admin.fetchTopicOffsets(topic).then(console.log)
  await admin.fetchTopicMetadata({ topics: [topic] }).then(console.log)

  await admin.deleteTopics({
    topics: [topic],
    timeout: 60,
  }).then(console.log).catch(e => console.error(`[example/admin] ${e.message}`, e));
}
run()