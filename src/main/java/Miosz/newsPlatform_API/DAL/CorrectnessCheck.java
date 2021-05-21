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

public class CorrectnessCheck {
    public void UpdateCorrectnessCheck(String newsID, List<String> researchPapers, double grade) {
        MongoClient mongoClient = MongoClients.create("mongodb://mongo:27017");
        MongoDatabase database = mongoClient.getDatabase("TestDatabase");

        Bson filter = eq("_id", new ObjectId(newsID));
        Bson updateOperation = set("correctnesscheck", new Document("_id", new ObjectId())
                .append("new_id", newsID)
                .append("researchPapers", researchPapers)
                .append("grade", grade));
        database.getCollection("News").updateOne(filter, updateOperation);
        mongoClient.close();
    }
}
