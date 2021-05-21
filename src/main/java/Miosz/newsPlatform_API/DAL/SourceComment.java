package Miosz.newsPlatform_API.DAL;

import org.bson.Document;
import org.bson.types.ObjectId;
import java.util.ArrayList;
import java.util.Date;

public class SourceComment {
    public void AddCommentToSource(String sourceID, String commentContent, String userID){
        Document source = new Source().ReadSource(sourceID);
        ArrayList comments =(ArrayList<Document>) source.get("comments");
        comments.add(new Document("_id", new ObjectId())
                .append("content", commentContent)
                .append("user_id", userID)
                .append("date", new Date().toString()));
        new Source().UpdateSourceList(sourceID,"comments", comments);
    }

    public void RemoveCommentFromSource( String sourceID,String commentID){
        Document source = new Source().ReadSource(sourceID);
        int counter = 0;
        ArrayList<Document> comments =(ArrayList<Document>) source.get("comments");
        for (Document comment:comments) {
            if(comment.get("_id").toString().equals(commentID)){
                comments.remove(counter);
                break;
            }
            else{
                counter++;
            }
        }
        new Source().UpdateSourceList(sourceID,"comments", comments);
    }

    public void EditCommentOnSource(String sourceID,String commentID, String newContent){
        Document source = new Source().ReadSource(sourceID);
        ArrayList<Document> comments =(ArrayList<Document>) source.get("comments");
        for (Document comment:comments) {
            if(comment.get("_id").toString().equals(commentID)){
                comment.remove("content");
                comment.append("content", newContent)
                        .append("lastEditDate", new Date().toString());
                break;
            }
        }
        new Source().UpdateSourceList(sourceID,"comments", comments);
    }
}
