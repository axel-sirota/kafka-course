const { KafkaAvro } = require('kafkajs-avro')

const kafka = new KafkaAvro({
  brokers: ['broker-1:9092', 'broker-2:9093'],
  clientId: 'lab9-producer',
  avro: {
    url: "http://localhost:8081"
  }
})

const producer = kafka.avro.producer()
const topic = 'disasters'
const typesOfDisasters = ['flood', 'hurricane']

const getIntensity = () => Math.floor(Math.random()* 10)
const recommendationsDict = {
  flood: ["turn off lights", "evacuate"],  
  hurricane: ["run"]
}
const scalesDict = {
  flood: "meters",
  hurricane: "richter"
}
const createMessage = (disaster, intensity, scale, recommendations) => ({
  subject: "disasters-value",
  version: "1",
  value: {
    disasterType: `${disaster}`,
    intensity: {
      scale: scale,
      measurement: intensity
    },
    recommendations: recommendations
  }
})

const sendMessage = () => {
  intensity = getIntensity()
  disaster = typesOfDisasters[Math.floor(Math.random()*typesOfDisasters.length)]
  recommendationToGive = recommendationsDict[disaster]
  scale = scalesDict[disaster]
  return producer
    .send({
      topic,
      messages: Array(1)
        .fill()
        .map(_ => createMessage(disaster, intensity, scale, recommendationToGive)),
    })
    .then(console.log)
    .catch(e => console.error(`[example/producer] ${e.message}`, e))
}

const run = async () => {
  await producer.connect()
  setInterval(sendMessage, 3000)
}

run().catch(e => console.error(`[example/producer] ${e.message}`, e))
