const path = require('path')
const { SchemaRegistry, SchemaType, readAVSCAsync } = require('@kafkajs/confluent-schema-registry')

const registry = new SchemaRegistry({ host: 'http://localhost:8081' })
const run = async () => {
  const schema = await readAVSCAsync(path.join(__dirname, 'DisasterValue.avsc'))
  const id = await registry.register({ type: SchemaType.AVRO, schema: JSON.stringify(schema), options: { subject: 'disaster' } }).then(console.log).catch(async e => {
    console.error(e)
    process.exit(1)
  })
  console.log(id)
}

run().then(console.log).catch(async e => {
  console.error(e)
  process.exit(1)
})
