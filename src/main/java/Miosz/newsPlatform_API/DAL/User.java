package Miosz.newsPlatform_API.DAL;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

public class User {
    public void CreateUser(String name, String pass, String email){
        MongoClient mongoClient = MongoClients.create("mongodb://mongo:27017");
        MongoDatabase database = mongoClient.getDatabase("TestDatabase");

        database.getCollection("User").insertOne(new Document("_id", new ObjectId())
                .append("name", name)
                .append("password", pass)
                .append("email",email));
        mongoClient.close();
    }

    public void UpdateUserField(String id, String field,String value){
        MongoClient mongoClient = MongoClients.create("mongodb://mongo:27017");
        MongoDatabase database = mongoClient.getDatabase("TestDatabase");

        Bson filter = eq("_id", new ObjectId(id));
        Bson updateOperation = set(field, value);
        database.getCollection("User").updateOne(filter,updateOperation);
        mongoClient.close();
    }

    public void UpdateUserList(String id, String field, List<?> value) {
        MongoClient mongoClient = MongoClients.create("mongodb://mongo:27017");
        MongoDatabase database = mongoClient.getDatabase("TestDatabase");

        Bson filter = eq("_id", new ObjectId(id));
        Bson updateOperation = set(field, value);
        database.getCollection("User").updateOne(filter,updateOperation);
        mongoClient.close();
    }

    public Document ReadUserInfo(String id){
        MongoClient mongoClient = MongoClients.create("mongodb://mongo:27017");
        MongoDatabase database = mongoClient.getDatabase("TestDatabase");

        Document user = database.getCollection("User").find(new Document("_id", new ObjectId(id))).first();
        mongoClient.close();
        return user;
    }

    public void DeleteUser(String id){
        MongoClient mongoClient = MongoClients.create("mongodb://mongo:27017");
        MongoDatabase database = mongoClient.getDatabase("TestDatabase");


        Bson filter = eq("_id", new ObjectId(id));
        database.getCollection("User").deleteOne(filter);
        mongoClient.close();
    }
}
