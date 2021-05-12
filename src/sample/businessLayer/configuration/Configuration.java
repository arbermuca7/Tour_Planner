package sample.businessLayer.configuration;

import lombok.Getter;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuration {

    @Getter private static String url;
    @Getter private static String username;
    @Getter private static String password;
    @Getter private static String pdfPath;


    public Configuration(){}

    public static void getDBConfigs(){
        try{
            FileInputStream confFile = new FileInputStream("src/sample/businessLayer/configuration/config.properties");
            Properties configData = new Properties();
            configData.load(confFile);
            url = configData.getProperty("ConnectionString");
            username = configData.getProperty("usernameDB");
            password = configData.getProperty("passwordDB");
            pdfPath = configData.getProperty("reportPath");
            confFile.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

}
