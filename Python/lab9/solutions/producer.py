import random
from confluent_kafka import avro
import logging
import uuid
from confluent_kafka.avro import AvroProducer

def load_avro_schema_from_file(schema_file="DisasterValue.avsc"):
    key_schema_string = """
    {"type": "string"}
    """

    key_schema = avro.loads(key_schema_string)
    value_schema = avro.load(schema_file)

    return key_schema, value_schema

def create_message(disaster, intensity_scale, intensity, recommendations):
    return {
        'disasterType': disaster,
        'intensity': {
            'scale': intensity_scale,
            'measurement': intensity
        },
        'recommendations': recommendations
  }

def send_record():
    key_schema, value_schema = load_avro_schema_from_file()

    producer_config = {
        "bootstrap.servers": 'localhost:9092',
        "schema.registry.url": 'http://localhost:8081'
    }

    producer = AvroProducer(producer_config, default_key_schema=key_schema, default_value_schema=value_schema)
    topic = "disasters"
    for _ in range(1000):
        key = str(uuid.uuid4())
        disaster = random.choice(["flood", "hurricane"])
        intensity = random.randint(1, 10)
        recommendations_dict = {
            'flood': ["turn off lights", "evacuate"],  
            'hurricane': ["run"]
        }
        scales_dict = {
            'flood': "meters",
            'hurricane': "richter"
        }
        value = create_message(disaster=disaster, intensity=intensity, recommendations=recommendations_dict[disaster], intensity_scale=scales_dict[disaster])

        try:
            producer.produce(topic=topic, key=key, value=value)
        except Exception as e:
            print(f"Exception while producing record value - {value} to topic - {topic}: {e}")
        else:
            print(f"Successfully producing record value - {value} to topic - {topic}")

    producer.flush()


if __name__ == "__main__":
    send_record()