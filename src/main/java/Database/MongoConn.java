package Database;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.internal.MongoDatabaseImpl;
import org.bson.Document;
import org.json.JSONObject;

import javax.print.Doc;
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
            MongoClientURI mongoClientURI= new MongoClientURI(uri);
            MongoClient mongoClient= new MongoClient(mongoClientURI);
            MongoDatabase mongoDatabase= mongoClient.getDatabase("VDC");
            MongoCollection mongoCollection=mongoDatabase.getCollection("masterData");
            Document document= new Document("name","Ritwik");
            document.append("age","21");
            mongoCollection.insertOne(document);
            System.out.println(mongoCollection);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
