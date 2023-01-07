package api;

import org.json.JSONObject;
import io.restassured.response.Response;
import utils.LogUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ApiUtils extends LogUtils {  //todos os dados necessários para logar

    protected static Response response;
    protected static JSONObject body;
    protected static String url;
    protected static Map <String, String> headers = new HashMap<>();
    protected static Map <String, String> params = new HashMap<>();

    public void log(String verbo){
        super.logInfo("*************Dados Enviados no Request*************");
        super.logInfo(verbo + " " + url);
        super.logInfo("Body: \n" + body);
        super.logInfo("Headers: " + headers);
        super.logInfo("Params: " + params);

        super.logInfo("*******Dados Recebidos*******");
        super.logInfo("Status Code " + response.statusCode()); //status da requisição
        super.logInfo("Payload recebido: \n" + response.asPrettyString());//pega o retorno e loga como json
        super.logInfo("Tempo de resposta: " + response.timeIn(TimeUnit.MICROSECONDS));//tempo da requisição

    }
}
