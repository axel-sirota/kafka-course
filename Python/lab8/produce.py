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
TOPIC = "new-driver"
for _ in range(25):
    key = TOPIC
    value = TOPIC
    logging.info(f'About to send message {key}:{value} to {TOPIC}')
    producer.send(TOPIC, key=key, value=value).add_callback(on_send_success).add_errback(on_send_error)
producer.flush()
producer.close()
