package sample.viewModels.TourVM;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;
import sample.models.Tour;

public class AddTourViewModel {

    public AddTourViewModel() {
        System.out.println("AddTourViewModel");
    }

    @Getter
    private final StringProperty inputIdentific = new SimpleStringProperty("");
    @Getter
    private final StringProperty inputName = new SimpleStringProperty("");
    @Getter
    private final StringProperty inputStart = new SimpleStringProperty("");
    @Getter
    private final StringProperty inputDestination = new SimpleStringProperty("");
    @Getter
    private final StringProperty inputDistance = new SimpleStringProperty("");
    @Getter
    private final StringProperty inputDescription = new SimpleStringProperty("");

    /**
     * this method takes the input values from the textFields
     * and saves then in the Tour
     * and the tour will be saved also in the database
     * @return the Tour
     */
    public Tour tourData() {
        String ident = this.inputIdentific.get();
        String name = this.inputName.get();
        String start = this.inputStart.get();
        String dest = this.inputDestination.get();
        String dist = this.inputDistance.get();
        String desc = this.inputDescription.get();

        double distance = 0;
        if (!dist.isEmpty())
            distance = Double.parseDouble(dist);
        //create the tour
        Tour tour = new Tour(ident, name, desc, start, dest, distance);
        return tour;
        /*
        String ident = this.IDTextField.getText();
        String name = this.NameTextField.getText();
        String start = this.StartingPointTextField.getText();
        String dest = this.DestinationTextField.getText();
        String dist = this.DistanceTextField.getText();
        String desc = this.DescriptionTextField.getText();
        */
    }
}

