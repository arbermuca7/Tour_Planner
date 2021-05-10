package sample.views;

import javafx.beans.binding.Bindings;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import sample.viewModels.InstructionViewModel;

import java.net.URL;
import java.util.ResourceBundle;

public class InstructionController implements Initializable {

    public MainWindowController mainWindowController;

    InstructionViewModel instructionViewModel = new InstructionViewModel();

    public Label HelpOutput;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        instructionViewModel.getOutputHelping().set(instructionViewModel.getSearchHelp()+"\n"+instructionViewModel.getTourHelp()
                +"\n"+instructionViewModel.getLogHelp());
        Bindings.bindBidirectional(HelpOutput.textProperty(), instructionViewModel.getOutputHelping());

    }
}
