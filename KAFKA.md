## KAFKA SERVER COMMANDS
### 1.0 - Start kafka server
```bash
sudo sh kafka-server-start.sh ../config/server.properties &
```
### 1.1 - Stop kafka server
```bash
sudo sh kafka-server-stop.sh
```
### 1.3 - Read as Consumer
```bash
sudo sh kafka-console-consumer.sh --bootstrap-server {host}:{port} --topic {topic-name} --from-beginning
```

## TOPIC COMMANDS
### 2.0 - Get all topics
```bash
sudo sh kafka-topics.sh --zookeeper localhost:2181 --list
```
### 2.1 - Delete topic
```bash
sudo sh kafka-topics.sh --zookeeper localhost:2181 --delete --topic {topic-name}
```
### 2.2 - Create a Topic on Kafka Server
```bash
sudo sh kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic {topic-name
```