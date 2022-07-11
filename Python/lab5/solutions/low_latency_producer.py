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

producer = KafkaProducer(bootstrap_servers=['localhost:9092', 'localhost:9093'], 
                        acks = 0,
                        batch_size = 512,   # in bytes
                        linger_ms = 0)
topic = "latency"
latencies = []
start_time = time.time()
count = 0
while count < 100000:
    producer.send(topic=topic, key=b'1', value=b'1')
    time_now = time.time()
    latencies.append(time_now-start_time)
    start_time = time_now
    count += 1
producer.flush()
producer.close()
print(f'I have taken {sum(latencies)/100000} s to send 100000 messages')
