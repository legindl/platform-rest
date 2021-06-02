package Miosz.newsPlatform_API.BLL;

import java.net.HttpURLConnection;
import java.net.URL;

public class NLP {
    static String host = "http://nlp:8080";

    public void processNews(String newsID) throws Exception{
        String endpoint = "/nlp/rest/NLP/"+newsID;
        System.out.println("NLPing news");
        URL url = new URL(host +""+ endpoint);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        int code = connection.getResponseCode();
        System.out.println(code);
        connection.disconnect();
    }
}
