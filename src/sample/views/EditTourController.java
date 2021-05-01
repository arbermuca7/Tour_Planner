package sample.views;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.models.Tour;
import sample.viewModels.*;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EditTourController implements Initializable {
    EditTourViewModel editTourViewModel = new EditTourViewModel();
    MainWindowController mainWindowController = new MainWindowController();
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
        //set the value of the tour we want to edit
        editTourViewModel.setTourDataToEdit(mainWindowController.TourListView);
        //validate the input fields
        editTourViewModel.validate_WordsTextFields(NameTextField);
        editTourViewModel.validate_WordsTextFields(StartingPointTextField);
        editTourViewModel.validate_WordsTextFields(DestinationTextField);
        editTourViewModel.validate_NumbersTextFields(DistanceTextField);

    }

    public void editTourD(ActionEvent actionEvent) throws SQLException {
        String ident = mainWindowController.getIdentification();
        //get the index of the selected tour
        int index = mainWindowController.TourListView.getSelectionModel().getSelectedIndex();
        //remove the selected Tour
        mainWindowController.TourListView.getItems().remove(index);
        //get the edited data
        String data = editTourViewModel.editTourD();
        String[] content = data.split(",");
        String name = content[0];
        String start= content[1];
        String dest = content[2];
        String dist = content[3];
        String desc = content[4];
        double distance = 0;
        if (!dist.isEmpty())
            distance = Double.parseDouble(dist);
        //create the new updated tour
        Tour tour = new Tour(ident,name,desc,start,dest,distance);
        //edit Tour in DB
        //dataAccess.editTourData(tour,ident);
        //add the updated Tour to the ListView
        mainWindowController.TourListView.getItems().add(index,tour);
        //close the window
        Stage stage = (Stage) EditBtn.getScene().getWindow();
        stage.close();
    }
}
