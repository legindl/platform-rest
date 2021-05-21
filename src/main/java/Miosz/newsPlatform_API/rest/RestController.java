package Miosz.newsPlatform_API.rest;

import Miosz.newsPlatform_API.BLL.BingNewsCrawler;
import Miosz.newsPlatform_API.DAL.*;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RestController {
    private static RestController instance;

    public static RestController getInstance() {
        if (instance == null) {
            instance = new RestController();
        }
        return instance;
    }

    public Document getUserByID(String id) {
        System.out.println("Getting user document");
        Document user = new User().ReadUserInfo(id);
        return user;
    }

    public List<String> getAllCategories() {
        System.out.println("Getting all categories list");
        List<Document> categories = new Category().ReadAllCategories();
        ArrayList<String> categoriesJSON = new ArrayList<>();
        for (Document category : categories){
            categoriesJSON.add(category.toJson());
        }
        return categoriesJSON;
    }

    public Document getCategoryByID(String id) {
        System.out.println("Getting category document");
        Document category = new Category().ReadCategory(id);
        return category;
    }

    public List<String> getAllNews(){
        System.out.println("Getting all News list");
        List<Document> newsList = new News().ReadAllNews();
        ArrayList<String> newsJSON = new ArrayList<>();
        for (Document news : newsList){
            newsJSON.add(news.toJson());
        }
        return newsJSON;
    }

    public Document getNewsByID(String id) {
        System.out.println("Getting News document");
        Document news = new News().ReadNews(id);
        return news;
    }

    public List<String> getNewsWithCategory(String id) {
        System.out.println("Getting all News list");
        List<Document> newsList = new News().ReadAllNewsFromCategory(id);
        ArrayList<String> newsJSON = new ArrayList<>();
        for (Document news : newsList){
            newsJSON.add(news.toJson());
        }
        return newsJSON;
    }

    public Document getResearchPaperByID(String id) {
        System.out.println("Getting ResearchPaper");
        Document researchPaper = new ResearchPaper().ReadResearchPaper(id);
        return researchPaper;
    }

    public List<String> getAllResearchPaper() {
        System.out.println("Getting all ResearchPaper list");
        List<Document> researchPapers = new ResearchPaper().ReadAllResearchPapers();
        ArrayList<String> researchPapersJSON = new ArrayList<>();
        for (Document researchPaper : researchPapers){
            researchPapersJSON.add(researchPaper.toJson());
        }
        return researchPapersJSON;
    }

    public Document getSourceByID(String id) {
        System.out.println("Getting Source");
        Document source = new Source().ReadSource(id);
        return source;
    }

    public List<String> getAllSources() {
        System.out.println("Getting all ResearchPaper list");
        List<Document> sources = new Source().ReadAllSources();
        ArrayList<String> sourceJSON = new ArrayList<>();
        for (Document source : sources){
            sourceJSON.add(source.toJson());
        }
        return sourceJSON;
    }

    public Document getTarget(String id) {
        System.out.println("Getting Source");
        Document target = new Target().ReadTarget(id);
        return target;
    }

    public List<String> getAllTargets() {
        System.out.println("Getting all ResearchPaper list");
        List<Document> targets = new Target().ReadAllTArgets();
        ArrayList<String> targetsJSON = new ArrayList<>();
        for (Document target : targets){
            targetsJSON.add(target.toJson());
        }
        return targetsJSON;
    }

    public List<String> getAllValueIS() {
        System.out.println("Getting all ResearchPaper list");
        List<Document> valueIsList = new ValueIs().ReadAllValueIs();
        ArrayList<String> valueIsJSON = new ArrayList<>();
        for (Document valueIs : valueIsList){
            valueIsJSON.add(valueIs.toJson());
        }
        return valueIsJSON;
    }

    public Document getValueIS(String id) {
        System.out.println("Getting Source");
        Document valueIs = new ValueIs().ReadValueIs(id);
        return valueIs;
    }

    public void createUser(String name, String passwrd, String email) {
        new User().CreateUser(name, passwrd, email);
    }

    public void createNews(String title, String url, String source, String categoriesString, String description, String cDate, String fDate) {
        List<String> categories = Arrays.asList(categoriesString.split(","));
        new News().CreateNews(title, url, source, categories, description, cDate, fDate);
    }

    public void createResearchPaper(String title, String url, String source, String categoriesString, String description, String cDate, String fDate) {
        List<String> categories = Arrays.asList(categoriesString.split(","));
        new ResearchPaper().CreateResearchPaper(title,url, source, categories, description, cDate, fDate);
    }

    public void createCategory(String name, String keywords) {
        List<String> keywordList = Arrays.asList(keywords.split(","));
        new Category().CreateCategory(name,keywordList);
    }

    public void createSource(String title, String url, String source, String categoriesString) {
        List<String> categories = Arrays.asList(categoriesString.split(","));
        new Source().CreateSource(title,url,source,categories);
    }

    public void createTarget(String title) {
        new Target().AddTarget(title);
    }

    public void createValueIs(String title) {
        new ValueIs().AddValueIs(title);
    }

    public void deleteUser(String id) {
        new User().DeleteUser(id);
    }

    public void deleteCategory(String id) {
        new Category().DeleteCategory(id);
    }

    public void deleteNews(String id) {
        new News().DeleteNews(id);
    }

    public void deleteResearchPaper(String id) {
        new ResearchPaper().DeleteResearchPaper(id);
    }

    public void deleteSource(String id) {
        new Source().DeleteSource(id);
    }

    public void deleteTarget(String id) {
        new Target().DeleteTarget(id);
    }

    public void deleteValueIs(String id) {
        new ValueIs().DeleteValueIs(id);
    }

    public void deleteNewsComment(String newsID, String commentID) {
        new NewsComment().RemoveCommentFromNews(newsID,commentID);
    }

    public void deleteSourceComment(String sourceID, String commentID) {
        new SourceComment().RemoveCommentFromSource(sourceID,commentID);
    }

    public void deleteAlertRule(String userID, String alertID) {
        new AlertRule().RemoveAlertRule(userID,alertID);
    }

    public void addCommentOnNews(String newsID, String commentContent, String userID) {
        new NewsComment().AddCommentToNews(newsID,commentContent,userID);
    }

    public void updateCommentOnNews(String newsID, String commentID, String newContent) {
        new NewsComment().EditCommentOnNews(newsID, commentID, newContent);
    }

    public void addCommentOnSource(String sourceID, String commentContent, String userID) {
        new SourceComment().AddCommentToSource(sourceID, commentContent, userID);
    }

    public void updateCommentOnSource(String sourceID, String commentID, String newContent) {
        new SourceComment().EditCommentOnSource(sourceID, commentID, newContent);
    }

    public void updateCorrectnessCheck(String newsID, String researchPapers, String grade) {
        List<String> researchPapersList = Arrays.asList(researchPapers.split(","));
        new CorrectnessCheck().UpdateCorrectnessCheck(newsID,researchPapersList,Double.parseDouble(grade));
    }

    public void updateAlertRuleNews(String userID, String newsID, String targetID, String valueIsID, String value) {
        new AlertRule().CreateAlertRuleNews(userID,newsID,targetID,valueIsID,Double.parseDouble(value));
    }

    public void updateAlertRuleCategory(String userID, String categoryID, String targetID, String valueIsID, String value) {
        new AlertRule().CreateAlertRuleCategory(userID,categoryID,targetID,valueIsID,Double.parseDouble(value));
    }

    public void startBingCrawler() {
        try {
            new BingNewsCrawler().SearchNews();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
