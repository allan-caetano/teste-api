package api;

import java.util.HashMap;
import java.util.Map;

public class ApiHeaders {

    Map<String, String> headers = new HashMap<>();

    public Map<String, String> headers() {

        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/json");
        return headers;
        }
}
