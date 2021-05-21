package Miosz.newsPlatform_API.BLL;

import java.net.HttpURLConnection;
import java.net.URL;

public class NLP {
    static String host = "http://nlp:8080";

    public void processNews(String newsID) throws Exception{
        String endpoint = "/nlp/rest/NLP/"+newsID;

        URL url = new URL(host +""+ endpoint);
        url.openStream();
    }
}
