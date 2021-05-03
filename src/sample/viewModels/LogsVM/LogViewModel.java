package sample.viewModels.LogsVM;

import javafx.scene.control.TextField;

public interface LogViewModel {
    void logData();
    void validate_date(TextField textField);
    void validate_duration(TextField textField);
    void validate_distance_fuel(TextField textField);
    void validate_speed(TextField textField);
    void validate_rate(TextField textField);
    void validate_toll(TextField textField);
    void validate_WordsTextFields(TextField textField);

}
