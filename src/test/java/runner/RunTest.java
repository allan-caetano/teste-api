package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

/**
 * Allan Caetano
 */

import java.io.IOException;

@RunWith(Cucumber.class)  //iniciar os cenários
@CucumberOptions(plugin = {"pretty","html:target/report.html", "json:target/report/cucumber.json"},
        features = {"src/test/resources/Features"},
        glue = {"steps"},
        tags = "@cpf-com-restricao")

public class RunTest {

    @AfterClass
    public static void report() throws IOException {
        Runtime.getRuntime().exec("cmd.exe /c mvn cucumber-report:reporting");
    }
}
