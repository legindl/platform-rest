package Miosz.newsPlatform_API.rest;

import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/create")
public class Create {
    private RestController rc = RestController.getInstance();

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String get(){
        System.out.println("eldo");
        return "<html><title>Hello</title><body><h1>ELO!!</h1><body></html>";

    }

    @POST
    @Path("/user")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response createUser(@FormDataParam("name") String name,
                               @FormDataParam("passwrd") String passwrd,
                               @FormDataParam("email") String email){
        if (name == "" || passwrd == "" || email == "")
        {
            System.out.println("FAIL");
            return Response.status(400).build();
        }
        rc.createUser(name, passwrd, email);
        return Response.ok().header("Access-Control-Allow-Origin", "*").build();
    }

    @POST
    @Path("/Category")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response createCategory(@FormDataParam("name") String name,
                               @FormDataParam("keywords") String keywords){
        if (name == "" || keywords == "")
        {
            System.out.println("FAIL");
            return Response.status(400).build();
        }
        rc.createCategory(name, keywords);
        return Response.ok().header("Access-Control-Allow-Origin", "*").build();
    }

    @POST
    @Path("/news")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response createNews(@FormDataParam("title") String title,
                               @FormDataParam("url") String url,
                               @FormDataParam("source") String source,
                               @FormDataParam("categories") String categories,
                               @FormDataParam("description") String description,
                               @FormDataParam("cDate") String cDate,
                               @FormDataParam("fDate") String fDate
                               ){
        if (title == "" || url == "" || source == ""|| categories == ""|| description == ""|| cDate == ""|| fDate == "")
        {
            System.out.println("FAIL");
            return Response.status(400).build();
        }
        rc.createNews(title, url, source, categories, description, cDate, fDate);
        return Response.ok().header("Access-Control-Allow-Origin", "*").build();
    }

    @POST
    @Path("/researchPaper")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response createResearchPaper(@FormDataParam("title") String title,
                               @FormDataParam("url") String url,
                               @FormDataParam("source") String source,
                               @FormDataParam("categories") String categories,
                               @FormDataParam("description") String description,
                               @FormDataParam("cDate") String cDate,
                               @FormDataParam("fDate") String fDate
    ){
        if (title == "" || url == "" || source == ""|| categories == ""|| description == ""|| cDate == ""|| fDate == "")
        {
            System.out.println("FAIL");
            return Response.status(400).build();
        }
        rc.createResearchPaper(title, url, source, categories, description, cDate, fDate);
        return Response.ok().header("Access-Control-Allow-Origin", "*").build();
    }

    @POST
    @Path("/source")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response createSource(@FormDataParam("name") String title,
                                        @FormDataParam("url") String url,
                                        @FormDataParam("description") String source,
                                        @FormDataParam("categories") String categories
    ){
        if (title == "" || url == "" || source == ""|| categories == "")
        {
            System.out.println("FAIL");
            return Response.status(400).build();
        }
        rc.createSource(title, url, source, categories);
        return Response.ok().header("Access-Control-Allow-Origin", "*").build();
    }

    @POST
    @Path("/target")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response createTarget(@FormDataParam("name") String title){
        if (title == "")
        {
            System.out.println("FAIL");
            return Response.status(400).build();
        }
        rc.createTarget(title);
        return Response.ok().header("Access-Control-Allow-Origin", "*").build();
    }

    @POST
    @Path("/valueIs")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response createValueIs(@FormDataParam("name") String title){
        if (title == "")
        {
            System.out.println("FAIL");
            return Response.status(400).build();
        }
        rc.createValueIs(title);
        return Response.ok().header("Access-Control-Allow-Origin", "*").build();
    }
}
