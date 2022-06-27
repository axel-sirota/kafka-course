import random
import sys
import time
from kafka import KafkaAdminClient

admin = KafkaAdminClient(bootstrap_servers=['localhost:9092', 'localhost:9093'])
topic = "deliveries"
admin.create_topics([topic])


admin.delete_topics([topic])