package sample.viewModels.LogsVM;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import lombok.Getter;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import sample.models.Log;
import sample.views.MainWindowController;

public class AddLogViewModel{

    private static final Logger logger = LogManager.getLogger(AddLogViewModel.class);

    public AddLogViewModel(){
        System.out.println("AddLogViewModel");
    }
    @Getter private final StringProperty inputName = new SimpleStringProperty("");
    @Getter private final StringProperty inputDate = new SimpleStringProperty("");
    @Getter private final StringProperty inputDuration = new SimpleStringProperty("");
    @Getter private final StringProperty inputDistance = new SimpleStringProperty("");
    @Getter private final StringProperty inputAvgSpeed = new SimpleStringProperty("");
    @Getter private final StringProperty inputFuelCosts = new SimpleStringProperty("");
    @Getter private final StringProperty inputTollRoads = new SimpleStringProperty("");
    @Getter private final StringProperty inputTravelMode = new SimpleStringProperty("");
    @Getter private final StringProperty inputRouteType = new SimpleStringProperty("");
    @Getter private final StringProperty inputRating = new SimpleStringProperty("");
    @Getter private final StringProperty inputRestingPlace = new SimpleStringProperty("");


    @Getter private String id = null;

    /**
     * this method takes the input values from the textFields
     * and saves then in the Log
     * and the Logs will be saved also in the database
     * @return the Log
     * */
    public Log logData() {
        String name     = this.inputName.get();
        String date     = this.inputDate.get();
        String duration = this.inputDuration.get();
        String distance = this.inputDistance.get();
        String avgSpeed = this.inputAvgSpeed.get();
        String fuelCost = this.inputFuelCosts.get();
        String tollRoad = this.inputTollRoads.get();
        String travelMode = this.inputTravelMode.get();
        String routeType = this.inputRouteType.get();
        String rating   = this.inputRating.get();
        String restingPlc = this.inputRestingPlace.get();

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
        logger.info("take the data from window and saved as a tourLog");
        return log;
        /*
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
        */
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
        logger.info("getting the ID of the selected tour");
    }
}
