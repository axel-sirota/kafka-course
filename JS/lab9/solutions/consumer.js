const { KafkaAvro } = require('kafkajs-avro')

const kafka = new KafkaAvro({
  brokers: ['localhost:9092', 'localhost:9093'],
  clientId: 'lab9',
  avro: {
    url: "http://localhost:8081"
  }
})

const topic = "disasters"
const consumer = kafka.avro.consumer({ groupId: `lab9-consumer` })

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
