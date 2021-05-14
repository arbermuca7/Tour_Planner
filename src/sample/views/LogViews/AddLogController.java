package sample.views.LogViews;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import lombok.Getter;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import sample.businessLayer.inputValidation.IValid;
import sample.businessLayer.inputValidation.Valid;
import sample.businessLayer.javaApp.JavaAppManager;
import sample.businessLayer.javaApp.JavaAppManagerFactory;
import sample.models.Log;
import sample.models.Tour;
import sample.viewModels.LogsVM.AddLogViewModel;
import sample.views.MainWindowController;
import sample.views.TourViews.AddTourController;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddLogController implements Initializable {

    private static final Logger logger = LogManager.getLogger(AddLogController.class);

    public MainWindowController mainWindowController;
    public AddLogViewModel addLogViewModel = new AddLogViewModel();
    public IValid validate = new Valid();

    public TextField NameTextField;
    public TextField DateTextField;
    public TextField DurationTextField;
    public TextField DistanceTextField;
    public TextField AverageSpeedTextField;
    public TextField FuelCostTextField;
    public TextField TollRoadsTextField;
    public TextField TravelModeTextField;
    public TextField RouteTypeTextField;
    public TextField RatingTextField;
    public TextField RestingPlaceTextField;
    public Button addBtn;

    @Getter private JavaAppManager manager;
    @Getter private Log logItems;
    //@Getter private String id = null;



    public void addLog(ActionEvent actionEvent) throws SQLException {
        logItems = addLogViewModel.logData();
        //save the log in the database
        manager.setLogItems(logItems, addLogViewModel.getId());
        //insert the log into the observable list
        mainWindowController.getLogsTableItems().add(logItems);
        // set the observable list into the table view
        mainWindowController.mainWindowViewModel.setLogsToTable(mainWindowController.LogTableView, mainWindowController.getLogsTableItems());
        //close the window after adding the log into the table
        Stage stage = (Stage) addBtn.getScene().getWindow();
        stage.close();

        logger.info("Add-Button in Log clicked");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("-->AddLogController init");
        logger.info("AddLogController initialized");
        //manager initialisation
        manager = JavaAppManagerFactory.GetManager();
        //Binding of Controller Components and StringProperties in VM
        NameTextField.textProperty().bindBidirectional(addLogViewModel.getInputName());
        DateTextField.textProperty().bindBidirectional(addLogViewModel.getInputDate());
        DurationTextField.textProperty().bindBidirectional(addLogViewModel.getInputDuration());
        DistanceTextField.textProperty().bindBidirectional(addLogViewModel.getInputDistance());
        AverageSpeedTextField.textProperty().bindBidirectional(addLogViewModel.getInputAvgSpeed());
        FuelCostTextField.textProperty().bindBidirectional(addLogViewModel.getInputFuelCosts());
        TollRoadsTextField.textProperty().bindBidirectional(addLogViewModel.getInputTollRoads());
        TravelModeTextField.textProperty().bindBidirectional(addLogViewModel.getInputTravelMode());
        RouteTypeTextField.textProperty().bindBidirectional(addLogViewModel.getInputRouteType());
        RatingTextField.textProperty().bindBidirectional(addLogViewModel.getInputRating());
        RestingPlaceTextField.textProperty().bindBidirectional(addLogViewModel.getInputRestingPlace());

        //validate the input fields
        validate.validate_date(DateTextField);
        validate.validate_duration(DurationTextField);
        validate.validate_distance_fuel(DistanceTextField);
        validate.validate_distance_fuel(FuelCostTextField);
        validate.validate_speed(AverageSpeedTextField);
        validate.validate_toll(TollRoadsTextField);
        validate.validate_toll(RestingPlaceTextField);
        validate.validate_rate(RatingTextField);
        validate.validate_WordsTextFields(RouteTypeTextField);
        validate.validate_WordsTextFields(TravelModeTextField);
    }
}
