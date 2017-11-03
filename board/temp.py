import sys
from kafka import KafkaProducer
import time
import random
#/opt/Kafka/kafka_2.10-0.10.0.1/bin/kafka-console-producer.sh --broker-list localhost:9092 --topic testing
try:
	producer=KafkaProducer(bootstrap_servers=['localhost:9092'])
	
	while True:
		
		producer.send('flink-demo',str(random.randint(0,70)))
		print "sent!"
		time.sleep(1)
except:
	e = sys.exc_info()[0]
	print e
