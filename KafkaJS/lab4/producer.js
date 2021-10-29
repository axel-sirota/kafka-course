const { Kafka } = require('kafkajs')

const kafka = new Kafka({
  brokers: ['broker-1:9092', 'broker-2:9093'],
  clientId: 'lab4-producer',
})

const producer = kafka.producer()
const typesOfDisasters = ['flood', 'hurricane']

const getIntensity = () => Math.floor(Math.random()* 10)
const createMessage = num => ({
  value: `${num}`,
})

const sendMessage = () => {
  topic = typesOfDisasters[Math.floor(Math.random()*typesOfDisasters.length)];
  return producer
    .send({
      topic,
      messages: Array(1)
        .fill()
        .map(_ => createMessage(getIntensity())),
    })
    .then(console.log)
    .catch(e => console.error(`[example/producer] ${e.message}`, e))
}

const run = async () => {
  await producer.connect()
  setInterval(sendMessage, 3000)
}

run().catch(e => console.error(`[example/producer] ${e.message}`, e))
