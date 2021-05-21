package Miosz.newsPlatform_API.rest;

import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/update")
public class Update {
    private RestController rc = RestController.getInstance();

    @PUT
    @Path("/newsComment")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCommentOnNews(@FormDataParam("newsID") String newsID,
                                     @FormDataParam("content") String commentContent,
                                     @FormDataParam("userID") String userID){
        System.out.println("Creating comment on news: " + newsID);

        if (newsID == "" || commentContent == "" || userID == "")
        {
            System.out.println("FAIL");
            return Response.status(400).build();
        }
        rc.addCommentOnNews(newsID,commentContent,userID);
        return Response.ok().header("Access-Control-Allow-Origin", "*").build();
    }

    @PUT
    @Path("/update/newsComment")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCommentOnNews(@FormDataParam("newsID") String newsID,
                                     @FormDataParam("commentID") String commentID,
                                     @FormDataParam("content") String newContent){
        System.out.println("Updating comment on news: " + newsID);

        if (newsID == "" || commentID == "" || newContent == "")
        {
            System.out.println("FAIL");
            return Response.status(400).build();
        }
        rc.updateCommentOnNews(newsID,commentID,newContent);
        return Response.ok().header("Access-Control-Allow-Origin", "*").build();
    }

    @PUT
    @Path("/sourceComment")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCommentOnSource(@FormDataParam("sourceID") String sourceID,
                                     @FormDataParam("content") String commentContent,
                                     @FormDataParam("userID") String userID){
        System.out.println("Creating comment on news: " + sourceID);

        if (sourceID == "" || commentContent == "" || userID == "")
        {
            System.out.println("FAIL");
            return Response.status(400).build();
        }
        rc.addCommentOnSource(sourceID,commentContent,userID);
        return Response.ok().header("Access-Control-Allow-Origin", "*").build();
    }

    @PUT
    @Path("/update/sourceComment")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCommentOnSource(@FormDataParam("sourceID") String sourceID,
                                        @FormDataParam("commentID") String commentID,
                                        @FormDataParam("content") String newContent){
        System.out.println("Updating comment on news: " + sourceID);

        if (sourceID == "" || commentID == "" || newContent == "")
        {
            System.out.println("FAIL");
            return Response.status(400).build();
        }
        rc.updateCommentOnSource(sourceID,commentID,newContent);
        return Response.ok().header("Access-Control-Allow-Origin", "*").build();
    }

    @PUT
    @Path("/correctnessCheck")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCorrectnessCheckOnNews(@FormDataParam("newsID") String newsID,
                                        @FormDataParam("researchPapers") String researchPapers,
                                        @FormDataParam("Grade") String Grade){
        System.out.println("Updating correctness check on news: " + newsID);

        if (newsID == "" || researchPapers == "" || Grade == "")
        {
            System.out.println("FAIL");
            return Response.status(400).build();
        }
        rc.updateCorrectnessCheck(newsID,researchPapers,Grade);
        return Response.ok().header("Access-Control-Allow-Origin", "*").build();
    }

    @PUT
    @Path("/alertRule/news")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addAlertNews(@FormDataParam("userID") String userID,
                                        @FormDataParam("newsID") String newsID,
                                        @FormDataParam("targetID") String targetID,
                                        @FormDataParam("valueIsID") String valueIsID,
                                        @FormDataParam("value") String value){
        System.out.println("Updating alert Rule on news: " + newsID);

        if (userID == "" || newsID == "" || targetID == "" || valueIsID == "" || value == "")
        {
            System.out.println("FAIL");
            return Response.status(400).build();
        }
        rc.updateAlertRuleNews(userID,newsID,targetID,valueIsID,value);
        return Response.ok().header("Access-Control-Allow-Origin", "*").build();
    }

    @PUT
    @Path("/alertRule/category")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addAlertCategory(@FormDataParam("userID") String userID,
                                        @FormDataParam("newsID") String categoryID,
                                        @FormDataParam("targetID") String targetID,
                                        @FormDataParam("valueIsID") String valueIsID,
                                        @FormDataParam("value") String value){
        System.out.println("Updating correctness check on news: " + categoryID);

        if (userID == "" || categoryID == "" || targetID == "" || valueIsID == "" || value == "")
        {
            System.out.println("FAIL");
            return Response.status(400).build();
        }
        rc.updateAlertRuleCategory(userID,categoryID,targetID,valueIsID,value);
        return Response.ok().header("Access-Control-Allow-Origin", "*").build();
    }


}
