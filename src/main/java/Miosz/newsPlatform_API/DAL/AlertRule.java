package Miosz.newsPlatform_API.DAL;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;

public class AlertRule {
    public void CreateAlertRuleNews( String userID, String newsID, String targetID, String valueIsID, Double value){
        MongoClient mongoClient = MongoClients.create("mongodb://mongo:27017");
        MongoDatabase database = mongoClient.getDatabase("TestDatabase");

        Document user = new User().ReadUserInfo(userID);
        ArrayList alertRules = (ArrayList<Document>) user.get("rules");
        alertRules.add(new Document("_id", new ObjectId())
                .append("news_id", newsID)
                .append("target_id", targetID)
                .append("valueis_id", valueIsID)
                .append("value", value));
        new User().UpdateUserList(userID,"rules", alertRules);
        mongoClient.close();
    }

    public void CreateAlertRuleCategory(String userID, String categoryID, String targetID, String valueIsID, Double value){
        MongoClient mongoClient = MongoClients.create("mongodb://mongo:27017");
        MongoDatabase database = mongoClient.getDatabase("TestDatabase");

        Document user = new User().ReadUserInfo(userID);
        ArrayList alertRules = (ArrayList<Document>) user.get("rules");
        alertRules.add(new Document("_id", new ObjectId())
                .append("category_id", categoryID)
                .append("target_id", targetID)
                .append("valueis_id", valueIsID)
                .append("value", value));
        new User().UpdateUserList(userID,"rules", alertRules);
        mongoClient.close();
    }

    public void RemoveAlertRule(String userID,String ruleID){
        MongoClient mongoClient = MongoClients.create("mongodb://mongo:27017");
        MongoDatabase database = mongoClient.getDatabase("TestDatabase");

        Document user = new Source().ReadSource(userID);

        int counter = 0;
        ArrayList<Document> comments =(ArrayList<Document>) user.get("rules");
        for (Document comment:comments) {
            if(comment.get("_id").toString().equals(ruleID)){
                comments.remove(counter);
                break;
            }
            else{
                counter++;
            }
        }
        new Source().UpdateSourceList(userID,"rules", comments);
        mongoClient.close();
    }
}
