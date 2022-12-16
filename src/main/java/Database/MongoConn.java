package Database;

import io.vertx.core.json.Json;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MongoConn {
    static String readFile(String path, Charset encoding)
            throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    public void makeConnection(){
        try {
            String content = readFile("src/main/resources/development/config.json",Charset.defaultCharset());
            String jsonString = content ;
            JSONObject obj = new JSONObject(jsonString);
            String uri = obj.getJSONObject("configs").getString("uri");
            System.out.println(uri);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
