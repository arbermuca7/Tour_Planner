package sample.viewModels.TourVM;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import lombok.Getter;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import sample.dataAccessLayer.database.DatabaseAccess;
import sample.dataAccessLayer.database.IDataAccess;
import sample.models.Tour;

public class EditTourViewModel{

    public EditTourViewModel(){
        System.out.println("EditTourViewModel");
    }
    private static final Logger logger = LogManager.getLogger(EditTourViewModel.class);

    @Getter private final StringProperty inputName = new SimpleStringProperty("");
    @Getter private final StringProperty inputStart = new SimpleStringProperty("");
    @Getter private final StringProperty inputDestination = new SimpleStringProperty("");
    @Getter private final StringProperty inputDistance = new SimpleStringProperty("");
    @Getter private final StringProperty inputDescription = new SimpleStringProperty("");

    @Getter private String id = null;

    //set the data to be changed in the edit window
    /**
     * @param editInfo String with all the informations of the tour
     * like identification, name, start, destination, description
     * and set these data in the edit window
     * */
    public void initEdit(String editInfo){
        String[] content = editInfo.split(",");
        id = content[0];
        inputName.set(content[1]);
        inputStart.set(content[2]);
        inputDestination.set(content[3]);
        inputDescription.set(content[4]);
        inputDistance.set(content[5]);
        System.out.println(id+","
                +content[1]+","+content[2]+","
                +content[3]+","+content[4]+","+content[5]);
        /*
        id = content[0];
        NameTextField.setText(content[1]);
        StartingPointTextField.setText(content[2]);
        DestinationTextField.setText(content[3]);
        DescriptionTextField.setText(content[4]);
        DistanceTextField.setText(content[5]);
        */
        logger.info("TourData in the edit Window initialized");
    }
    /**
     * takes the input from the text fields of the edit window
     * @return String with all the values to be added in DB
     * */
    public String tourData(){
        String name = this.inputName.get();
        String start = this.inputStart.get();
        String dest = this.inputDestination.get();
        String dist = this.inputDistance.get();
        String desc = this.inputDescription.get();

        logger.info("take the edited tour data from the window");

        return name+","+start+","+dest+","+dist+","+desc;
        /*
        String name = this.NameTextField.getText();
        String start = this.StartingPointTextField.getText();
        String dest = this.DestinationTextField.getText();
        String dist = this.DistanceTextField.getText();
        String desc = this.DescriptionTextField.getText();
        */
    }
}
