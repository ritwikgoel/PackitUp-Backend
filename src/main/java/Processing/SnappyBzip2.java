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

    public void Compress(String inputtext) throws IOException {
        String path= "/Users/ritwikgoel/Downloads/"+inputtext;
        String data = null;
        String finalData = "";
        byte[] compressed = new byte[0];
        try {
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
                //System.out.println(data);
                finalData += data;
            }

            myReader.close();
            System.out.println(finalData); //working
            compressed = Snappy.compress(finalData.getBytes("UTF-8"));
            String outputName="/Users/ritwikgoel/Documents/Capstone/FileSystem/"+inputtext+"_LZ77_"+"_"+".txt";//Add the ID name
            File outputFile = new File(outputName);

            Files.write(outputFile.toPath(), compressed);

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        //THis is for uncompressing the data
//        byte[] uncompressed = Snappy.uncompress(compressed);
//        String result = new String(uncompressed, "UTF-8");
//        System.out.println("This is a SnappyBzip2::");
//        System.out.println(result);
    }



    public void Decompress(String input){
        //open a file and add it to a byte stream and then pass it to this function
        //THis is for uncompressing the data
//        byte[] uncompressed = Snappy.uncompress(compressed);
//        String result = new String(uncompressed, "UTF-8");
//        System.out.println("This is a SnappyBzip2::");
//        System.out.println(result);
    }
}
