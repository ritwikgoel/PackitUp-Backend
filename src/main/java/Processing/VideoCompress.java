package Processing;
import io.vertx.core.Vertx;
import io.vertx.rxjava.ext.web.Router;
import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;

import java.io.IOException;

public enum VideoCompress {
    INSTANCE;
//Send path here. Can send database path or File server. File server makes sense. Database will contain the address of the videos
    public void compressFFMPEG() throws IOException {
        String path= "/Users/ritwikgoel/Downloads/outputs/input.mp4";
        System.out.println(path.substring(path.length()-5));
        FFmpeg ffmpeg = new FFmpeg("/opt/homebrew/bin/ffmpeg");//path manually
        FFprobe ffprobe = new FFprobe("/opt/homebrew/bin/ffprobe");//manual path
        FFmpegBuilder builder = new FFmpegBuilder()
                .setInput(path)//manual path as of now
                .overrideOutputFiles(true) // Override the output if it exists
                .addOutput("outputFFMEG.mp4")   // Filename for the destination
                .setFormat("mp4")        // Format is inferred from filename, or can be set
                .disableSubtitle()       // No subtiles
                .setAudioChannels(1)         // Mono audio
                .setAudioCodec("aac")        // using the aac codec
                .setAudioSampleRate(48_000)  // at 48KHz
                .setVideoCodec("libx264")     // Video using x264
                .setVideoFrameRate(24, 1)     // at 24 frames per second
                .setVideoResolution(640, 480) // at 640x480 resolution
                .setStrict(FFmpegBuilder.Strict.EXPERIMENTAL) // Allow FFmpeg to use experimental specs
                .done();
        FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);
// Run a one-pass encode
        executor.createJob(builder).run();
// Or run a two-pass encode (which is better quality at the cost of being slower)
        // executor.createTwoPassJob(builder).run();
    }
    public void decider(String path){
        if(path.substring(path.length()-4).contains("mp3")){
            //mp3 file over here
            System.out.println("This is a mp3");
        }else if(path.substring(path.length()-4).contains("mp4")){
            //mp4
            System.out.println("This is a mp4");
        }else if(path.substring(path.length()-4).contains("pdf")){
            //pdf
            System.out.println("This is a pdf");
        }else if(path.substring(path.length()-4).contains("jpg")){
            //jpg
            System.out.println("This is a jpg");
        }else if(path.substring(path.length()-4).contains("jpeg")){
            //jpeg
            System.out.println("This is a jpeg");
        }
    }

}
