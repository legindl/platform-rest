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

public class ResearchPaper {
    public void CreateResearchPaper(String title, String URL, String source, List<String> categories, String description, String cDate, String fDate) {
        MongoClient mongoClient = MongoClients.create("mongodb://mongo:27017");
        MongoDatabase database = mongoClient.getDatabase("TestDatabase");

        database.getCollection("ResearchPaper").insertOne(new Document("_id", new ObjectId())
                .append("title", title)
                .append("url", URL)
                .append("source_id", source)
                .append("categories", categories)
                .append("description",  description)
                .append("createDate", cDate)
                .append("foundDate", fDate));
        mongoClient.close();
    }

    public void UpdateResearchPaperField(String id, String field,String value){
        MongoClient mongoClient = MongoClients.create("mongodb://mongo:27017");
        MongoDatabase database = mongoClient.getDatabase("TestDatabase");

        Bson filter = eq("_id", new ObjectId(id));
        Bson updateOperation = set(field, value);
        database.getCollection("ResearchPaper").updateOne(filter,updateOperation);
        mongoClient.close();
    }


    public void UpdateResearchPaperList(String id, String field, List<?> value){
        MongoClient mongoClient = MongoClients.create("mongodb://mongo:27017");
        MongoDatabase database = mongoClient.getDatabase("TestDatabase");

        Bson filter = eq("_id", new ObjectId(id));
        Bson updateOperation = set(field, value);
        database.getCollection("ResearchPaper").updateOne(filter,updateOperation);
        mongoClient.close();
    }

    public Document ReadResearchPaper(String id){
        MongoClient mongoClient = MongoClients.create("mongodb://mongo:27017");
        MongoDatabase database = mongoClient.getDatabase("TestDatabase");

        Document researchPaper = database.getCollection("ResearchPaper").find(new Document("_id", new ObjectId(id))).first();
        mongoClient.close();
        return researchPaper;
    }

    public List<Document> ReadAllResearchPapers(){
        MongoClient mongoClient = MongoClients.create("mongodb://mongo:27017");
        MongoDatabase database = mongoClient.getDatabase("TestDatabase");

        List<Document> researchPaperList = database.getCollection("ResearchPaper").find().into(new ArrayList<Document>());
        mongoClient.close();
        return researchPaperList;
    }

    public List<Document> ReaAllResearchPapersFromCategory(String category){
        MongoClient mongoClient = MongoClients.create("mongodb://mongo:27017");
        MongoDatabase database = mongoClient.getDatabase("TestDatabase");

        List<Document> researchPaperList = database.getCollection("ResearchPaper").find(gte("categories", category)).into(new ArrayList<Document>());
        mongoClient.close();
        return researchPaperList;
    }

    public void DeleteResearchPaper(String id){
        MongoClient mongoClient = MongoClients.create("mongodb://mongo:27017");
        MongoDatabase database = mongoClient.getDatabase("TestDatabase");

        Bson filter = eq("_id", new ObjectId(id));
        database.getCollection("ResearchPaper").deleteOne(filter);
        mongoClient.close();
    }

}
