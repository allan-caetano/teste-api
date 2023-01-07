package steps;

import api.ApiHeaders;
import api.ApiRequests;
import com.github.javafaker.Faker;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.cucumber.messages.internal.com.google.gson.Gson;
import org.json.JSONObject;

import org.junit.Assert;
import user.Users;
import utils.JsonUtils;
import utils.PropertiesUtils;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimulacaoStep extends ApiRequests {

    private final PropertiesUtils propertiesUtils = new PropertiesUtils();

    PropertiesUtils prop = new PropertiesUtils();
    JsonUtils jsonUtils = new JsonUtils();
    ApiHeaders apiHeaders = new ApiHeaders();
    Faker faker = new Faker();
    Users userEnvio;

    @Dado("que possuo acesso a API de Simulacoes")
    public void quePossuoAcessoAAPIDeSimulacoes() {
        String uri = prop.getProp("url_simulacao");
        logInfo(uri);
    }

    @Quando("envio um resquest para o recurso correto")
    public void envioUmResquestParaORecursoCorreto() {
        url = prop.getProp("url_simulacao");
        headers = apiHeaders.headers();
        super.GET();
    }

    @Então("devo receber uma lista de simulacoes cadastradas")
    public void devoReceberUmaListaDeSimulacoesCadastradas() {
        Assert.assertTrue(response.jsonPath().getList("id").size() >= 3);
        logInfo("Lista de IDS: "+response.jsonPath().getString("id"));
    }

    @Quando("envio um resquest para API simulacoes com CPF {string}")
    public void envioUmResquestParaAPISimulacoesComCPF(String cpf) throws IOException {
        super.url = prop.getProp("url_simulacao") + propertiesUtils.obterNumero();
        super.headers = apiHeaders.headers();
        super.GET();
        logInfo(propertiesUtils.obterNumero());
    }

    @Então("retorna a simulação feita para o CPF {string}")
    public void retornaASimulaçãoFeitaParaOCPF(String cpf) throws IOException {
        assertEquals(propertiesUtils.obterNumero(), response.jsonPath().getString("cpf"),
                "Erro na comparação do Objeto.");
    }

    @Dado("que possuo acesso a API de Simulacoes para criar uma simulacao")
    public void quePossuoAcessoAAPIDeSimulacoesParaCriarUmaSimulacao() {
        String uri = prop.getProp("url_simulacao");
        logInfo(uri);
    }

    @Quando("envio um resquest para criacao de uma simulacao")
    public void envioUmResquestParaCriacaoDeUmaSimulacao() throws IOException {
        super.url = prop.getProp("url_simulacao");
        super.headers = apiHeaders.headers();
        userEnvio = Users.builder()
                .nome(faker.name().fullName())
                .cpf(faker.number().digits(11))
                .email(jsonUtils.parseJSONFile().getString("email"))
               // .email(faker.internet().emailAddress())
                .valor(1200.00)
                .parcelas(3)
                .seguro(true)
                .build();

        super.body = new JSONObject((new Gson().toJson(userEnvio)));
        super.POST();
    }

    @Então("retorna a simulação com os dados cadastrados")
    public void retornaASimulaçãoComOsDadosCadastrados() {
        Assert.assertEquals(userEnvio.getCpf(), response.jsonPath().get("cpf"));
        Assert.assertEquals(userEnvio.getNome(), response.jsonPath().get("nome"));
        logInfo("CPF Cadastrado: " + response.jsonPath().get("cpf"));
        propertiesUtils.guardarCPFGerado(response.jsonPath().get("cpf"));
    }

    @Então("retorna a simulação com os a mensagem de erro")
    public void retornaASimulaçãoComOsAMensagemDeErro() {
        assertEquals("CPF não pode ser vazio", response.jsonPath().get("erros.cpf"),
                "Mensagem error");
    }

    @Dado("que possuo acesso a API de Simulacoes para alterar uma simulacao existente")
    public void quePossuoAcessoAAPIDeSimulacoesParaAlterarUmaSimulacaoExistente() {
        super.url = prop.getProp("url_simulacao");
        logInfo(url);
    }

    @Quando("envio um resquest para alteracao de uma simulacao passando CPF {string} existente")
    public void envioUmResquestParaAlteracaoDeUmaSimulacaoPassandoCPFExistente(String cpf) {
        super.url = prop.getProp("url_simulacao") + cpf;
        super.headers = apiHeaders.headers();
        userEnvio = Users.builder()
                .nome(faker.name().fullName())
                .cpf("66414919004")
                .email("xpto-auto@email.com")
                .valor(1200.00)
                .parcelas(3)
                .seguro(true)
                .build();
        super.body = new JSONObject((new Gson().toJson(userEnvio)));
        super.PUT();
    }

    @Então("retorna a simulação com os dados alterados")
    public void retornaASimulaçãoComOsDadosAlterados() {
        String emailNovo = userEnvio.getEmail();
        Assert.assertEquals(userEnvio.getEmail(), response.jsonPath().get("email"));
        System.out.println(emailNovo);
    }

    @Dado("que possuo acesso a API de Simulacoes para deletar uma simulacao existente")
    public void quePossuoAcessoAAPIDeSimulacoesParaDeletarUmaSimulacaoExistente() {
        super.url = prop.getProp("url_simulacao");
        logInfo(url);
    }

    @Quando("envio um resquest para deletar uma simulacao passando CPF {string} existente")
    public void envioUmResquestParaDeletarUmaSimulacaoPassandoCPFExistente(String cpf) {
        super.url = prop.getProp("url_simulacao") + cpf;
        super.headers = apiHeaders.headers();
        super.body = new JSONObject();
        super.DELETE();
    }

    @Quando("envio um resquest para criacao de uma simulacao sem o CPF")
    public void envioUmResquestParaCriacaoDeUmaSimulacaoSemOCPF() {
        super.url = prop.getProp("url_simulacao");
        super.headers = apiHeaders.headers();
        userEnvio = Users.builder()
                .nome(faker.name().fullName())
                .email(faker.internet().emailAddress())
                .valor(1200.00)
                .parcelas(3)
                .seguro(true)
                .build();
        super.body = new JSONObject((new Gson().toJson(userEnvio)));
        super.POST();
    }

    @Quando("envio um resquest para criacao de uma simulacao com CPF {string}")
    public void envioUmResquestParaCriacaoDeUmaSimulacaoComCPF(String cpf) {
        super.url = prop.getProp("url_simulacao");
        super.headers = apiHeaders.headers();
        userEnvio = Users.builder()
                .nome(faker.name().fullName())
                .email(faker.internet().emailAddress())
                .cpf(cpf)
                .valor(1200.00)
                .parcelas(3)
                .seguro(true)
                .build();
        super.body = new JSONObject();
        super.POST();
    }

    @Então("retorna a simulação com os a mensagem de erro {string}")
    public void retornaASimulaçãoComOsAMensagemDeErro(String mensagem) {
        assertEquals(mensagem, response.jsonPath().get("mensagem"),
                "Mensagem error");
    }

    @Quando("eu efetuar uma solicitação para {string} recurso de restricoes")
    public void euEfetuarUmaSolicitaçãoParaRecursoDeRestricoes(String cpfRestricao) {
        super.url = prop.getProp("url_resticoes") + cpfRestricao;
        logInfo(url);
        super.headers = apiHeaders.headers();
        super.GET();
    }
}
