package sample.views;
import sample.MenuModel;
import sample.ReportGeneration;
import sample.viewModels.LogViewModel;
import sample.viewModels.TourViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.beans.binding.Bindings;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable{
    public TourViewModel viewModel = new TourViewModel();
    public LogViewModel logViewModel = new LogViewModel();
    public MenuModel menuModel = new MenuModel();
    public ReportGeneration reportGeneration = new ReportGeneration();
    public int count = 0;
    public TextField InputTextField;
    public Label OutputLabel;

    public MainWindowController()
    {
        System.out.println("Controller created");
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
}