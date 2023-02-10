package steps;

import api.ApiHeaders;
import api.ApiRequests;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import utils.JsonUtils;
import utils.PropertiesUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RestricaoStep extends ApiRequests {

    PropertiesUtils prop = new PropertiesUtils();
    JsonUtils jsonUtils = new JsonUtils();
    ApiHeaders apiHeaders = new ApiHeaders();

    @Dado("que possuo acesso a API de restricoes")
    public void quePossuoAcessoAAPIDeRestricoes() {
      // String uri = prop.getProp("url_resticoes");
       logInfo("Aqui chegou");
    }



    @Quando("envio um resquest de um CPF {string} com dados validos")
    public void envioUmResquestDeUmCPFComDadosValidos(String cpf) {
        super.url = prop.getProp("url_resticoes") + cpf;
        logInfo(url);
        super.headers = apiHeaders.headers();
        super.GET();
    }

    @Então("devo receber a mensagem informando situacao do CPF {string}")
    public void devoReceberAMensagemInformandoSituacaoDoCPF(String cpf) {
        assertEquals("O CPF "+cpf+" tem problema", response.jsonPath().getString("mensagem"),
                "Erro na comparação do Objeto.");
    }

    @E("o status code deve ser {int}")
    public void oStatusCodeDeveSer(int statusCode) {
        assertEquals(statusCode, response.statusCode(),
                "Status code diferente do esperado!!");
    }


}
