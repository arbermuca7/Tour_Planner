package sample.viewModels.TourVM;

import javafx.scene.control.TextField;

public interface TourViewModel {
    String tourData();
    void validate_WordsTextFields(TextField textField);
    void validate_NumbersTextFields(TextField textField);
}
