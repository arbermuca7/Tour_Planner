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
import sample.models.Tour;
import sample.views.MainWindowController;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddLogController implements Initializable {

    public MainWindowController mainWindowController;
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
    @Getter private String id = null;



    public void addLog(ActionEvent actionEvent) throws SQLException {
        logItems = logData();
        //save the log in the database
        manager.setLogItems(logItems,id);
        //insert the log into the observable list
        mainWindowController.getLogsTableItems().add(logItems);
        // set the observable list into the table view
        mainWindowController.setLogsToTable();
        //close the window after adding the log into the table
        Stage stage = (Stage) addBtn.getScene().getWindow();
        stage.close();

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("-->AddLogController init");
        //manager initialisation
        manager = JavaAppManagerFactory.GetManager();
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

    /**
     * this method takes the input values from the textFields
     * and saves then in the Log
     * and the Logs will be saved also in the database
     * @return the Log
     * */
    public Log logData() {
        String name       = this.NameTextField.getText();
        String date       = this.DateTextField.getText();
        String duration   = this.DurationTextField.getText();
        String distance   = this.DistanceTextField.getText();
        String avgSpeed   = this.AverageSpeedTextField.getText();
        String fuelCost   = this.FuelCostTextField.getText();
        String tollRoad   = this.TollRoadsTextField.getText();
        String travelMode = this.TravelModeTextField.getText();
        String routeType  = this.RouteTypeTextField.getText();
        String rating     = this.RatingTextField.getText();
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
        Log log = new Log(name,date,duration,dist,speed,fuel,routeType,rate,travelMode,hasTollRoads,hasRestPlace);

        return log;
    }

    /**
     * initialize the ID of the tour we clicked
     * that's how we can add it the to the database
     * and connect it directly to the tour
     * */
    public void getClickedID(String addInfo){
        String[] content = addInfo.split(",");
        id = content[0];
        System.out.println("TourID in Log: "+id);
    }
}
