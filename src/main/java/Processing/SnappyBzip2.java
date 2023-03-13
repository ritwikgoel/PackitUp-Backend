package Processing;

import org.xerial.snappy.Snappy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

public enum SnappyBzip2
{
    INSTANCE;

    public void Compress() throws IOException {
        String data = null;
        try {
            File myObj = new File("/Users/ritwikgoel/Downloads/input.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
                //System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        String input = "Hello snappy-java! Snappy-java is a JNI-based wrapper of "
                + "Snappy, a fast compresser/decompresser.";
        byte[] compressed = Snappy.compress(data.getBytes("UTF-8"));
        File outputFile = new File("/Users/ritwikgoel/Downloads/output.txt");
        Files.write(outputFile.toPath(), compressed);

        byte[] uncompressed = Snappy.uncompress(compressed);
        String result = new String(uncompressed, "UTF-8");
        System.out.println("This is a SnappyBzip2::");
        //System.out.println(result);
        //String result2 = new String(compressed, "UTF-8");
        //this is working
    }
}
