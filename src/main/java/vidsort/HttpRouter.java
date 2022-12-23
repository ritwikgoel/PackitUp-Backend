package vidsort;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;

public class HttpRouter extends AbstractVerticle {
    @Override
    public void start() {

        Router router = Router.router(vertx);
        router.get("/").handler(event -> {
            event.response().setChunked(true).end("Home page");
        });
        router.get("/health").handler(event -> {
            event.response().end("Ok");
        });
        vertx.createHttpServer().requestHandler(router::accept).listen(8080);

    }
}