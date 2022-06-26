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
state_string = "AK,AL,AZ,AR,CA,CO,CT,DE,FL,GA," \
       "HI,ID,IL,IN,IA,KS,KY,LA,ME,MD," \
       "MA,MI,MN,MS,MO,MT,NE,NV,NH,NJ," \
       "NM,NY,NC,ND,OH,OK,OR,PA,RI,SC," \
       "SD,TN,TX,UT,VT,VA,WA,WV,WI,WY"
state_list = state_string.split(',')
TOPIC = "my_orders4"
for _ in range(25):
    key = random.choice(state_list)
    value = str(random.randint(10, 10000))
    logging.info(f'About to send message {key}:{value} to {TOPIC}')
    producer.send(TOPIC, key=key, value=value).add_callback(on_send_success).add_errback(on_send_error)
    # time.sleep(random.random()*30)
producer.flush()
producer.close()
