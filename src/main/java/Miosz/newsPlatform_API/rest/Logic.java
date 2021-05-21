package Miosz.newsPlatform_API.rest;

import jersey.repackaged.com.google.common.base.Stopwatch;
import org.bson.Document;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Path("/logic")
public class Logic {
    private RestController rc = RestController.getInstance();

    @GET
    @Path("Bing")
    @Produces(MediaType.TEXT_PLAIN)
    public Response startBingCrawler(){
        System.out.println("Starting Crawler: " + new Date());
        Stopwatch stopwatch = Stopwatch.createStarted();
        rc.startBingCrawler();
        stopwatch.stop();
        return Response.ok("It took "+stopwatch.elapsed(TimeUnit.MILLISECONDS)+"ms", MediaType.TEXT_PLAIN).header("Access-Control-Allow-Origin", "*").build();

    }
}
