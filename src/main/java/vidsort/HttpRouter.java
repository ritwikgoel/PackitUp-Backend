package vidsort;
import Processing.*;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import java.io.IOException;
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
                VideoCompress.INSTANCE.compressFFMPEG(inputfilename); //Sending the input of the file; Can also send the ID here only
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
                    SnappyBzip2.INSTANCE.Decompress(inputfilename);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            else if(inputfilename.contains("FFMPEG")){
                System.out.println("It contains FFMPEG");
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