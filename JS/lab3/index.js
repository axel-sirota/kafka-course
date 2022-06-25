const { Kafka } = require('kafkajs')

const kafka = new Kafka({
  logLevel: 'INFO',
  brokers: ['broker-1:9092', 'broker-2:9093'],
  clientId: 'lab3-producer',
})

const topic = 'my_orders'
const producer = kafka.producer()
const stateList = "AK,AL,AZ,AR,CA,CO,CT,DE,FL,GA," +
"HI,ID,IL,IN,IA,KS,KY,LA,ME,MD," +
"MA,MI,MN,MS,MO,MT,NE,NV,NH,NJ," +
"NM,NY,NC,ND,OH,OK,OR,PA,RI,SC," +
"SD,TN,TX,UT,VT,VA,WA,WV,WI,WY";
const stateArray = stateList.split(",")

const getRandomState = () => stateArray[Math.floor(Math.random()*stateArray.length)];
const createMessage = num => ({
  key: `${num}`,
  value: `${Math.floor(Math.random()* (10000-10+1)+10)}`,
})

const sendMessage = () => {
  return producer
    .send({
      topic,
      messages: Array(25)
        .fill()
        .map(_ => createMessage(getRandomState())),
    })
    .then(console.log)
    .then(process.exit)
    .catch(e => console.error(`[example/producer] ${e.message}`, e))
}

const run = async () => {
  await producer.connect()
  sendMessage()
}

run().catch(e => console.error(`[example/producer] ${e.message}`, e))
