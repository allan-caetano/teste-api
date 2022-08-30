package user;

import com.github.javafaker.IdNumber;
import io.cucumber.messages.internal.com.google.gson.Gson;
import org.json.JSONObject;
import org.json.XML;

public class GetPlace {

    private int userId;
    private String title;
    private String body;
    private int id;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getId(IdNumber idNumber) {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public JSONObject getJson(){
        return new JSONObject((new Gson().toJson(this)));
    }

    public String getXml(){//para transformar xml em json
        return XML.toString(getJson());
    }
}