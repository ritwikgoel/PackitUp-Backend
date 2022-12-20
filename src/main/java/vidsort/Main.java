package vidsort;

import Database.MongoConn;
import Processing.VideoCompress;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import org.apache.log4j.BasicConfigurator;

import java.io.IOException;

public class Main extends AbstractVerticle {

    public static void main(String[] args) throws IOException {
        //BasicConfigurator.configure();
        Vertx vertx = Vertx.vertx(new VertxOptions().setWorkerPoolSize(40));
        VertxOptions vertxOptions = new VertxOptions();
        int eventLoopSize = vertxOptions.getEventLoopPoolSize();
        vertx.deployVerticle(HttpRouter.class.getName());
        //MongoConn mongoConn= new MongoConn();//Manually start the database to run
        //mongoConn.makeConnection();
        //VideoCompress.INSTANCE.compress();

    }
}