const { Kafka } = require('kafkajs')
var net = require('net');

const kafka = new Kafka({
  brokers: ['localhost:9092', 'localhost:9093'],
  clientId: 'lab8-producer',
})

const topic = 'new-driver' 
const producer = kafka.producer()


const sendMessage = () => {
  var messages = [{key: 'new_driver', value: 'new-driver'}];
  return producer.send({
      topic,
      messages: messages,
      acks: 0
  })
}

const run = async () => {
  await producer.connect()
  setInterval(sendMessage, 100)
}
run()