package sample.businessLayer.configuration;

import lombok.Getter;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuration {

    private String url;
    private String username;
    private String password;

    public Configuration(){}

    public String getDBConfigs(){
        try{
            FileInputStream confFile = new FileInputStream("src/sample/businessLayer/configuration/config.properties");
            Properties configData = new Properties();
            configData.load(confFile);
            url = configData.getProperty("ConnectionString");
            username = configData.getProperty("usernameDB");
            password = configData.getProperty("passwordDB");
            confFile.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return url+"#"+username+"#"+password;
    }

}
