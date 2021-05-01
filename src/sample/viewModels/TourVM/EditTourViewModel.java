package sample.viewModels.TourVM;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import lombok.Getter;
import sample.dataAccessLayer.database.DatabaseAccess;
import sample.dataAccessLayer.database.IDataAccess;
import sample.models.Tour;

public class EditTourViewModel implements TourViewModel {

    public EditTourViewModel(){
        System.out.println("EditTourViewModel");
    }
    @Getter private final StringProperty inputName = new SimpleStringProperty("");
    @Getter private final StringProperty inputStart = new SimpleStringProperty("");
    @Getter private final StringProperty inputDestination = new SimpleStringProperty("");
    @Getter private final StringProperty inputDistance = new SimpleStringProperty("");
    @Getter private final StringProperty inputDescription = new SimpleStringProperty("");

    IDataAccess dataAccess = new DatabaseAccess();

    public void setTourDataToEdit(ListView<Tour> listView){
        String name = listView.getSelectionModel().getSelectedItem().getT_Name();
        String start =  listView.getSelectionModel().getSelectedItem().getStartPoint();
        String dest = listView.getSelectionModel().getSelectedItem().getDestination();
        Double dist = listView.getSelectionModel().getSelectedItem().getT_Distance();
        String desc = listView.getSelectionModel().getSelectedItem().getDescription();
        inputName.set(name);
        inputStart.set(start);
        inputDestination.set(dest);
        inputDescription.set(desc);
        String distance = "";
        if (!dist.equals(""))
            distance = Double.toString(dist);
        inputDistance.set(distance);
    }

    public String tourData(){
        String name = this.inputName.get();
        String start =  this.inputStart.get();
        String dest = this.inputDestination.get();
        String dist = this.inputDistance.get();
        String desc = this.inputDescription.get();

        //double distance = 0;
        //if (!dist.isEmpty())
        //    distance = Double.parseDouble(dist);

        return name+","+start+","+dest+","+dist+","+desc;
        //create the tour
        //Tour tour = new Tour(ident,name,desc,start,dest,distance);

    }
    /**
     * a method to validate the input of
     * a text field where can be set only letters, space and number between 1 and 9
     * @param textField the field which will be validated
     * */
    @Override
    public void validate_WordsTextFields(TextField textField){
        textField.setTextFormatter(new TextFormatter<>(change ->
                (change.getControlNewText().matches("[a-zA-Z1-9 ]*")) ? change : null));

    }
    /**
     * a method to validate the numbers input of
     * a text field where we set maximal 2 digits after the "."
     * @param textField the field which will be validated
     * */
    @Override
    public void validate_NumbersTextFields(TextField textField){
        textField.setTextFormatter(new TextFormatter<>(change ->
                (change.getControlNewText().matches("([1-9][0-9]*)?([.])?([1-9][0-9]{0,1})?")) ? change : null));
    }
}
