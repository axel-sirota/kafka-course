
import io

from confluent_kafka import Consumer, KafkaError
from avro.io import DatumReader, BinaryDecoder
import avro.schema

schema = avro.schema.parse(open("DisasterValue.avsc").read())
reader = DatumReader(schema)

def decode(msg_value):
    message_bytes = io.BytesIO(msg_value)
    message_bytes.seek(5)
    decoder = BinaryDecoder(message_bytes)
    event_dict = reader.read(decoder)
    return event_dict

topic = "disasters"

consumer_config = {
    "group.id": 'disaster.example',
    "bootstrap.servers": 'localhost:9092',
}
c = Consumer(consumer_config)
c.subscribe([topic])
checks = 0
while checks < 1000:
    checks += 1
    msg = c.poll()
    if not msg.error():
        msg_value = msg.value()
        event_dict = decode(msg_value)
        print(event_dict)
    elif msg.error().code() != KafkaError._PARTITION_EOF:
        print(msg.error())
        running = False
