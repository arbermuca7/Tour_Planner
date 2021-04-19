package sample.views;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.*;
import sample.viewModels.AddTourViewModel;

public class AddTourController implements Initializable {

    AddTourViewModel addTourViewModel = new AddTourViewModel();
    public TextField NameTextField;
    public TextField StartingPointTextField;
    public TextField DestinationTextField;
    public TextField DistanceTextField;
    public TextArea DescriptionTextField;
    public Button addBtn;

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
