package sample.views.LogViews;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.viewModels.LogsVM.AddLogViewModel;

import java.net.URL;
import java.util.ResourceBundle;

public class AddLogController implements Initializable {

    AddLogViewModel addLogViewModel = new AddLogViewModel();
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
    public Button addBtn;

    public void addLog(ActionEvent actionEvent) {
        addLogViewModel.addLog();
        //close the window after adding the log into the table
        Stage stage = (Stage) addBtn.getScene().getWindow();
        stage.close();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Controller AddLog init");
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
        //validate the input fields
        addLogViewModel.validate_date(DateTextField);
        addLogViewModel.validate_duration(DurationTextField);
        addLogViewModel.validate_distance_fuel(DistanceTextField);
        addLogViewModel.validate_distance_fuel(FuelCostTextField);
        addLogViewModel.validate_speed(AverageSpeedTextField);
        addLogViewModel.validate_toll(TollRoadsTextField);
        addLogViewModel.validate_rate(RatingTextField);
        addLogViewModel.validate_WordsTextFields(RouteTypeTextField);
        addLogViewModel.validate_WordsTextFields(TravelModeTextField);
    }
}
