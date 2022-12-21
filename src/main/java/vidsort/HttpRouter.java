package vidsort;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;

public class HttpRouter extends AbstractVerticle {
    @Override
    public void start() {

        Router router = Router.router(vertx);

        router.route().handler(ctx -> {

            // This handler will be called for every request
            HttpServerResponse response = ctx.response();
            response.putHeader("content-type", "text/plain");

            // Write to the response and end it
            response.end("Hello World from Vert.njknjx-Web!");
        });
        router.get("/health").handler(event -> {
            event.response().end("Ok");
        });
        vertx.createHttpServer().requestHandler(router::accept).listen(8080);

    }
}