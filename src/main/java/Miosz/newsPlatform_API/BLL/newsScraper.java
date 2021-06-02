package Miosz.newsPlatform_API.BLL;

import Miosz.newsPlatform_API.DAL.News;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

public class newsScraper {
    static String host = "http://scraper:8080";

    public void scrapeNews(String newsUrl, String newsID) throws Exception{
        String encodedURL = newsUrl.replaceAll("/","%23");
        String endpoint = "/scraper/rest/scraper/" + newsID + "/"+ encodedURL;

        URL url = new URL(host +""+ endpoint);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        InputStream is =null;
        try {
            int code = connection.getResponseCode();
            if (code != 404) {
                is = connection.getInputStream();
                BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
                String jsonText = readAll(rd);
                JSONObject json = new JSONObject(jsonText);
                System.out.println("Scraping : " + url.toString());
                System.out.println(json);
                String newsId = json.getString("id");
                String newsContent = json.getString("content");
                updateNewsContent(newsId, newsContent);
            }
        }finally {
            if(is != null) {
                is.close();
                connection.disconnect();
            }
        }
    }

    private void updateNewsContent(String newsId, String newsContent) throws Exception {
        System.out.println("Updating content of: " + newsId);
        new News().UpdateNewsField(newsId,"content",newsContent);
        System.out.println("Starting NLP: " + newsId);
        new NLP().processNews(newsId);
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }
}
