package sample;
import javafx.fxml.Initializable;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Controller implements Initializable{
    public int count = 0;
    public TextField InputTextField;
    public Label OutputLabel;

    private final StringProperty input = new SimpleStringProperty("");
    private final StringProperty output = new SimpleStringProperty("Norbert is the 0 who clicked");

    public Controller()
    {
        System.out.println("Controller created");
    }
    String name;
    @FXML
    public void calculateOutput(ActionEvent actionEvent) {
        count++;
        this.output.set(this.input.get().concat(" is the "+count+" who clicked"));
        this.input.set("");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Controller init");
        InputTextField.textProperty().bindBidirectional(inputProperty());
        Bindings.bindBidirectional(OutputLabel.textProperty(), outputProperty());
    }
    public StringProperty inputProperty() {
        return input;
    }

    public StringProperty outputProperty() {
        return output;
    }

}
