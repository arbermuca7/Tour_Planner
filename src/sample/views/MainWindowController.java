package sample.views;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.MenuModel;
import sample.ReportGeneration;
import sample.viewModels.HomeWindowViewModel;
import sample.viewModels.LogViewModel;
import sample.viewModels.TourViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.beans.binding.Bindings;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable{
    public HomeWindowViewModel viewModel = new HomeWindowViewModel();
    public TextField InputTextField;
    public Label OutputLabel;

    public MainWindowController()
    {
        System.out.println("Main Controller");
    }

    @FXML
    public void calculateOutput(ActionEvent actionEvent) {
        viewModel.calculateOutputStr();
    }

    @FXML
    public void clearInput(ActionEvent actionEvent) {
        InputTextField.textProperty().setValue("");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Controller init");
        InputTextField.textProperty().bindBidirectional(viewModel.inputProperty());
        Bindings.bindBidirectional(OutputLabel.textProperty(), viewModel.outputProperty());
    }

    /**
     * the method which connects the main window with
     * the addTour window, when you click the add button
     * */
    public void addTourWindow(ActionEvent actionEvent) {
        Parent root=null;
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(MainWindowController.class.getResource("addTour.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        AddTourController controller = loader.getController();
        Stage stage = new Stage();
        stage.setTitle("Add Tour");
        stage.setScene(new Scene(root));
        stage.show();
    }
    /**
     * The method is used to connect the main window with
     * the editTour window, when you click the edit button
     * */
    public void editTourWindow(ActionEvent actionEvent) {
        Parent root=null;
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(MainWindowController.class.getResource("editTour.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        EditTourController controller = loader.getController();
        Stage stage = new Stage();
        stage.setTitle("Edit Tour");
        stage.setScene(new Scene(root));
        stage.show();
    }
    /**
     * The method is used to open the addLog window,
     * when you click at the edit button
     **/
    public void addLogWindow(ActionEvent actionEvent) {
        Parent root=null;
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(MainWindowController.class.getResource("addLog.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        AddLogController controller = loader.getController();
        Stage stage = new Stage();
        stage.setTitle("Edit Tour");
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * The method is used to open the editLog window,
     * when you click at the edit button
     **/
    public void editLogWindow(ActionEvent actionEvent) {
        Parent root=null;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainWindowController.class.getResource("editLog.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        EditLogController controller = loader.getController();
        Stage stage = new Stage();
        stage.setTitle("Edit Tour");
        stage.setScene(new Scene(root));
        stage.show();
    }

}
