package sample.views;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sample.viewModels.EditTourViewModel;

import java.net.URL;
import java.util.ResourceBundle;

public class EditTourController implements Initializable {
    EditTourViewModel editTourViewModel = new EditTourViewModel();
    public TextField NameTextField;
    public TextField StartingPointTextField;
    public TextField DestinationTextField;
    public TextField DistanceTextField;
    public TextArea DescriptionTextField;
    public Button EditBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        NameTextField.textProperty().bindBidirectional(editTourViewModel.getInputName());
        StartingPointTextField.textProperty().bindBidirectional(editTourViewModel.getInputStart());
        DestinationTextField.textProperty().bindBidirectional(editTourViewModel.getInputDestination());
        DistanceTextField.textProperty().bindBidirectional(editTourViewModel.getInputDistance());
        DescriptionTextField.textProperty().bindBidirectional(editTourViewModel.getInputDescription());

    }

    public void editTour(ActionEvent actionEvent) {

    }
}
