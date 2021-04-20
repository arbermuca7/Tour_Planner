package sample.viewModels;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import lombok.Getter;
import lombok.Setter;
import sample.models.Tour;

public class AddTourViewModel {

    public AddTourViewModel(){
        System.out.println("AddTourViewModel");
    }
     @Getter private final StringProperty inputName = new SimpleStringProperty("");
     @Getter private final StringProperty inputStart = new SimpleStringProperty("");
     @Getter private final StringProperty inputDestination = new SimpleStringProperty("");
     @Getter private final StringProperty inputDistance = new SimpleStringProperty("");
     @Getter private final StringProperty inputDescription = new SimpleStringProperty("");

    /**
     * this method takes the input values from the textFields
     * and saves then in the Tour
     * and the tour will be saved also in the database
     * */
    public void addTour(){
        String name = this.inputName.get();
        String start =  this.inputStart.get();
        String dest = this.inputDestination.get();
        String dist = this.inputDistance.get();
        String desc = this.inputDescription.get();

        double distance = 0;
        if (!dist.equals(""))
            distance = Double.parseDouble(dist);

        //System.out.println("N:"+name+", S:"+start+", De:"+dest+", Di:"+distance+", Desc:"+desc);

        Tour tour = new Tour(name,desc,start,dest,distance);
        //System.out.println("Objekt--> N:"+tour.getT_Name()+", S:"+tour.getStartPoint()+", De:"
        // +tour.getDestination()+", Di:"+tour.getT_Distance()+", Desc:"+tour.getDescription());

        //database access
        //database.addToDatabase(tour);
    }
    /**
     * a method to validate the input of
     * a text field with a certain regex
     * @param textField the field which will be validated
     * */
    public void validate_WordsTextFields(TextField textField){
        textField.setTextFormatter(new TextFormatter<>(change ->
                (change.getControlNewText().matches("[a-zA-Z1-9]*")) ? change : null));

    }
    /**
     * a method to validate the numbers input of
     * a text field with a certain regex
     * @param textField the field which will be validated
     * */
    public void validate_NumbersTextFields(TextField textField){
        textField.setTextFormatter(new TextFormatter<>(change ->
                (change.getControlNewText().matches("([1-9][0-9]*)?")) ? change : null));
    }
}

