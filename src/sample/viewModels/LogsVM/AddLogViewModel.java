package sample.viewModels.LogsVM;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import lombok.Getter;

public class AddLogViewModel {

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

    /**
     * this method takes the input values from the textFields
     * and saves then in the Log
     * and the Logs will be saved also in the database
     * */
    public void addLog() {
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

        //Add the Log
        //Log log = new Log(name,date,duration,dist,speed,fuel,routeType,rate,travelMode,hasTollRoads);

        //database access
        //database.addLogData(log);
    }
    /**
     * a method to validate the date input
     * in a format dd.mm.yyyy
     * @param textField the field which will be validated
     * */
    public void validate_date(TextField textField){
        textField.setTextFormatter(new TextFormatter<>(change ->
                (change.getControlNewText().matches("([0-9.]{0,10})?")) ? change : null));

    }
    /**
     * a method to validate the time spend on route input
     * in a format hh:mm:ss
     * @param textField the field which will be validated
     * */
    public void validate_duration(TextField textField){
        textField.setTextFormatter(new TextFormatter<>(change ->
                (change.getControlNewText().matches("([0-9:]{0,8})?")) ? change : null));

    }
    /**
     * a method to validate the numbers input of
     * a text field  where we set maximal 2 digits after the "."
     * @param textField the field which will be validated
     * */
    public void validate_distance_fuel(TextField textField){
        textField.setTextFormatter(new TextFormatter<>(change ->
                (change.getControlNewText().matches("([1-9][0-9]*)?([.])?([1-9][0-9]?)?")) ? change : null));
    }
    /**
     * a method to validate the speed input of
     * a text field  where we set between 2 and 5 digits
     * @param textField the field which will be validated
     * */
    public void validate_speed(TextField textField){
        textField.setTextFormatter(new TextFormatter<>(change ->
                (change.getControlNewText().matches("([0-9]{0,5})")) ? change : null));
    }
    /**
     * a method to validate the rating input
     * between 1 and 5 with a certain regex
     * @param textField the field which will be validated
     * */
    public void validate_rate(TextField textField){
        textField.setTextFormatter(new TextFormatter<>(change ->
                (change.getControlNewText().matches("([1-5]?)")) ? change : null));
    }
    /**
     * a method to validate the true/false input with
     * the format of just being possible to write true or false
     * @param textField the field which will be validated
     * */
    public void validate_toll(TextField textField){
        textField.setTextFormatter(new TextFormatter<>(change ->
                (change.getControlNewText().matches("[tf]?")) ? change : null));
    }
    /**
     * a method to validate the input of
     * a text field where can be set only letters, space and number between 1 and 9
     * @param textField the field which will be validated
     * */
    public void validate_WordsTextFields(TextField textField){
        textField.setTextFormatter(new TextFormatter<>(change ->
                (change.getControlNewText().matches("[a-zA-Z]*")) ? change : null));

    }
}
