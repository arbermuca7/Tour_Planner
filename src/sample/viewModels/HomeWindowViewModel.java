package sample.viewModels;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import lombok.Getter;
import sample.models.Tour;

public class HomeWindowViewModel {

    @Getter private final StringProperty searchInputTours = new SimpleStringProperty("");
    @Getter private final StringProperty searchInputLogs = new SimpleStringProperty("");
    @Getter private final StringProperty outputNameTour = new SimpleStringProperty("Tour: ");

    @Getter private ObservableList<Tour> tourListItems;

    private Tour currentTour;

    private int count = 0;
    private final StringProperty input = new SimpleStringProperty("");
    private final StringProperty output = new SimpleStringProperty("Norbert clicked 0 times.");
    //public StringProperty inputProperty() { return input; }
    //public StringProperty outputProperty() { return output; }
    public void calculateOutputStr() {
        count++;
        this.output.set(this.input.get().concat(" is the "+count+" who clicked"));
        this.input.set("");
    }

    //set the tour items into the ListView
    /**
     * the following method takes
     * @param listView as the ListView Panel where all the tour will be shown
     * and inserts all of the created tour in this List
     * */
    public void setToursToList(ListView<Tour> listView){
        tourListItems = FXCollections.observableArrayList();
        tourListItems.addAll();
        listView.setItems(getTourListItems());
    }
    /**
     * the method which formats the cells to show the name
     * and has as a parameter the
     * @param listView  the list of all tours
     * */
    //format cells to show name
    public void setFormatCells(ListView<Tour> listView){
        listView.setCellFactory((param -> new ListCell<Tour>(){
            @Override
            protected void updateItem(Tour tourItem, boolean empty){
                super.updateItem(tourItem, empty);

                if(empty || (tourItem == null) || (tourItem.getT_Name() == null)){
                    setText(null);
                }else{
                    setText(tourItem.getT_Name());
                }
            }
        }));
    }
    /**
     * this method sets the listener to the tour
     * and take as parameter
     * @param listView the list of all tours
     * */
    //set the listener to the tour
    public void setTourListener(ListView<Tour> listView){
        listView.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if(newValue!=null && oldValue != newValue) {
                currentTour = newValue;
            }
        }));
    }

}
