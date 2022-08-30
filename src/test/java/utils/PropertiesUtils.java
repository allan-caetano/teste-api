package utils;

import java.io.IOException;
import java.security.Provider;
import java.util.Properties;

import static java.lang.System.*;

public class PropertiesUtils {

    Properties properties = new Properties();

    public String getProp(String key){
        try {
            if (System.getProperty("env") == null){
                properties.load(getClass().getClassLoader().getResourceAsStream("homolog.properties"));
            } else {
                properties.load(getClass().getClassLoader().getResourceAsStream(System.getProperty("env")));
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return properties.getProperty(key);
    }

}