package Miosz.newsPlatform_API.DAL;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class Target {
    public void AddTarget(String name){
        MongoClient mongoClient = MongoClients.create("mongodb://mongo:27017");
        MongoDatabase database = mongoClient.getDatabase("TestDatabase");

        database.getCollection("Target").insertOne(new Document("_id", new ObjectId())
                .append("name", name));
        mongoClient.close();
    }

    public Document ReadTarget(String id){
        MongoClient mongoClient = MongoClients.create("mongodb://mongo:27017");
        MongoDatabase database = mongoClient.getDatabase("TestDatabase");

        Document target = database.getCollection("Target").find(new Document("_id", new ObjectId(id))).first();
        mongoClient.close();
        return target;
    }

    public List<Document> ReadAllTArgets(){
        MongoClient mongoClient = MongoClients.create("mongodb://mongo:27017");
        MongoDatabase database = mongoClient.getDatabase("TestDatabase");

        List<Document> targetList = database.getCollection("Target").find().into(new ArrayList<Document>());
        mongoClient.close();
        return targetList;
    }

    public void DeleteTarget(String id){
        MongoClient mongoClient = MongoClients.create("mongodb://mongo:27017");
        MongoDatabase database = mongoClient.getDatabase("TestDatabase");

        Bson filter = eq("_id", new ObjectId(id));
        database.getCollection("Target").deleteOne(filter);
        mongoClient.close();
    }
}
