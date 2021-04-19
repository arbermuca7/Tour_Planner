package sample.viewModels;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class HomeWindowViewModel {

    private int count = 0;
    private final StringProperty input = new SimpleStringProperty("");
    private final StringProperty output = new SimpleStringProperty("Norbert is the 0 who clicked");

    public StringProperty inputProperty() {
        return input;
    }

    public StringProperty outputProperty() {
        return output;
    }

    public void calculateOutputStr() {
        count++;
        this.output.set(this.input.get().concat(" is the "+count+" who clicked"));
        this.input.set("");
    }

}
