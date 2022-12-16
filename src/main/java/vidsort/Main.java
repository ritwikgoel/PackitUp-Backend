package vidsort;

import Database.MongoConn;
import Processing.VideoCompress;
import org.apache.log4j.BasicConfigurator;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        BasicConfigurator.configure();
        MongoConn mongoConn= new MongoConn();
        mongoConn.makeConnection();

        //VideoCompress.INSTANCE.compress();

    }
}