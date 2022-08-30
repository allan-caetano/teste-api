package report;

import com.rajatthareja.reportbuilder.ReportBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class HTMLReport {

    public static void main(String[] args) {
        // Create ReportBuilder Object
        ReportBuilder reportBuilder = new ReportBuilder();

        String path = System.getProperty("user.dir");

        // Set output Report Dir
        reportBuilder.setReportDirectory(path + "//target//");

        // Set output report file name
        reportBuilder.setReportFileName("CucumberHTML");

        // Set Report Title
        reportBuilder.setReportTitle("Execução de teste API");

        reportBuilder.setReportColor("green");

        // Enable voice control for report
        reportBuilder.enableVoiceControl();

        // Add additional info for Report
        reportBuilder.setAdditionalInfo("Environment", "My Environment");

        // Create list or report Files or Directories or URLs or JSONObject or
        // JSONString
        List<Object> cucumberJsonReports = new ArrayList<>();
        cucumberJsonReports.add(new File(path + "//target//report//cucumber.json"));
        cucumberJsonReports.add(new File(path + "//target//"));

        // Build your report
        reportBuilder.build(cucumberJsonReports);
    }
}
