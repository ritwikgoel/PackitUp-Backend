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
        String finalData = "";
        byte[] compressed = new byte[0];
        try {
            File myObj = new File("/Users/ritwikgoel/Desktop/input.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
                //System.out.println(data);
                finalData += data;
            }

            myReader.close();
            System.out.println(finalData); //working
            compressed = Snappy.compress(finalData.getBytes("UTF-8"));
            File outputFile = new File("/Users/ritwikgoel/Desktop/output.txt");
            Files.write(outputFile.toPath(), compressed);

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        byte[] uncompressed = Snappy.uncompress(compressed);
        String result = new String(uncompressed, "UTF-8");
        System.out.println("This is a SnappyBzip2::");
        System.out.println(result);
    }
}
