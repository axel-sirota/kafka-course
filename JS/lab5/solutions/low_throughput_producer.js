const { Kafka } = require('kafkajs')
const { performance } = require('perf_hooks');

const kafka = new Kafka({
  brokers: ['localhost:9092', 'localhost:9093'],
  clientId: 'lab4-producer',
})


const producer = kafka.producer({maxInFlightRequests : 1})
const topic = 'throughput' 
const sendMessage = () => {
  return producer.send({
      topic,
      messages: [{key: '1', value: '1'}],
      acks: all
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
  console.log(`I have sent ${count} messages in 10 seconds`)
}
run()