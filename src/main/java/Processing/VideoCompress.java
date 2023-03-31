package Processing;
import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;

import java.io.IOException;

public enum VideoCompress {
    INSTANCE;
//Send path here. Can send database path or File server. File server makes sense. Database will contain the address of the videos
    public void compressFFMPEG(String videoinput) throws IOException {
        String path= "/Users/ritwikgoel/Downloads/"+videoinput;
        System.out.println(path);
        FFmpeg ffmpeg = new FFmpeg("/opt/homebrew/bin/ffmpeg");//path manually
        FFprobe ffprobe = new FFprobe("/opt/homebrew/bin/ffprobe");//manual path
        //Changing the file name in accordance with input name + ID of the USER
        //String outputName="/Users/ritwikgoel/Documents/Capstone/FileSystem/"+"ffmpeg_"+videoinput+"_"+".mp4";//Add the ID name
        String outputName="/Users/ritwikgoel/Documents/Capstone/FileSystem/"+videoinput+"_FFMPEG_"+".mp4";//Add the ID name
        FFmpegBuilder builder = new FFmpegBuilder()
                .setInput(path)
                .overrideOutputFiles(true)
                .addOutput(outputName)
                .setFormat("mp4")
                .disableSubtitle()
                .setAudioChannels(1)
                .setAudioCodec("aac")
                .setAudioSampleRate(48_000)
                .setVideoCodec("libx264")
                .setVideoFrameRate(24, 1)
                .setVideoResolution(640, 480)
                .setStrict(FFmpegBuilder.Strict.EXPERIMENTAL)
                .done();
        FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);
        executor.createJob(builder).run();
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
