package utils;

public class LogUtils { //esta classe serve para verificar se o Json não é vazio
    public void logInfo(String value) {
        if (!value.contains("{}")) {
            ScenarioUtils.addText(value);
        }
    }

    public void logError(String value) {
        if (!value.contains("{}")) {
            ScenarioUtils.addText(value);
        }
    }
}
