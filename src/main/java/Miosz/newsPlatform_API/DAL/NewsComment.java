package Miosz.newsPlatform_API.DAL;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Date;

public class NewsComment {
    public void AddCommentToNews(String newsID, String commentContent, String userID){
        MongoClient mongoClient = MongoClients.create("mongodb://mongo:27017");
        MongoDatabase database = mongoClient.getDatabase("TestDatabase");

        Document news = new News().ReadNews(newsID);
        ArrayList comments =(ArrayList<Document>) news.get("comments");
        comments.add(new Document("_id", new ObjectId())
                .append("content", commentContent)
                .append("user_id", userID)
                .append("date", new Date().toString()));
        new News().UpdateNewsList(newsID,"comments", comments);
        mongoClient.close();
    }

    public void RemoveCommentFromNews(String newsID,String commentID){
        MongoClient mongoClient = MongoClients.create("mongodb://mongo:27017");
        MongoDatabase database = mongoClient.getDatabase("TestDatabase");

        Document news = new News().ReadNews(newsID);
        int counter = 0;
        ArrayList<Document> comments =(ArrayList<Document>) news.get("comments");
        for (Document comment:comments) {
            if(comment.get("_id").toString().equals(commentID)){
                comments.remove(counter);
                break;
            }
            else{
                counter++;
            }
        }
        new News().UpdateNewsList(newsID,"comments", comments);
        mongoClient.close();
    }

    public void EditCommentOnNews(String newsID,String commentID, String newContent){
        MongoClient mongoClient = MongoClients.create("mongodb://mongo:27017");
        MongoDatabase database = mongoClient.getDatabase("TestDatabase");

        Document news = new News().ReadNews(newsID);
        ArrayList<Document> comments =(ArrayList<Document>) news.get("comments");
        for (Document comment:comments) {
            if(comment.get("_id").toString().equals(commentID)){
                comment.remove("content");
                comment.append("content", newContent)
                        .append("lastEditDate", new Date().toString());
                break;
            }
        }
        new News().UpdateNewsList(newsID,"comments", comments);
        mongoClient.close();
    }
}
