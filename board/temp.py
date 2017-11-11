import sys
from kafka import KafkaProducer
import time
import random
#/opt/Kafka/kafka_2.10-0.10.0.1/bin/kafka-console-producer.sh --broker-list localhost:9092 --topic testing
chip_id=sys.argv[1] #incremented by the script
kafka_ip=sys.argv[2]+":9092" #localhost for localhost :)
print "starting board with id: ",chip_id
print "trying to connect kafka on: ", kafka_ip
try:
	producer=KafkaProducer(bootstrap_servers=[kafka_ip])
	
	while True:
		
		producer.send('flink-demo',str(random.randint(0,70)))
		#print "sent! by ", chip_id
		time.sleep(1)
except:
	e = sys.exc_info()[0]
	print e
