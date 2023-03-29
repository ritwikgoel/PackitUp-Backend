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
        router.post("/ffmpeg/:inputfilename/:id").handler(event->{
            String inputfilename = event.pathParam("inputfilename");
            String id = event.pathParam("id");
            HttpServerResponse response= event.response();
            try {
                VideoCompress.INSTANCE.compressFFMPEG(inputfilename,id); //Sending the input of the file; Can also send the ID here only
                event.response().end(" FFMPEF Video compressing done");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
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

        router.post("/upload/:path").handler(event->{
            String path = event.pathParam("path");
            System.out.println(path);
            HttpServerResponse response= event.response();
            try {
                //LZMA2Compress.INSTANCE.Lzma2Compression();
                //VideoCompress.INSTANCE.compressFFMPEG("input.mp4");
                //LZMA2Decompress.INSTANCE.Lzma2Decompression();
                SnappyBzip2.INSTANCE.Compress();
                HuffmanTextFles.INSTANCE.HuffmanTextFlesRunner();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            VideoCompress.INSTANCE.decider("this.mp3");
            event.response().end("Uploading with the link");
        });
        vertx.createHttpServer().requestHandler(router::accept).listen(8080);

    }
}