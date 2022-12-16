package vidsort;

import Processing.VideoCompress;
import org.apache.log4j.BasicConfigurator;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        BasicConfigurator.configure();
//        System.out.println("Hello World");
        VideoCompress.INSTANCE.compress();


    }
}