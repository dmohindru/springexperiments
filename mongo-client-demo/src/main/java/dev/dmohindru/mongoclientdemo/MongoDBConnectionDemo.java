package dev.dmohindru.mongoclientdemo;


import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoDBConnectionDemo {
    public static void main(String[] args) {

        //String CONNECTION_STRING_OLD_DRIVER = "old connection string format";

        String CONNECTION_STRING_NEW_DRIVER =
                "you mongo db secret: mongodb + srv connection string";
        ConnectionString connectionString = new ConnectionString(CONNECTION_STRING_NEW_DRIVER);


        MongoClient mongoClient = MongoClients.create(connectionString);
        MongoDatabase mongoDB = mongoClient.getDatabase("ppMgdStagingDB");
        MongoCollection<Document> orders = mongoDB.getCollection("Orders");
        System.out.println("Total Documents: " + orders.countDocuments());

    }
}
