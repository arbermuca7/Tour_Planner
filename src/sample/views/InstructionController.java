package sample.views;

import javafx.beans.binding.Bindings;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import sample.viewModels.InstructionViewModel;

import java.net.URL;
import java.util.ResourceBundle;

public class InstructionController implements Initializable {

    private static final Logger logger = LogManager.getLogger(InstructionController.class);

    public MainWindowController mainWindowController;

    InstructionViewModel instructionViewModel = new InstructionViewModel();

    public Label HelpOutput;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logger.info("InstructionController initialized");
        instructionViewModel.getOutputHelping().set(instructionViewModel.getSearchHelp()+"\n"+instructionViewModel.getTourHelp()
                +"\n"+instructionViewModel.getLogHelp());
        Bindings.bindBidirectional(HelpOutput.textProperty(), instructionViewModel.getOutputHelping());

    }
}
