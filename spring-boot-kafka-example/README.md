### Start zookeeper
bin/zookeeper-server-start.sh config/zookeeper.properties

### Start kafka server
bin/kafka-server-start.sh config/server.properties

### View topic messages
bin/kafka-console-consumer.sh --topic javaguides_json --from-beginning --bootstrap-server localhost:9092