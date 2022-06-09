package dev.dmohindru.mongoclientdemo;


import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoDBConnectionDemo {
    public static void main(String[] args) {

        //String CONNECTION_STRING_OLD_DRIVER = "mongodb://pp-staging:2hucZFecf7dDcMCl@prodmaindb-shard-00-00.9nyog.mongodb.net:27017,prodmaindb-shard-00-01.9nyog.mongodb.net:27017,prodmaindb-shard-00-02.9nyog.mongodb.net:27017/?ssl=true&replicaSet=atlas-x37tpa-shard-0&authSource=admin&retryWrites=true&w=majority";

        String CONNECTION_STRING_NEW_DRIVER =
                "mongodb+srv://pp-staging:2hucZFecf7dDcMCl@pp-mgd-staging.y1dgp.mongodb.net/ppMgdStagingDB";
        ConnectionString connectionString = new ConnectionString(CONNECTION_STRING_NEW_DRIVER);


        MongoClient mongoClient = MongoClients.create(connectionString);
        MongoDatabase mongoDB = mongoClient.getDatabase("ppMgdStagingDB");
        MongoCollection<Document> orders = mongoDB.getCollection("Orders");
        System.out.println("Total Documents: " + orders.countDocuments());

    }
}
