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

public class Category {
    public void CreateCategory(String name, List<String> keywords){
        MongoClient mongoClient = MongoClients.create("mongodb://mongo:27017");
        MongoDatabase database = mongoClient.getDatabase("TestDatabase");

        database.getCollection("Category").insertOne(new Document("_id", new ObjectId())
                .append("name", name)
                .append("keywords", keywords));
        mongoClient.close();
    }

    public Document ReadCategory(String id){
        MongoClient mongoClient = MongoClients.create("mongodb://mongo:27017");
        MongoDatabase database = mongoClient.getDatabase("TestDatabase");

        Document category = database.getCollection("Category").find(new Document("_id", new ObjectId(id))).first();
        mongoClient.close();
        return category;
    }

    public String ReadCategoryIDWithName(String name){
        MongoClient mongoClient = MongoClients.create("mongodb://mongo:27017");
        MongoDatabase database = mongoClient.getDatabase("TestDatabase");
        Document category = database.getCollection("Category").find(eq("name", name)).first();
        mongoClient.close();
        return category.get("_id").toString();

    }

    public List<Document> ReadAllCategories(){
        MongoClient mongoClient = MongoClients.create("mongodb://mongo:27017");
        MongoDatabase database = mongoClient.getDatabase("TestDatabase");

        List<Document> categoryList = database.getCollection("Category").find().into(new ArrayList<Document>());
        mongoClient.close();
        return categoryList;
    }

    public void DeleteCategory(String id){
        MongoClient mongoClient = MongoClients.create("mongodb://mongo:27017");
        MongoDatabase database = mongoClient.getDatabase("TestDatabase");

        Bson filter = eq("_id", new ObjectId(id));
        database.getCollection("Category").deleteOne(filter);
        mongoClient.close();
    }
}
