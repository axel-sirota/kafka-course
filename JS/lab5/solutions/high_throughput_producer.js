const { Kafka } = require('kafkajs')
const { performance } = require('perf_hooks');

const kafka = new Kafka({
  brokers: ['localhost:9092', 'localhost:9093'],
  clientId: 'lab4-producer',
})


const producer = kafka.producer({maxInFlightRequests : 5})
const topic = 'throughput' 
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

const run = async () => {
  await producer.connect()
  var startTime = performance.now()
  var count = 0
  while (performance.now() - startTime < 10000) {
    await sendMessage().catch(e => console.error(`[example/producer] ${e.message}`, e));
    count++
  }
  console.log(`I have sent ${count*1000} messages in 10 seconds`)
}
run()