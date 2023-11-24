### Start zookeeper
bin/zookeeper-server-start.sh config/zookeeper.properties

### Start kafka server
bin/kafka-server-start.sh config/server.properties

### View topic messages
bin/kafka-console-consumer.sh --topic wikimedia_recentchange --from-beginning --bootstrap-server localhost:9092

### Run MySQL as docker container
docker run --name=mysql -e MYSQL_ROOT_PASSWORD=Mysql@123 -p 3306:3306 mysql/mysql-server:latest