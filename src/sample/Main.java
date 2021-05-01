package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.dataAccessLayer.database.DatabaseAccess;
import sample.dataAccessLayer.database.IDataAccess;

import java.sql.SQLException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root =
                FXMLLoader.load(getClass().getResource("views/mainWindow.fxml"));
        primaryStage.setTitle("Tour Planner");
        primaryStage.setScene(new Scene(root, 500, 600));
        primaryStage.show();
    }


    public static void main(String[] args) throws SQLException {
        IDataAccess db = new DatabaseAccess();
        //db.openConnection();
        launch(args);
        //db.closeConnection();
    }
}
