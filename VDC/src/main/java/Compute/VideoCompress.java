package Compute;

import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.builder.FFmpegBuilder;

public enum VideoCompress {
    //for single ton class
    INSTANCE;
    FFmpegBuilder builder = new FFmpegBuilder()

            .setInput("input.mp4")
            .overrideOutputFiles(true)
            .addOutput("output.mp4")
            .setFormat("mp4")
            .disableSubtitle()

            .setAudioChannels(1)
            .setAudioCodec("aac")
            .setVideoCodec("libx264")
            .setVideoResolution(640, 480)
            .setStrict(FFmpegBuilder.Strict.EXPERIMENTAL)
            .done();



}
