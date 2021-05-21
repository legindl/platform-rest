package Miosz.newsPlatform_API.rest;

import org.bson.Document;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;

@Path("/logic")
public class Logic {
    private RestController rc = RestController.getInstance();

    @GET
    @Path("Bing")
    @Produces(MediaType.TEXT_PLAIN)
    public Response startBingCrawler(){
        System.out.println("Starting Crawler: " + new Date());
        rc.startBingCrawler();

            return Response.ok(MediaType.TEXT_PLAIN).header("Access-Control-Allow-Origin", "*").build();

    }
}
