import random
import sys
import time
import socket
import logging
from kafka import KafkaConsumer, KafkaProducer


def on_send_success(record_metadata):
    logging.info(record_metadata.topic)
    logging.info(record_metadata.partition)
    logging.info(record_metadata.offset)

def on_send_error(excp):
    logging.error('I am an errback', exc_info=excp)
    # handle exception


def processMessages():
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
        postgress_available = s.connect_ex(('localhost', 5432)) == 0
        mongo_available = s.connect_ex(('localhost', 27017)) == 0
    return postgress_available and mongo_available 

producer = KafkaProducer(bootstrap_servers=['localhost:9092', 'localhost:9093'], key_serializer=str.encode, value_serializer=str.encode)
TOPIC_RETRY = sys.argv[2]
# To consume latest messages and auto-commit offsets
consumer = KafkaConsumer(sys.argv[1],
                         group_id=f'{sys.argv[1]}.consumer',
                         bootstrap_servers=['localhost:9092', 'localhost:9093'],
                         auto_offset_reset='earliest', 
                         enable_auto_commit=False)

while True:
    partitions_assigned = consumer.poll(timeout_ms=1000, max_records=25)
    print(f'Got assigned the following partitions in the poll: {partitions_assigned.keys()}')
    for partition in partitions_assigned.keys():
        for message in partitions_assigned[partition]:
            key = message.key.decode("utf-8")
            value = message.value.decode("utf-8")
            print(f'Got message {key}:{value}')
            should_retry = processMessages()
            print(f'Result of the pings: {should_retry}')
            if should_retry:
                print(f'About to send message {key}:{value} to {TOPIC_RETRY}')
                producer.send(TOPIC_RETRY, key=key, value=value).add_callback(on_send_success).add_errback(on_send_error)
    time.sleep(1)

