package sample.viewModels;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;
import lombok.Setter;

public class AddTourViewModel {

    public AddTourViewModel(){
        System.out.println("AddTourViewModel");
    }
    @Setter @Getter private final StringProperty inputName = new SimpleStringProperty("");
    @Setter @Getter private final StringProperty inputStart = new SimpleStringProperty("");
    @Setter @Getter private final StringProperty inputDestination = new SimpleStringProperty("");
    @Setter @Getter private final StringProperty inputDistance = new SimpleStringProperty("");
    @Setter @Getter private final StringProperty inputDescription = new SimpleStringProperty("");

    //private final StringProperty output = new SimpleStringProperty("Norbert is the 0 who clicked");
}
