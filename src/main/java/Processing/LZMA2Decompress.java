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
    public void Lzma2Decompression() throws IOException {
        String from ="outputforlzma.xz";
        String to = "finalforLZMADecompress.mp4";
        try (FileInputStream fileStream = new FileInputStream(from);
             XZInputStream xzStream = new XZInputStream(fileStream, BasicArrayCache.getInstance())) {

            Files.copy(xzStream, Paths.get(to), StandardCopyOption.REPLACE_EXISTING);
        }
    }
}
