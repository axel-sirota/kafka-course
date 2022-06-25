const { Kafka } = require('kafkajs')

const kafka = new Kafka({
  brokers: ['broker-1:9092', 'broker-2:9093'],
  clientId: 'lab4-consumer',
})

var argv = require('minimist')(process.argv.slice(2));
const topic = argv["topic"]
if (topic === undefined) {
  console.log("Topic must be set as parameter --topic")
  process.exit(1);
}

const consumer = kafka.consumer({ groupId: `lab4-consumer-${topic}` })

const run = async () => {
  await consumer.connect()
  await consumer.subscribe({ topic, fromBeginning: true })
  await consumer.run({
    eachMessage: async ({ topic, partition, message }) => {
      const prefix = `${topic}[${partition} | ${message.offset}] / ${message.timestamp}`
      console.log(`- ${prefix} - ${message.value}`)
    },
  })
}

run().catch(e => console.error(`[example/consumer] ${e.message}`, e))
