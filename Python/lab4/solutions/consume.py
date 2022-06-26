import random
import sys
import time
import logging
from kafka import KafkaConsumer

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
            print(f'Got message {message.key.decode("utf-8")}:{message.value.decode("utf-8")}')
    time.sleep(1)
