package Processing;

import org.tukaani.xz.BasicArrayCache;
import org.tukaani.xz.XZInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
public enum LZMA2Decompress {
    INSTANCE;
    public void Lzma2Decompression(String input) throws IOException {
        String from ="/Users/ritwikgoel/Documents/Capstone/FileSystem/"+input;
        String strNew = input.replace("_", "");

        String strNew2 = strNew.replace("LZMA", "");
        String strNew3 = strNew2.replace(".xz", "");
        String strNew4 = strNew3.replace("PACKITUP", "DECOMPRESSED_");

        String to = "/Users/ritwikgoel/Downloads/"+strNew4;
        try (FileInputStream fileStream = new FileInputStream(from);
             XZInputStream xzStream = new XZInputStream(fileStream, BasicArrayCache.getInstance())) {

            Files.copy(xzStream, Paths.get(to), StandardCopyOption.REPLACE_EXISTING);
        }
    }
}
