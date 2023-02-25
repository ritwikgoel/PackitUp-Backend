package Processing;

import org.xerial.snappy.Snappy;

import java.io.IOException;

public enum SnappyBzip2
{
    INSTANCE;

    public void Compress() throws IOException {
        String input = "Hello snappy-java! Snappy-java is a JNI-based wrapper of "
                + "Snappy, a fast compresser/decompresser.";
        byte[] compressed = Snappy.compress(input.getBytes("UTF-8"));
        byte[] uncompressed = Snappy.uncompress(compressed);

        String result = new String(uncompressed, "UTF-8");
        System.out.println("This is a SnappyBzip2::");
        System.out.println(result);
        //String result2 = new String(compressed, "UTF-8");
        //this is working
    }
}
