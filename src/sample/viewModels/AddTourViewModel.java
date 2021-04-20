package sample.viewModels;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;
import lombok.Setter;
import sample.models.Tour;

public class AddTourViewModel {

    public AddTourViewModel(){
        System.out.println("AddTourViewModel");
    }
    @Setter @Getter private final StringProperty inputName = new SimpleStringProperty("");
    @Setter @Getter private final StringProperty inputStart = new SimpleStringProperty("");
    @Setter @Getter private final StringProperty inputDestination = new SimpleStringProperty("");
    @Setter @Getter private final StringProperty inputDistance = new SimpleStringProperty("");
    @Setter @Getter private final StringProperty inputDescription = new SimpleStringProperty("");

    public void addTour(){
        String name = this.inputName.get();
        String start =  this.inputStart.get();
        String dest = this.inputDestination.get();
        String dist = this.inputDistance.get();
        String desc = this.inputDescription.get();

        double distance = 0;
        if (!dist.equals(""))
            distance = Double.parseDouble(dist);

        System.out.println("N:"+name+", S:"+start+", De:"+dest+", Di:"+distance+", Desc:"+desc);

        Tour tour = new Tour(name,desc,start,dest,distance);
        System.out.println("Objekt--> N:"+tour.getT_Name()+", S:"+tour.getStartPoint()+", De:"
                +tour.getDestination()+", Di:"+tour.getT_Distance()+", Desc:"+tour.getDescription());

        //database access
        //database.addToDatabase(tour);
    }
}
