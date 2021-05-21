package Miosz.newsPlatform_API.DAL;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gte;
import static com.mongodb.client.model.Updates.set;

public class News {
    public Document CreateNews(String title, String url, String sourceID, List<String> categories, String description, String cDate, String fDate){
        MongoClient mongoClient = MongoClients.create("mongodb://mongo:27017");
        MongoDatabase database = mongoClient.getDatabase("TestDatabase");

        Document news = new Document("_id", new ObjectId())
                .append("title",title)
                .append("url",url)
                .append("source_id",sourceID)
                .append("categories", categories)
                .append("description",description)
                .append("createDate", cDate)
                .append("foundDate", fDate);

        if(database.getCollection("News").find(eq("title", title)).first() == null){
            database.getCollection("News").insertOne(news);
            mongoClient.close();
            return news;
        }
        else{
            System.out.println("News Already exists");
            mongoClient.close();
            return null;
        }
    }

    public void UpdateNewsField(String id, String field,String value) {
        MongoClient mongoClient = MongoClients.create("mongodb://mongo:27017");
        MongoDatabase database = mongoClient.getDatabase("TestDatabase");

        Bson filter = eq("_id", new ObjectId(id));
        Bson updateOperation = set(field, value);
        database.getCollection("News").updateOne(filter,updateOperation);
        mongoClient.close();
    }

    public void UpdateNewsList(String id, String field, List<?> value) {
        MongoClient mongoClient = MongoClients.create("mongodb://mongo:27017");
        MongoDatabase database = mongoClient.getDatabase("TestDatabase");

        Bson filter = eq("_id", new ObjectId(id));
        Bson updateOperation = set(field, value);
        database.getCollection("News").updateOne(filter,updateOperation);
        mongoClient.close();
    }

    public Document ReadNews(String id){
        MongoClient mongoClient = MongoClients.create("mongodb://mongo:27017");
        MongoDatabase database = mongoClient.getDatabase("TestDatabase");

        Document news = database.getCollection("News").find(new Document("_id", new ObjectId(id))).first();
        mongoClient.close();
        return news;
    }

    public List<Document> ReadAllNews(){
        MongoClient mongoClient = MongoClients.create("mongodb://mongo:27017");
        MongoDatabase database = mongoClient.getDatabase("TestDatabase");

        List<Document> newsList = database.getCollection("News").find().into(new ArrayList<Document>());
        mongoClient.close();
        return newsList;
    }

    public List<Document> ReadAllNewsFromCategory(String category){
        MongoClient mongoClient = MongoClients.create("mongodb://mongo:27017");
        MongoDatabase database = mongoClient.getDatabase("TestDatabase");

        List<Document> newsList = database.getCollection("News").find(gte("categories", category)).into(new ArrayList<Document>());
        mongoClient.close();
        return newsList;
    }

    public void DeleteNews(String id){
        MongoClient mongoClient = MongoClients.create("mongodb://mongo:27017");
        MongoDatabase database = mongoClient.getDatabase("TestDatabase");

        Bson filter = eq("_id", new ObjectId(id));
        database.getCollection("News").deleteOne(filter);
        mongoClient.close();
    }
}
