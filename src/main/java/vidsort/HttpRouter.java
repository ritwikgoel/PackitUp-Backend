package vidsort;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;

public class HttpRouter extends AbstractVerticle {

    public void start() throws Exception {
        super.start();
        HttpServer server = vertx.createHttpServer();
        Router router = Router.router(vertx);

        router.route().handler(ctx -> {
            // This handler will be called for every request
            HttpServerResponse response = ctx.response();
            ((HttpServerResponse) response).putHeader("content-type", "text/plain");

            // Write to the response and end it
            response.end("Hello World from Vert.x-Web!");
        });

        server.requestHandler(router).listen(8080);

    }

}
