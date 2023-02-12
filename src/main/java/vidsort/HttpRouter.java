package vidsort;

import Processing.VideoCompress;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.FileUpload;
import io.vertx.ext.web.Router;

import java.io.IOException;
import java.util.Set;

public class HttpRouter extends AbstractVerticle {
    @Override
    public void start() {
        Router router = Router.router(vertx);
        router.get("/").handler(event -> {
            event.response().setChunked(true).end("Home page");
        });
        router.get("/health").handler(event -> {
            HttpServerResponse response = event.response();
            System.out.println(response);
            event.response().end("Ok");
        });
        //This processing is for FFMPEG compression
        router.get("/processing").handler(event->{
            HttpServerResponse response= event.response();
            try {
                VideoCompress.INSTANCE.compressFFMPEG();
                event.response().end("Video compressing done");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        router.post("/upload/:path").handler(event->{
            String path = event.pathParam("path");
            System.out.println(path);
            HttpServerResponse response= event.response();
            VideoCompress.INSTANCE.decider("this.mp3");
            event.response().end("Uploading with the link");
        });
        vertx.createHttpServer().requestHandler(router::accept).listen(8080);
        //Handling file uploads- PART OF VERTX
        //You can use the context data in the
        //RoutingContext
        // to maintain any data that you want to share between handlers for the lifetime of the request.
        /*
        * Blocking Handler:
        * router.post("/some/endpoint").handler(ctx -> {
  ctx.request().setExpectMultipart(true);
  ctx.next();
}).blockingHandler(ctx -> {
  // ... Do some blocking operation
});
        * */

        /*
        * Path Parameters:
        * router
  .route(HttpMethod.POST, "/catalogue/products/:productType/:productID/")
  .handler(ctx -> {

    String productType = ctx.pathParam("productType");
    String productID = ctx.pathParam("productID");

    // Do something with them...
  });
        *
        * */

        /*
        Can do POST and GET requests
        * router.route(HttpMethod.PUT, "myapi/orders")
  .consumes("application/json")
  .produces("application/json")
  .handler(ctx -> {

    // This would be match for any PUT method to paths starting
    // with "myapi/orders" with a content-type of "application/json"
    // and an accept header matching "application/json"

  });

        *
        * */
    }
}