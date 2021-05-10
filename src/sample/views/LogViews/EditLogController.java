package sample.views.LogViews;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import lombok.Getter;
import sample.businessLayer.inputValidation.IValid;
import sample.businessLayer.inputValidation.Valid;
import sample.businessLayer.javaApp.JavaAppManager;
import sample.businessLayer.javaApp.JavaAppManagerFactory;
import sample.models.Log;
import sample.viewModels.LogsVM.EditLogViewModel;
import sample.views.MainWindowController;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EditLogController implements Initializable {

    public MainWindowController mainWindowController = new MainWindowController();
    public EditLogViewModel editLogViewModel = new EditLogViewModel();
    public IValid validate = new Valid();

    public TextField NameTextField;
    public TextField DateTextField;
    public TextField DurationTextField;
    public TextField DistanceTextField;
    public TextField AverageSpeedTextField;
    public TextField FuelCostsTextField;
    public TextField TollRoadsTextField;
    public TextField TravelModeTextField;
    public TextField RouteTypeTextField;
    public TextField RatingTextField;
    public TextField RestingPlaceTextField;
    public Button EditBtn;

    @Getter private JavaAppManager manager;
    @Getter private Log logItem;
    String id = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("-->EditLogController init");
        //manager initialisation
        manager = JavaAppManagerFactory.GetManager();
        //Binding of Controller Components and StringProperties in VM
        NameTextField.textProperty().bindBidirectional(editLogViewModel.getInputName());
        DateTextField.textProperty().bindBidirectional(editLogViewModel.getInputDate());
        DurationTextField.textProperty().bindBidirectional(editLogViewModel.getInputDuration());
        DistanceTextField.textProperty().bindBidirectional(editLogViewModel.getInputDistance());
        AverageSpeedTextField.textProperty().bindBidirectional(editLogViewModel.getInputAvgSpeed());
        FuelCostsTextField.textProperty().bindBidirectional(editLogViewModel.getInputFuelCosts());
        TollRoadsTextField.textProperty().bindBidirectional(editLogViewModel.getInputTollRoads());
        TravelModeTextField.textProperty().bindBidirectional(editLogViewModel.getInputTravelMode());
        RouteTypeTextField.textProperty().bindBidirectional(editLogViewModel.getInputRouteType());
        RatingTextField.textProperty().bindBidirectional(editLogViewModel.getInputRating());
        RestingPlaceTextField.textProperty().bindBidirectional(editLogViewModel.getInputRestingPlace());
        //validate the input fields
        validate.validate_date(DateTextField);
        validate.validate_duration(DurationTextField);
        validate.validate_distance_fuel(DistanceTextField);
        validate.validate_distance_fuel(FuelCostsTextField);
        validate.validate_speed(AverageSpeedTextField);
        validate.validate_toll(TollRoadsTextField);
        validate.validate_toll(RestingPlaceTextField);
        validate.validate_rate(RatingTextField);
        validate.validate_WordsTextFields(RouteTypeTextField);
        validate.validate_WordsTextFields(TravelModeTextField);

    }

    public void editTour(ActionEvent actionEvent) throws SQLException {
        //get the tour to edit
        logItem = editLogViewModel.logData();

        //edit Log in DB
        manager.editLogItems(logItem);

        //add the updated Log to the TableView
        mainWindowController.getLogsTableItems().clear();
        manager.GetAllLogs(mainWindowController.getLogsTableItems());

        //close the window
        Stage stage = (Stage) EditBtn.getScene().getWindow();
        stage.close();
    }
/*
    public void initTheEdit(String info){
        String[] content = info.split(",");
        NameTextField.setText(content[0]);
        DateTextField.setText(content[1]);
        DurationTextField.setText(content[2]);
        DistanceTextField.setText(content[3]);
        AverageSpeedTextField.setText(content[4]);
        FuelCostsTextField.setText(content[5]);
        TollRoadsTextField.setText(content[6]);
        TravelModeTextField.setText(content[7]);
        RouteTypeTextField.setText(content[8]);
        RatingTextField.setText(content[9]);
        RestingPlaceTextField.setText(content[10]);

        /*System.out.println("INIT the EDIT LOG: "+content[0]+","+content[1]+","+content[2]+","+content[3]+","+content[4]+","
                +content[5]+","+content[6]+","+content[7]+","+content[8]+","+content[9]+","+content[10]);
    }

    public Log logData(){
        String name = this.NameTextField.getText();
        String date = this.DateTextField.getText();
        String duration = this.DurationTextField.getText();
        String distance = this.DistanceTextField.getText();
        String avgSpeed = this.AverageSpeedTextField.getText();
        String fuelCost = this.FuelCostsTextField.getText();
        String tollRoad = this.TollRoadsTextField.getText();
        String travel = this.TravelModeTextField.getText();
        String route = this.RouteTypeTextField.getText();
        String rating = this.RatingTextField.getText();
        String restingPlc = this.RestingPlaceTextField.getText();

        //parse the distance
        double dist = 0;
        if(!distance.isEmpty()){
            dist = Double.parseDouble(distance);
        }
        //parse the average speed
        int speed = 0;
        if(!avgSpeed.isEmpty()){
            speed = Integer.parseInt(avgSpeed);
        }
        //parse fuel consumption
        float fuel = 0;
        if(!fuelCost.isEmpty()){
            fuel = Float.parseFloat(fuelCost);
        }
        //parse the route rating
        int rate = 0;
        if(!rating.isEmpty()){
            rate = Integer.parseInt(rating);
        }
        //parse the boolean if there is any toll road
        boolean hasTollRoads = false;
        if(tollRoad.equals("t")){
            hasTollRoads = true;
        }
        //parse the boolean if there is any resting place
        boolean hasRestPlace = false;
        if(restingPlc.equals("t")){
            hasRestPlace = true;
        }
        //Add the Log
        return new Log(name,date,duration,dist,speed,fuel,route,rate,travel,hasTollRoads,hasRestPlace);
    }*/
}
