package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.businessLayer.configuration.Configuration;

import java.sql.SQLException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //load the Config File
        Configuration.getDBConfigs();

        Parent root =
                FXMLLoader.load(getClass().getResource("views/mainWindow.fxml"));
        primaryStage.setTitle("Tour Planner");
        primaryStage.setScene(new Scene(root, 500, 600));
        primaryStage.show();

    }
    public static void main(String[] args) throws SQLException {
        launch(args);
    }
}
