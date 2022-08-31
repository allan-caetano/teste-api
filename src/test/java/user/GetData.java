package user;

import io.cucumber.messages.internal.com.google.gson.Gson;
import org.json.JSONObject;
import org.json.XML;

public class GetData {

    private int userId;
    private int id;

    public int getUserId() {
        return userId;
    }

    public JSONObject getJson(){
        return new JSONObject((new Gson().toJson(this)));
    }

    public String getXml(){//para transformar xml em json
        return XML.toString(getJson());
    }
}