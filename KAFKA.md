# Apache Kafka with Mutual TLS – Full Command & Configuration Guide

This guide provides detailed commands and explanations for setting up a Kafka server and configuring mutual TLS (mTLS) between Kafka brokers and clients. Mutual TLS ensures that both parties authenticate each other using certificates.


## KAFKA SERVER COMMANDS
### 1.0 - Start kafka server
Starts the Kafka broker in the background using the configuration specified in server.properties.
```bash
sudo sh kafka-server-start.sh ../config/server.properties &
```


### 1.1 - Stop kafka server
Stops the running Kafka broker.
```bash
sudo sh kafka-server-stop.sh
```


### 1.3 - Read as Consumer
Starts a Kafka consumer to read messages from the specified topic from the beginning.
```bash
sudo sh kafka-console-consumer.sh --bootstrap-server {host}:{port} --topic {topic-name} --from-beginning
```


## TOPIC COMMANDS
### 2.0 - Get all topics
Lists all topics registered in the Kafka cluster (using ZooKeeper).
```bash
sudo sh kafka-topics.sh --zookeeper localhost:2181 --list
```


### 2.1 - Delete topic
Deletes the specified topic from the Kafka cluster.
```bash
sudo sh kafka-topics.sh --zookeeper localhost:2181 --delete --topic {topic-name}
```


### 2.2 - Create a Topic on Kafka Server
Creates a new topic with a specified replication factor and number of partitions.
Note: Replace {topic-name} with your desired topic name.
```bash
sudo sh kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic {topic-name
```


## CREATION BROKER CERTIFICATE COMMANDS
### 3.0 - Create certificate for kafka broker
Generates a key pair and creates a keystore (innbets-feed-broker.keystore.jks) for the Kafka broker.
Alias: innbets-feed-broker
Validity: 365 days
Passwords: Set for both keystore and key.
```bash
keytool -genkeypair -alias innbets-feed-broker   -keyalg RSA -keystore innbets-feed-broker.keystore.jks -validity 365 -storepass ksock0wis0icke03owWokeoxhudhinao -keypass d4101058d4078bbd900e1f168d659b36327a598cb650b6b5af23ff7b202d7d36
```


### 3.1 - Add ssl configuration in server.properties file
ssl.keystore.location: Full path to the broker keystore.
ssl.keystore.password: Password for the keystore.
ssl.key.password: Password for the key inside the keystore.
ssl.client.auth=required: Enforces mutual TLS by requiring client authentication.
```
string: ksock0wis0icke03owWokeoxhudhinao
key: Dodks0dd0ickaso02edjciahi
hmac: d4101058d4078bbd900e1f168d659b36327a598cb650b6b5af23ff7b202d7d36
```
> Update Kafka's server.properties file with the full path to the keystore, for example:
```

ssl.keystore.location=/home/kafka/kafka/ssl/innbets-feed-broker.keystore.jks
ssl.keystore.password=ksock0wis0icke03owWokeoxhudhinao
ssl.key.password=d4101058d4078bbd900e1f168d659b36327a598cb650b6b5af23ff7b202d7d36
ssl.client.auth=required
```

### 3.2 - Export public certificate for the CLIENTS
```bash
keytool -export -alias innbets-feed-broker -file feed-client-broker.cert -keystore innbets-feed-broker.keystore.jks -storepass ksock0wis0icke03owWokeoxhudhinao
```

##  4.0 - CLIENT TRUSTSTORE CREATION
The truststore is used to verify the identity of the broker. To do this, the client must import the broker's public certificate (the one exported previously, for example feed-client-broker.cert).
```bash
keytool -import -alias innbets-feed-broker \
-file /percorso/del/file/feed-client-broker.cert \
-keystore kafka.client.truststore.jks \
-storepass clientTruststorePass
```
> Note: If the file does not exist, the command will create it. Make sure the path to the feed-client-broker.cert file is correct on the client.


## 5.0 CLIENT KEYSTORE CREATION
For two-way authentication (mutual TLS), each client must have its own keystore containing the private key and associated certificate.
```bash
keytool -genkeypair -alias kafka-client -keyalg RSA -keystore kafka.client.keystore.jks -validity 365 -storepass clientKeystorePass -keypass clientKeyPass
```


### 5.1 - Export the client certificate
This certificate must then be imported into the broker's truststore to authorize the client.
```bash
keytool -export -alias kafka-client -file client.cert -keystore kafka.client.keystore.jks -storepass clientKeystorePass
```


## 6.0 CONFIGURE THE CLIENT PROPERTIES FILE
Create or update the configuration file (e.g. client.properties) with the following properties to enable SSL and mutual TLS connection:
### Keystore del client (necessario per mutual TLS)
```
bootstrap.servers=10.10.3.229:9093
security.protocol=SSL
ssl.keystore.location=/path/to/kafka.client.keystore.jks
ssl.keystore.password=clientKeystorePass
ssl.key.password=clientKeyPass
```
### Truststore del client (per verificare il broker)
```
ssl.truststore.location=/path/to/kafka.client.truststore.jks
ssl.truststore.password=clientTruststorePass
```


## 6.0 - IMPORT THE CLIENT CERTIFICATE INTO THE BROKER'S TRUSTSTORE:
Allows the broker to verify that the certificate presented by the client is trusted.
```bash
keytool -import -alias kafka-client \
-file client.cert \
-keystore kafka.broker.truststore.jks \
-storepass brokerTruststorePass
```

