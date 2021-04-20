package sample.viewModels;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;

public class EditTourViewModel {

    public EditTourViewModel(){
        System.out.println("EditTourViewModel");
    }

    @Getter private final StringProperty inputName = new SimpleStringProperty("");
    @Getter private final StringProperty inputStart = new SimpleStringProperty("");
    @Getter private final StringProperty inputDestination = new SimpleStringProperty("");
    @Getter private final StringProperty inputDistance = new SimpleStringProperty("");
    @Getter private final StringProperty inputDescription = new SimpleStringProperty("");
}
