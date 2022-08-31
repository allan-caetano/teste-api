package utils;

import java.io.*;
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

    public String obterNumero() throws IOException {
        Properties props = new Properties();
        File arquivo = new File("src/test/resources/cucumber.properties");
        try {
            props.load(new InputStreamReader(new FileInputStream(arquivo)));
            return props.getProperty("CPF");
        } catch (IOException e) {
            throw new IOException("Erro ao ler o arquivo de propriedades.", e);
        }
    }

    public  boolean guardarCPFGerado(String numero) {
        boolean retorno = true;
        BufferedWriter escritor;
        File arquivo = new File("src/test/resources/cucumber.properties");
        try {
            if (arquivo.exists())
                if (!arquivo.delete())
                    throw new IllegalArgumentException("Nao foi possivel deletar o arquivo anterior.");
            arquivo.createNewFile();
            escritor = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(arquivo)));
            escritor.write("CPF=" + numero);
            escritor.flush();
            escritor.close();
        } catch (IOException | IllegalArgumentException e) {
            retorno = false;
            e.printStackTrace(System.err);
        }
        return retorno;
    }

}