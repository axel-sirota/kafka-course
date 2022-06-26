import random
import sys
import time
from kafka import KafkaProducer
import logging

def on_send_success(record_metadata):
    logging.info(record_metadata.topic)
    logging.info(record_metadata.partition)
    logging.info(record_metadata.offset)

def on_send_error(excp):
    logging.error('I am an errback', exc_info=excp)
    # handle exception

producer = KafkaProducer(bootstrap_servers=['localhost:9092', 'localhost:9093'], key_serializer=str.encode, value_serializer=str.encode)
topic_list = ["flood", "hurricane"]
for _ in range(2500):
    topic = random.choice(topic_list)
    key = topic
    partition = random.randint(0, 2)
    value = str(random.randint(1, 10))
    logging.info(f'About to send message {key}:{value} to {topic}:{partition}')
    producer.send(topic=topic, partition=partition, key=key, value=value).add_callback(on_send_success).add_errback(on_send_error)
    time.sleep(random.random())
producer.flush()
producer.close()
