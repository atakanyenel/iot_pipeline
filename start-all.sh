#!/bin/bash
trap "kill 0" EXIT
#kafka
/opt/Kafka/kafka_2.10-0.10.0.1/bin/kafka-server-start.sh /opt/Kafka/kafka_2.10-0.10.0.1/config/server.properties &
#flink
/root/flink-1.3.2/bin/start-local.sh
#elasticsearch as user: elasticuser
runuser -l elasticuser -c /home/elasticuser/elasticsearch-5.6.3/bin/elasticsearch &

#kafka-flink-elasticsearch connector
mvn -f ./kafka-flink-101/pom.xml exec:java -Dexec.mainClass=com.grallandco.demos.ReadFromKafka &


#python server
server/venv/bin/python server/server.py &

wait
