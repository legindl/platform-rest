package Miosz.newsPlatform_API.rest;

import org.bson.Document;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/read")
public class Read {
    private RestController rc = RestController.getInstance();

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String get(){
        System.out.println("elo");
        return "<html><title>Hello</title><body><h1>ELO!!</h1><body></html>";

    }

    @GET
    @Path("user/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") String id){
        System.out.println("Getting User: " + id);
        Document result = rc.getUserByID(id);

        if (result != null) {
            System.out.println("User: "+id+" found");
            return Response.ok(result.toJson(), MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*").build();
        } else {
            System.out.println("Error Occurred getting user: " + id);
            return Response.status(Response.Status.NOT_FOUND).header("Access-Control-Allow-Origin", "*").build();
        }
    }

    @GET
    @Path("category/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategory(@PathParam("id") String id){
        System.out.println("Getting Category:" + id);
        Document result = rc.getCategoryByID(id);

        if (result != null) {
            System.out.println("Category: "+id+" found");
            return Response.ok(result.toJson(), MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*").build();
        } else {
            System.out.println("Error Occurred getting Category");
            return Response.status(Response.Status.NOT_FOUND).header("Access-Control-Allow-Origin", "*").build();
        }
    }

    @GET
    @Path("category/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategories(){
        System.out.println("Getting all categories");
        List<String> result = rc.getAllCategories();

        if (result != null) {
            System.out.println("categories found");
            return Response.ok(result.toString(), MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*").build();
        } else {
            System.out.println("Error Occurred getting all categories");
            return Response.status(Response.Status.NOT_FOUND).header("Access-Control-Allow-Origin", "*").build();
        }
    }

    @GET
    @Path("news/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllNews(){
        System.out.println("Getting all News");
        List<String> result = rc.getAllNews();

        if (result != null) {
            System.out.println("News found");
            return Response.ok(result.toString(), MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*").build();
        } else {
            System.out.println("Error Occurred getting all News");
            return Response.status(Response.Status.NOT_FOUND).header("Access-Control-Allow-Origin", "*").build();
        }
    }

    @GET
    @Path("news/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNews(@PathParam("id") String id){
        System.out.println("Getting News:" + id);
        Document result = rc.getNewsByID(id);

        if (result != null) {
            System.out.println("News: "+id+" found");
            return Response.ok(result.toJson(), MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*").build();
        } else {
            System.out.println("Error Occurred getting News");
            return Response.status(Response.Status.NOT_FOUND).header("Access-Control-Allow-Origin", "*").build();
        }
    }

    @GET
    @Path("news/category/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNewsWithCategory(@PathParam("id") String id){
        System.out.println("Getting News:" + id);
        List<String> result = rc.getNewsWithCategory(id);

        if (result != null) {
            System.out.println("News: "+id+" found");
            return Response.ok(result.toString(), MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*").build();
        } else {
            System.out.println("Error Occurred getting News");
            return Response.status(Response.Status.NOT_FOUND).header("Access-Control-Allow-Origin", "*").build();
        }
    }

    @GET
    @Path("rpaper/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getResearchPaper(@PathParam("id") String id){
        System.out.println("Getting Research Paper:" + id);
        Document result = rc.getResearchPaperByID(id);

        if (result != null) {
            System.out.println("Research Paper: "+id+" found");
            return Response.ok(result.toJson(), MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*").build();
        } else {
            System.out.println("Error Occurred getting Research Paper");
            return Response.status(Response.Status.NOT_FOUND).header("Access-Control-Allow-Origin", "*").build();
        }
    }

    @GET
    @Path("rpaper/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllResearchPaper(){
        System.out.println("Getting all Research Papers");
        List<String> result = rc.getAllResearchPaper();

        if (result != null) {
            System.out.println("Research Papers found");
            return Response.ok(result.toString(), MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*").build();
        } else {
            System.out.println("Error Occurred getting all Research Papers");
            return Response.status(Response.Status.NOT_FOUND).header("Access-Control-Allow-Origin", "*").build();
        }
    }

    @GET
    @Path("source/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllSources(){
        System.out.println("Getting all Sources");
        List<String> result = rc.getAllSources();

        if (result != null) {
            System.out.println("Sources found");
            return Response.ok(result.toString(), MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*").build();
        } else {
            System.out.println("Error Occurred getting all Sources");
            return Response.status(Response.Status.NOT_FOUND).header("Access-Control-Allow-Origin", "*").build();
        }
    }

    @GET
    @Path("source/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSource(@PathParam("id") String id){
        System.out.println("Source:" + id);
        Document result = rc.getSourceByID(id);

        if (result != null) {
            System.out.println("Source: "+id+" found");
            return Response.ok(result.toJson(), MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*").build();
        } else {
            System.out.println("Error Occurred getting Source");
            return Response.status(Response.Status.NOT_FOUND).header("Access-Control-Allow-Origin", "*").build();
        }
    }

    @GET
    @Path("target/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTargets(){
        System.out.println("Getting all Sources");
        List<String> result = rc.getAllTargets();

        if (result != null) {
            System.out.println("Target found");
            return Response.ok(result.toString(), MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*").build();
        } else {
            System.out.println("Error Occurred getting all Targets");
            return Response.status(Response.Status.NOT_FOUND).header("Access-Control-Allow-Origin", "*").build();
        }
    }

    @GET
    @Path("target/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTarget(@PathParam("id") String id){
        System.out.println("Source:" + id);
        Document result = rc.getTarget(id);

        if (result != null) {
            System.out.println("Target: "+id+" found");
            return Response.ok(result.toJson(), MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*").build();
        } else {
            System.out.println("Error Occurred getting Target");
            return Response.status(Response.Status.NOT_FOUND).header("Access-Control-Allow-Origin", "*").build();
        }
    }

    @GET
    @Path("valueIs/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllValueIs(){
        System.out.println("Getting all ValueIs");
        List<String> result = rc.getAllValueIS();

        if (result != null) {
            System.out.println("ValueIs found");
            return Response.ok(result.toString(), MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*").build();
        } else {
            System.out.println("Error Occurred getting all ValueIs");
            return Response.status(Response.Status.NOT_FOUND).header("Access-Control-Allow-Origin", "*").build();
        }
    }

    @GET
    @Path("valueIs/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getValueIs(@PathParam("id") String id){
        System.out.println("ValueIs:" + id);
        Document result = rc.getValueIS(id);

        if (result != null) {
            System.out.println("ValueIs: "+id+" found");
            return Response.ok(result.toJson(), MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*").build();
        } else {
            System.out.println("Error Occurred getting ValueIs");
            return Response.status(Response.Status.NOT_FOUND).header("Access-Control-Allow-Origin", "*").build();
        }
    }
}
