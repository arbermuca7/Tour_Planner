package sample.businessLayer.inputValidation;

import javafx.scene.control.TextField;

public interface IValid {

    void validate_WordsTextFields(TextField textField);
    void validate_date(TextField textField);
    void validate_duration(TextField textField);
    void validate_distance_fuel(TextField textField);
    void validate_speed(TextField textField);
    void validate_rate(TextField textField);
    void validate_toll(TextField textField);
    void validate_specialTextFields(TextField textField);

}
