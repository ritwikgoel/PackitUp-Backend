package vidsort;

import Database.MongoConn;
import Processing.VideoCompress;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import org.apache.log4j.BasicConfigurator;

import java.io.IOException;

public class Main extends AbstractVerticle {
    public static void main(String[] args) {
//        BasicConfigurator.configure();
        VertxOptions vertxOptions = new VertxOptions();
//        MongoConn mongoConn= new MongoConn();//Manually start the database to run
//        mongoConn.makeConnection();
        int eventLoopSize = vertxOptions.getEventLoopPoolSize();
        Vertx vertx = Vertx.vertx(new VertxOptions().setWorkerPoolSize(40));
        vertx.deployVerticle(HttpRouter.class.getName(),new DeploymentOptions().setWorkerPoolSize(20).setInstances(eventLoopSize));
    }
}
