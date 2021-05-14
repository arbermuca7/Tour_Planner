package sample.viewModels.LogsVM;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.Initializable;
import lombok.Getter;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import sample.models.Log;
import sample.viewModels.TourVM.EditTourViewModel;

import java.net.URL;
import java.util.ResourceBundle;

public class EditLogViewModel {

    private static final Logger logger = LogManager.getLogger(EditLogViewModel.class);

    public EditLogViewModel(){
        System.out.println("EditLogViewModel");
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


    public void initTheEdit(String info){
        String[] content = info.split(",");
        inputName.set(content[0]);
        inputDate.set(content[1]);
        inputDuration.set(content[2]);
        inputDistance.set(content[3]);
        inputAvgSpeed.set(content[4]);
        inputFuelCosts.set(content[5]);
        inputTollRoads.set(content[6]);
        inputTravelMode.set(content[7]);
        inputRouteType.set(content[8]);
        inputRating.set(content[9]);
        inputRestingPlace.set(content[10]);
        /*
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
        */

        /*System.out.println("INIT the EDIT LOG: "+content[0]+","+content[1]+","+content[2]+","+content[3]+","+content[4]+","
                +content[5]+","+content[6]+","+content[7]+","+content[8]+","+content[9]+","+content[10]);*/
        logger.info("TourLogData in the edit Window initialized");
    }

    /**
     * takes the input from the text fields of the edit window
     * @return String with all the values to be added in DB
     * */
    public Log logData(){
        String name = this.inputName.get();
        String date = this.inputDate.get();
        String duration = this.inputDuration.get();
        String distance = this.inputDistance.get();
        String avgSpeed = this.inputAvgSpeed.get();
        String fuelCost = this.inputFuelCosts.get();
        String tollRoad = this.inputTollRoads.get();
        String travel = this.inputTravelMode.get();
        String route = this.inputRouteType.get();
        String rating = this.inputRating.get();
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
        logger.info("take the edited data and save them as a TourLog");
        //Add the Log
        return new Log(name,date,duration,dist,speed,fuel,route,rate,travel,hasTollRoads,hasRestPlace);

        /*
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
        */
    }
}
