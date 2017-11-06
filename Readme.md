# Master Praktikum Pipeline

This repository connects Apache Kafka, Apache Flink and Elasticsearch and exposes an API. The IoT board is also simulated in the repo.

## Instructions
To run this project make sure that you have Kafka, Flink and Elasticsearch are running correctly. 
### board
This is for simulating an IOT board with a temperature sensor. It has a kafka-producer to generate data to kafka broker. To run it simply `pip -r install requirements.txt` and `python temp.py`.
### server
Server is a python Flask server with a python Elasticsearch client. It has one endpoint that lists indices of the localhost cluster. To run it `pip -r install requirements.txt` and `python server.py`. The server starts listening on *localhost:5000*.
### kafka-flink-101
The main Apache pipeline. It uses the Flink-Kafka connector. I couldn't connect Elasticsearch through Flink's connector so I wrote a CURL sender class to send the data to elasticsearch. To run it, you should have maven installed. To install maven, run `apt-get install maven` for linux systems. Inside the directory ,run `mvn clean package` to download dependencies and build the project. Then run `mvn exec:java -Dexec.mainClass=com.grallandco.demos.ReadFromKafka`. This project is cloned from [this Github repository](https://github.com/tgrall/kafka-flink-101). After that the ElasticSearch connection is added.

## API Endpoints

- `http://localhost:5000/`  
    will return every entry in `temperature` index.

- `http://localhost:5000/api/v1/{indexName}/{id}`  
    will return the document with the given `id` from the index `indexName`.


#### Kafka Commands
    /opt/Kafka/kafka_2.10-0.10.0.1/bin/kafka-topics.sh --list --zookeeper localhost:2181

    /opt/Kafka/kafka_2.10-0.10.0.1/bin/kafka-server-start.sh /opt/Kafka/kafka_2.10-0.10.0.1/config/server.properties

    /opt/Kafka/kafka_2.10-0.10.0.1/bin/kafka-console-consumer.sh --zookeeper localhost:2181 --topic testing from-beginning

    /opt/Kafka/kafka_2.10-0.10.0.1/bin/kafka-console-producer.sh --broker-list localhost:9092 --topic testing

    mvn exec:java -Dexec.mainClass=com.grallandco.demos.ReadFromKafka


