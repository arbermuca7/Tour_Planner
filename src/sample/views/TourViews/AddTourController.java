package sample.views.TourViews;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lombok.Getter;
import sample.businessLayer.inputValidation.IValid;
import sample.businessLayer.inputValidation.Valid;
import sample.businessLayer.javaApp.JavaAppManager;
import sample.businessLayer.javaApp.JavaAppManagerFactory;
import sample.models.Tour;
import sample.viewModels.TourVM.AddTourViewModel;
import sample.views.MainWindowController;

public class AddTourController implements Initializable {

    public MainWindowController mainWindowController;
    public AddTourViewModel addTourViewModel = new AddTourViewModel();
    public IValid validate = new Valid();

    public TextField IDTextField;
    public TextField NameTextField;
    public TextField StartingPointTextField;
    public TextField DestinationTextField;
    public TextField DistanceTextField;
    public TextArea DescriptionTextField;
    public Button AddBtn;

    @Getter private Tour tour;
    @Getter private JavaAppManager manager;
    /**
     * this method inserts the tour into the database or file
     * and closes the window.
     * */
    @FXML
    public void addTour(ActionEvent actionEvent) throws SQLException {
        tour = addTourViewModel.tourData();
        //add tour to ObservableList in MainController and database
        manager.SetDataItems(tour);
        mainWindowController.getTourListItems().add(tour);
        //Save tour to listview
        mainWindowController.setToursToList();
        //close the window
        Stage stage = (Stage) AddBtn.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("-->AddTourController init");
        //manager initialisation
        manager = JavaAppManagerFactory.GetManager();
        //Binding StringProperties in VM with the Controller Components
        IDTextField.textProperty().bindBidirectional(addTourViewModel.getInputIdentific());
        NameTextField.textProperty().bindBidirectional(addTourViewModel.getInputName());
        StartingPointTextField.textProperty().bindBidirectional(addTourViewModel.getInputStart());
        DestinationTextField.textProperty().bindBidirectional(addTourViewModel.getInputDestination());
        DistanceTextField.textProperty().bindBidirectional(addTourViewModel.getInputDistance());
        DescriptionTextField.textProperty().bindBidirectional(addTourViewModel.getInputDescription());

        //validate the input fields
        validate.validate_specialTextFields(NameTextField);
        validate.validate_specialTextFields(StartingPointTextField);
        validate.validate_specialTextFields(DestinationTextField);
        validate.validate_distance_fuel(DistanceTextField);
    }
}

