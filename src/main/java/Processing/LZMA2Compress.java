package Processing;

import org.tukaani.xz.LZMA2Options;
import org.tukaani.xz.UnsupportedOptionsException;
import org.tukaani.xz.XZOutputStream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public enum LZMA2Compress {
    INSTANCE;
//XZ is a file format based on LZMA2 (an improved version of LZMA)
//
//The guys that invented the XZ format build a pure java implementation of the XZ archive compression / extraction algorithms
    public void Lzma2Compression(String videoinput) throws IOException {
        String path= "/Users/ritwikgoel/Downloads/"+videoinput;
        System.out.println(path );
        System.out.println("LZMA RUN AFTER RECHECKING");
        long bytes = Files.size(Paths.get(path));
        System.out.println(bytes/1024);
        long kb= (long) ((bytes/1024)*0.62);
        String outputName="/Users/ritwikgoel/Documents/Capstone/FileSystem/"+videoinput+"_LZMA("+kb+"kb)_"+".xz";//Add the ID name
        FileInputStream inFile = new FileInputStream(path);
        FileOutputStream outfile = new FileOutputStream(outputName);
        LZMA2Options options = new LZMA2Options();
        try {
            options.setPreset(7); // play with this number: 6 is default but 7 works better for mid sized archives ( > 8mb)
        } catch (UnsupportedOptionsException e) {
            throw new RuntimeException(e);
        }

        XZOutputStream out = new XZOutputStream(outfile, options);

        byte[] buf = new byte[8192];
        int size;
        while ((size = inFile.read(buf)) != -1)
            out.write(buf, 0, size);
        out.finish();
    }
}
