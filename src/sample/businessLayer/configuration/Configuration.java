package sample.businessLayer.configuration;

import lombok.Getter;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import sample.viewModels.MainWindowViewModel;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuration {

    @Getter private static String url;
    @Getter private static String username;
    @Getter private static String password;
    @Getter private static String pdfPath;

    private static final Logger logger = LogManager.getLogger(Configuration.class);


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
            logger.info("get DB Connection and File location");
        }catch (IOException e) {
            e.printStackTrace();
            logger.info("the connection with the config.properties failed. Error message:"+e.getMessage());
        }
    }

}
