package sample.views.TourViews;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lombok.Getter;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import sample.businessLayer.inputValidation.IValid;
import sample.businessLayer.inputValidation.Valid;
import sample.businessLayer.javaApp.JavaAppManager;
import sample.businessLayer.javaApp.JavaAppManagerFactory;
import sample.models.Tour;
import sample.viewModels.TourVM.EditTourViewModel;
import sample.views.MainWindowController;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EditTourController implements Initializable {

    private static final Logger logger = LogManager.getLogger(EditTourController.class);

    public MainWindowController mainWindowController = new MainWindowController();
    public EditTourViewModel editTourViewModel = new EditTourViewModel();
    public IValid validate = new Valid();

    public TextField NameTextField;
    public TextField StartingPointTextField;
    public TextField DestinationTextField;
    public TextField DistanceTextField;
    public TextArea DescriptionTextField;
    public Button EditBtn;
    
    public JavaAppManager manager;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("-->EditTourController init");
        logger.info("EditTourController initialized");
        //set the value of the tour we want to edit
        //manager initialisation
        manager = JavaAppManagerFactory.GetManager();
        //Binding StringProperties in VM with the Controller Components
        NameTextField.textProperty().bindBidirectional(editTourViewModel.getInputName());
        StartingPointTextField.textProperty().bindBidirectional(editTourViewModel.getInputStart());
        DestinationTextField.textProperty().bindBidirectional(editTourViewModel.getInputDestination());
        DistanceTextField.textProperty().bindBidirectional(editTourViewModel.getInputDistance());
        DescriptionTextField.textProperty().bindBidirectional(editTourViewModel.getInputDescription());
        //validate the input fields
        validate.validate_specialTextFields(NameTextField);
        validate.validate_specialTextFields(StartingPointTextField);
        validate.validate_specialTextFields(DestinationTextField);
        validate.validate_distance_fuel(DistanceTextField);
    }
    // here is edited everything which a tour contains
    public void editTourD(ActionEvent actionEvent) throws SQLException {
        System.out.println("+++++++++\nID: "+editTourViewModel.getId()+"\n+++++++");
        //get the edited data
        String data = editTourViewModel.tourData();
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
        Tour tour = new Tour(editTourViewModel.getId(),name,desc,start,dest,distance);
        //edit Tour in DB
        manager.editData(tour,editTourViewModel.getId());
        //add the updated Tour to the ListView
        mainWindowController.getTourListItems().clear();
        manager.GetData(mainWindowController.getTourListItems());
        //close the window
        Stage stage = (Stage) EditBtn.getScene().getWindow();
        stage.close();

        logger.info("Save-Changes Button clicked");
    }
}
