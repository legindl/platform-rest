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
import static com.mongodb.client.model.Filters.gte;
import static com.mongodb.client.model.Updates.set;

public class Source {
    public void CreateSource(String name, String url, String descritpion, List<String> categories){
        MongoClient mongoClient = MongoClients.create("mongodb://mongo:27017");
        MongoDatabase database = mongoClient.getDatabase("TestDatabase");

        database.getCollection("Source").insertOne(new Document("_id", new ObjectId())
                .append("name", name)
                .append("url", url)
                .append("description", descritpion)
                .append("categories", categories)
                .append("grade", 0));

        mongoClient.close();
    }

    public void UpdateSourceField(String id, String field,String value){
        MongoClient mongoClient = MongoClients.create("mongodb://mongo:27017");
        MongoDatabase database = mongoClient.getDatabase("TestDatabase");

        Bson filter = eq("_id", new ObjectId(id));
        Bson updateOperation = set(field, value);
        database.getCollection("Source").updateOne(filter,updateOperation);
        mongoClient.close();
    }

    public void UpdateSourceList(String id, String field, List<?> value) {
        MongoClient mongoClient = MongoClients.create("mongodb://mongo:27017");
        MongoDatabase database = mongoClient.getDatabase("TestDatabase");

        Bson filter = eq("_id", new ObjectId(id));
        Bson updateOperation = set(field, value);
        database.getCollection("Source").updateOne(filter,updateOperation);
        mongoClient.close();
    }

    public Document ReadSource(String id){
        MongoClient mongoClient = MongoClients.create("mongodb://mongo:27017");
        MongoDatabase database = mongoClient.getDatabase("TestDatabase");

        Document source = database.getCollection("Source").find(new Document("_id", new ObjectId(id))).first();
        mongoClient.close();
        return source;
    }

    public String ReadSourceIDWithName(String name){
        MongoClient mongoClient = MongoClients.create("mongodb://mongo:27017");
        MongoDatabase database = mongoClient.getDatabase("TestDatabase");
        Document source = database.getCollection("Source").find(eq("name", name)).first();
        mongoClient.close();
        return source.get("_id").toString();
    }

    public List<Document> ReadAllSources(){
        MongoClient mongoClient = MongoClients.create("mongodb://mongo:27017");
        MongoDatabase database = mongoClient.getDatabase("TestDatabase");

        List<Document> sourceList = database.getCollection("Source").find().into(new ArrayList<Document>());
        mongoClient.close();
        return sourceList;
    }

    public List<Document> ReadAllSourcesWithCategory(String category){
        MongoClient mongoClient = MongoClients.create("mongodb://mongo:27017");
        MongoDatabase database = mongoClient.getDatabase("TestDatabase");

        List<Document> sourceList = database.getCollection("Source").find(gte("categories", category)).into(new ArrayList<Document>());
        mongoClient.close();
        return sourceList;
    }

    public void DeleteSource(String id){
        MongoClient mongoClient = MongoClients.create("mongodb://mongo:27017");
        MongoDatabase database = mongoClient.getDatabase("TestDatabase");

        Bson filter = eq("_id", new ObjectId(id));
        database.getCollection("Source").deleteOne(filter);
        mongoClient.close();
    }
}
