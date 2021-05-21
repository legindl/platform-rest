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

public class ValueIs {
    public void AddValueIs(String name){
        MongoClient mongoClient = MongoClients.create("mongodb://mongo:27017");
        MongoDatabase database = mongoClient.getDatabase("TestDatabase");

        database.getCollection("ValueIs").insertOne(new Document("_id", new ObjectId())
                .append("name", name));
        mongoClient.close();
    }

    public Document ReadValueIs(String id){
        MongoClient mongoClient = MongoClients.create("mongodb://mongo:27017");
        MongoDatabase database = mongoClient.getDatabase("TestDatabase");

        Document target = database.getCollection("ValueIs").find(new Document("_id", new ObjectId(id))).first();
        mongoClient.close();
        return target;
    }

    public List<Document> ReadAllValueIs(){
        MongoClient mongoClient = MongoClients.create("mongodb://mongo:27017");
        MongoDatabase database = mongoClient.getDatabase("TestDatabase");

        List<Document> targetList = database.getCollection("ValueIs").find().into(new ArrayList<Document>());
        mongoClient.close();
        return targetList;
    }

    public void DeleteValueIs(String id){
        MongoClient mongoClient = MongoClients.create("mongodb://mongo:27017");
        MongoDatabase database = mongoClient.getDatabase("TestDatabase");

        Bson filter = eq("_id", new ObjectId(id));
        database.getCollection("ValueIs").deleteOne(filter);
        mongoClient.close();
    }
}
