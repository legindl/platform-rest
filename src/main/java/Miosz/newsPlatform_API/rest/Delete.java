package Miosz.newsPlatform_API.rest;

import org.bson.Document;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/delete")
public class Delete {
    private RestController rc = RestController.getInstance();

    @DELETE
    @Path("user/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("id") String id){
        System.out.println("Removing User: " + id);
        if (id == "")
        {
            System.out.println("FAIL");
            return Response.status(400).build();
        }
        rc.deleteUser(id);
        return Response.ok().header("Access-Control-Allow-Origin", "*").build();
    }

    @DELETE
    @Path("category/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCategory(@PathParam("id") String id){
        System.out.println("Removing Catergory: " + id);
        if (id == "")
        {
            System.out.println("FAIL");
            return Response.status(400).build();
        }
        rc.deleteCategory(id);
        return Response.ok().header("Access-Control-Allow-Origin", "*").build();
    }

    @DELETE
    @Path("news/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteNews(@PathParam("id") String id){
        System.out.println("Removing News: " + id);
        if (id == "")
        {
            System.out.println("FAIL");
            return Response.status(400).build();
        }
        rc.deleteNews(id);
        return Response.ok().header("Access-Control-Allow-Origin", "*").build();
    }

    @DELETE
    @Path("researchPaper/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteResearchPaper(@PathParam("id") String id){
        System.out.println("Removing Research Paper: " + id);
        if (id == "")
        {
            System.out.println("FAIL");
            return Response.status(400).build();
        }
        rc.deleteResearchPaper(id);
        return Response.ok().header("Access-Control-Allow-Origin", "*").build();
    }

    @DELETE
    @Path("source/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteSource(@PathParam("id") String id){
        System.out.println("Removing Source: " + id);
        if (id == "")
        {
            System.out.println("FAIL");
            return Response.status(400).build();
        }
        rc.deleteSource(id);
        return Response.ok().header("Access-Control-Allow-Origin", "*").build();
    }

    @DELETE
    @Path("target/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteTarget(@PathParam("id") String id){
        System.out.println("Removing Target: " + id);
        if (id == "")
        {
            System.out.println("FAIL");
            return Response.status(400).build();
        }
        rc.deleteTarget(id);
        return Response.ok().header("Access-Control-Allow-Origin", "*").build();
    }

    @DELETE
    @Path("valueIs/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteValueIs(@PathParam("id") String id){
        System.out.println("Removing ValueIs: " + id);
        if (id == "")
        {
            System.out.println("FAIL");
            return Response.status(400).build();
        }
        rc.deleteValueIs(id);
        return Response.ok().header("Access-Control-Allow-Origin", "*").build();
    }

    @DELETE
    @Path("/newsComment")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response deleteNewsComment(@FormDataParam("newID") String newsID,
                                  @FormDataParam("commentID") String commentID){
        if (newsID == "" || commentID == "")
        {
            System.out.println("FAIL");
            return Response.status(400).build();
        }
        rc.deleteNewsComment(newsID, commentID);
        return Response.ok().header("Access-Control-Allow-Origin", "*").build();
    }

    @DELETE
    @Path("/sourceComment")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response deleteSourceComment(@FormDataParam("sourceID") String sourceID,
                                      @FormDataParam("commentID") String commentID){
        if (sourceID == "" || commentID == "")
        {
            System.out.println("FAIL");
            return Response.status(400).build();
        }
        rc.deleteSourceComment(sourceID, commentID);
        return Response.ok().header("Access-Control-Allow-Origin", "*").build();
    }

    @DELETE
    @Path("/alertRule")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response deleteAlertRule(@FormDataParam("userID") String userID,
                                        @FormDataParam("alertId") String alertID){
        if (userID == "" || alertID == "")
        {
            System.out.println("FAIL");
            return Response.status(400).build();
        }
        rc.deleteAlertRule(userID, alertID);
        return Response.ok().header("Access-Control-Allow-Origin", "*").build();
    }
}
