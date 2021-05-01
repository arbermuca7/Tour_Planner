package sample.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.viewModels.AddTourViewModel;

public class AddTourController implements Initializable {

    AddTourViewModel addTourViewModel = new AddTourViewModel();

    public TextField IDTextField;
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
        System.out.println("Controller AddTour init");
        IDTextField.textProperty().bindBidirectional(addTourViewModel.getInputIdentific());
        NameTextField.textProperty().bindBidirectional(addTourViewModel.getInputName());
        StartingPointTextField.textProperty().bindBidirectional(addTourViewModel.getInputStart());
        DestinationTextField.textProperty().bindBidirectional(addTourViewModel.getInputDestination());
        DistanceTextField.textProperty().bindBidirectional(addTourViewModel.getInputDistance());
        DescriptionTextField.textProperty().bindBidirectional(addTourViewModel.getInputDescription());
        //validate the input fields
        addTourViewModel.validate_WordsTextFields(NameTextField);
        addTourViewModel.validate_WordsTextFields(StartingPointTextField);
        addTourViewModel.validate_WordsTextFields(DestinationTextField);
        addTourViewModel.validate_NumbersTextFields(DistanceTextField);
    }
}
