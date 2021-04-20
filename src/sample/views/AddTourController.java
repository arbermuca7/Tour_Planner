package sample.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.viewModels.AddTourViewModel;

public class AddTourController implements Initializable {

    AddTourViewModel addTourViewModel = new AddTourViewModel();
    public TextField NameTextField;
    public TextField StartingPointTextField;
    public TextField DestinationTextField;
    public TextField DistanceTextField;
    public TextArea DescriptionTextField;
    public Button AddBtn;

    /**
     * this method inserts the tour into the database or file
     * and closes the window.
     * */
    @FXML
    public void addTour(ActionEvent actionEvent) {
        addTourViewModel.addTour();
        //close the window
        Stage stage = (Stage) AddBtn.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Controller init");
        NameTextField.textProperty().bindBidirectional(addTourViewModel.getInputName());
        StartingPointTextField.textProperty().bindBidirectional(addTourViewModel.getInputStart());
        DestinationTextField.textProperty().bindBidirectional(addTourViewModel.getInputDestination());
        DistanceTextField.textProperty().bindBidirectional(addTourViewModel.getInputDistance());
        DescriptionTextField.textProperty().bindBidirectional(addTourViewModel.getInputDescription());
    }
}
