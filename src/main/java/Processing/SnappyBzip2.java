package Processing;
import org.xerial.snappy.Snappy;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Scanner;

public enum SnappyBzip2
{
    INSTANCE;
    static  byte[] compressed = new byte[0];

    public void Compress(String inputtext) throws IOException {
        String path= "/Users/ritwikgoel/Downloads/"+inputtext;
        String data = null;
        String finalData = "";
         compressed = new byte[0];
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
        byte[] uncompressed = Snappy.uncompress(compressed);
        String result = new String(uncompressed, "UTF-8");
        System.out.println(result);
        String outputName="/Users/ritwikgoel/Downloads/"+"Decompressed.txt";
        System.out.println(outputName);
        File outputFile = new File(outputName);

        Files.write(outputFile.toPath(), uncompressed);
    }



    public void Decompress(String input) throws Exception {
        String path= "/Users/ritwikgoel/Documents/Capstone/FileSystem/"+input;
        System.out.println(path);
        File file = new File(path);
        String finalData = "";
        String data = null;
        BufferedReader br
                = new BufferedReader(new FileReader(path));

        // Declaring a string variable
        String st;
        // Condition holds true till
        // there is character in a string
        while ((st = br.readLine()) != null)
        {
            finalData+=st;
        }
        System.out.println(finalData);
        System.out.println("Working till here");
        byte[] b = finalData.getBytes(Charset.forName("UTF-8"));

        byte[] uncompressed = Snappy.uncompress(compressed);
            String strNew = input.replace("_", "");

            String strNew2 = strNew.replace("LZ77", "");
            String strNew3 = strNew2.replace(".xz", "");
            String strNew4 = strNew3.replace("PACKITUP", "DECOMPRESSED_");


            String outputName="/Users/ritwikgoel/Documents/Capstone/FileSystem/"+strNew4;
        System.out.println(outputName);
            File outputFile = new File(outputName);

            Files.write(outputFile.toPath(), uncompressed);

        }
        //THis is for uncompressing the data
//        byte[] uncompressed = Snappy.uncompress(compressed);
//        String result = new String(uncompressed, "UTF-8");
//        System.out.println("This is a SnappyBzip2::");
//        System.out.println(result);
    }
