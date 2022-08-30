package steps;

import api.ApiUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.ScenarioUtils;

import java.util.HashMap;

public class Hooks extends ApiUtils {

    @Before   //executa antes de cenários
    public void before(Scenario scenario){
        ScenarioUtils.add(scenario);
        headers = new HashMap<>();
        params = new HashMap<>();
    }

    @After  //executa depois de cenários
    public void after(){
        ScenarioUtils.remove();
    }
}
