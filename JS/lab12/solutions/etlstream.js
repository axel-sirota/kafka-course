const {KafkaStreams} = require("kafka-streams");
const config = require("./config.js");

const kafkaStreams = new KafkaStreams(config);

kafkaStreams.on("error", (error) => {
  console.log("Error occured:", error.message);
});


const stream = kafkaStreams.getKStream();

stream
  .from("RawTempReadings")
  .filter(kv => Number(kv.value) > -50)
  .filter(kv => Number(kv.value) < 130)
  .tap(kv => console.log(kv))
  .to("ValidatedTempReadings");

// const inputStream = kafkaStreams.getKStream();
// inputStream.to("RawTempReadings");

// const produceInterval = setInterval(() => {
//   inputStream.writeToStream(`${Math.floor(Math.random()* 300)-100}`);
// }, 100);

Promise.all([
  stream.start(),
  // inputStream.start(),
]).then(() => {
  console.log("started..");
  setTimeout(() => {
      // clearInterval(produceInterval);
      console.log("stopped..");
  }, 5000);
});
