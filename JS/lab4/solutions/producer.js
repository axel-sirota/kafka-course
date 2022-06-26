const { Kafka } = require('kafkajs')

const kafka = new Kafka({
  brokers: ['localhost:9092', 'localhost:9093'],
  clientId: 'lab4-producer',
})

const MyPartitioner = () => {
  return ({ topic, partitionMetadata, message }) => {
      // select a partition based on some logic
      // return the partition number
      return Math.floor(Math.random()* 3)
  }
}

const producer = kafka.producer({ createPartitioner: MyPartitioner })
const typesOfDisasters = ['flood', 'hurricane']

const getIntensity = () => Math.floor(Math.random()* 10)
const createMessage = (disaster, num) => ({
  key: `${disaster}`,
  value: `${num}`,
})
const sendMessage = () => {
  topic = typesOfDisasters[Math.floor(Math.random()*typesOfDisasters.length)];
  console.log(`Sending to topic ${topic}`);
  return producer
    .send({
      topic,
      messages: [createMessage(topic, getIntensity())],
    })
    .then(console.log)
    .catch(e => console.error(`[example/producer] ${e.message}`, e));
}

const run = async () => {
  await producer.connect()
  setInterval(sendMessage, 100)
}

run().catch(e => console.error(`[example/producer] ${e.message}`, e))
