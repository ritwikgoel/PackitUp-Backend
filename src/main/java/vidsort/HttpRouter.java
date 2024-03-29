package vidsort;
import Database.MongoConn;
import Processing.*;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HttpRouter extends AbstractVerticle {
    @Override
    public void start() {
        Router router = Router.router(vertx);
        router.get("/health").handler(event -> {
            HttpServerResponse response = event.response();
            System.out.println(response);
            event.response().end("Ok");
        });
        //localhost:8080/ffmpeg/input.mp4/1
        router.post("/ffmpeg/:inputfilename").handler(event->{
            String inputfilename = event.pathParam("inputfilename");
            HttpServerResponse response= event.response();
            try {
                VideoCompress.INSTANCE.compressFFMPEG(inputfilename);
                //Sending the input of the file; Can also send the ID here only
                        MongoConn mongoConn= new MongoConn();//Manually start the database to run
        mongoConn.makeConnection(inputfilename);
                event.response().end(" FFMPEF Video compressing done");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        router.post("/download/:inputfilename").handler(event->{
            String inputfilename = event.pathParam("inputfilename");
            System.out.println(inputfilename);
            HttpServerResponse response= event.response();
            //                VideoCompress.INSTANCE.compressFFMPEG(inputfilename); //Sending the input of the file; Can also send the ID here only
            System.out.println("This is working on the donwloads post request");
            if(inputfilename.contains("LZMA")){
                System.out.println("It contains LZMA");
                try {
                    LZMA2Decompress.INSTANCE.Lzma2Decompression(inputfilename);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            } else if(inputfilename.contains("LZ77")){
                System.out.println("It contains LZ77");
                try {
                    System.out.println(inputfilename);
                    //SnappyBzip2.INSTANCE.Decompress(inputfilename);

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            else if(inputfilename.contains("FFMPEG")){
                System.out.println("It contains FFMPEG");
//                try {
//                    String path="/Users/ritwikgoel/Documents/Capstone/FileSystemVideo/" + "DECOMPRESSED_" + inputfilename;
//                    //Files.copy(Paths.get(path.substring(0,path.length() -12)), Paths.get("/Users/ritwikgoel/Downloads/"));
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }

            }
            event.response().end(" Downloading done above");
        });
        //localhost:8080/lzma/input.mp4/1
        router.post("/lzma/:inputfilename").handler(event->{
            //This is working
            String inputfilename = event.pathParam("inputfilename");
            HttpServerResponse response= event.response();
            try {
                LZMA2Compress.INSTANCE.Lzma2Compression(inputfilename);
                MongoConn mongoConn= new MongoConn();//Manually start the database to run
                mongoConn.makeConnection(inputfilename);
                event.response().end("LZMA File compressing done");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        //Do Decompression later
        //Do Decompression later
        //Do Decompression later

        //Post request for

        router.post("/lz77/:inputfilename").handler(event->{
            //This is working
            String inputfilename = event.pathParam("inputfilename");
            HttpServerResponse response= event.response();
            try {
                SnappyBzip2.INSTANCE.Compress(inputfilename);
                MongoConn mongoConn= new MongoConn();//Manually start the database to run
                mongoConn.makeConnection(inputfilename);
                event.response().end("LZ77 File compressing done");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });



        router.post("/upload/").handler(event->{
            HttpServerResponse response= event.response();
            //SnappyBzip2.INSTANCE.Compress();
            //HuffmanTextFles.INSTANCE.HuffmanTextFlesRunner();
            //VideoCompress.INSTANCE.decider("this.mp3");
            event.response().end("Uploading with the link");
        });
        vertx.createHttpServer().requestHandler(router::accept).listen(8080);

    }
}