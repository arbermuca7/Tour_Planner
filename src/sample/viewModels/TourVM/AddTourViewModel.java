package sample.viewModels.TourVM;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import lombok.Getter;
import sample.dataAccessLayer.database.DatabaseAccess;
import sample.dataAccessLayer.database.IDataAccess;
import sample.models.Tour;
import sample.viewModels.HomeWindowViewModel;
import sample.views.MainWindowController;

public class AddTourViewModel implements TourViewModel {

    public AddTourViewModel(){
        System.out.println("AddTourViewModel");
    }
    @Getter private final StringProperty inputIdentific = new SimpleStringProperty("");
    @Getter private final StringProperty inputName = new SimpleStringProperty("");
    @Getter private final StringProperty inputStart = new SimpleStringProperty("");
    @Getter private final StringProperty inputDestination = new SimpleStringProperty("");
    @Getter private final StringProperty inputDistance = new SimpleStringProperty("");
    @Getter private final StringProperty inputDescription = new SimpleStringProperty("");

    HomeWindowViewModel homeWindowViewModel = new HomeWindowViewModel();
    MainWindowController mainWindowController = new MainWindowController();
    IDataAccess dataAccess = new DatabaseAccess();

    /**
     * this method takes the input values from the textFields
     * and saves then in the Tour
     * and the tour will be saved also in the database
     * */
    public String tourData(){
        String ident = this.inputIdentific.get();
        String name = this.inputName.get();
        String start =  this.inputStart.get();
        String dest = this.inputDestination.get();
        String dist = this.inputDistance.get();
        String desc = this.inputDescription.get();

        double distance = 0;
        if (!dist.isEmpty())
            distance = Double.parseDouble(dist);

        //create the tour
        Tour tour = new Tour(ident,name,desc,start,dest,distance);

        //add tour to Observeable list in HomeWindowViewModel
        homeWindowViewModel.getTourListItems().add(tour);
        //Save tour to listview
        homeWindowViewModel.setToursToList(mainWindowController.TourListView);
        //add the tour to database
        //dataAccess.addTourData(tour);
        return "";
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

