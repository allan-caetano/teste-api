package utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonUtils {

    public JSONObject parseJSONFile() throws IOException, JSONException{
        String content = new String(Files.readAllBytes(Paths.get("utils/createUser.json")));
        return new JSONObject(content);
    }
}
