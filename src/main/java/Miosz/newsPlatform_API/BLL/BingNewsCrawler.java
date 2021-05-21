package Miosz.newsPlatform_API.BLL;

import Miosz.newsPlatform_API.DAL.Category;
import Miosz.newsPlatform_API.DAL.News;
import Miosz.newsPlatform_API.DAL.Source;
import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class BingNewsCrawler {
    static String host = "http://crawler:8080";

    public static void SearchNews() throws Exception {

        List<String> markets = getMarkets();
        List<String> categories = getCategories();
        for (String market : markets){
            for (String category : categories){
                String endpoint = "/crawler/rest/api/" + market + "/category/" + category;
                System.out.println(host +""+ endpoint);
                URL url = new URL(host +""+ endpoint);
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                connection.setRequestMethod("GET");
                InputStream is =null;
                    try {
                        int code = connection.getResponseCode();
                        if (code != 404) {
                            is = connection.getInputStream();
                            BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
                            String jsonText = readAll(rd);
                            JSONObject json = new JSONObject(jsonText);
                            System.out.println("Looking at: " + url.toString());
                            JSONArray newsArray = json.getJSONArray("value");
                            for (Object newsObj: newsArray){
                                System.out.println("Found news: " + new JSONObject(newsObj.toString()).get("name"));
                                createNews(new JSONObject(newsObj.toString()), category);
                            }
                        }
                    }finally {
                        if(is != null) {
                            is.close();
                            connection.disconnect();
                        }
                    }
            }
        }
    }

    public static void createNews(JSONObject newsJson, String category) throws ParseException {
        String sourceID = new Source().ReadSourceIDWithName("Bing");
        ArrayList<String> categories= new ArrayList<>();
        categories.add( new Category().ReadCategoryIDWithName(category));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'");
        Date createDate= formatter.parse((String) newsJson.get("datePublished"));
        Document news = new News().CreateNews(newsJson.get("name").toString(),newsJson.get("url").toString(),sourceID,categories,newsJson.get("description").toString(),createDate.toString(), new Date().toString());

            Thread newThread = new Thread(() -> {
                try {
                    new newsScraper().scrapeNews(news.get("url").toString(), news.get("_id").toString());
                } catch (Exception e) {
                }
            });
            newThread.start();
    }

    public static List<String> getCategories(){
        List<Document> categoriesDocuments = new Category().ReadAllCategories();
        ArrayList<String> categories = new ArrayList<>();
        for (Document category:categoriesDocuments){
            categories.add(category.get("name").toString());
        }

        return categories;
    }

    public static List<String> getMarkets(){
        String[] marketsArray = {"en-GB","en-US","en-CA"};
        List<String> markets = Arrays.asList(marketsArray);

        return markets;
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
