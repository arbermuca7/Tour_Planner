package sample.viewModels;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import lombok.Getter;
import sample.dataAccessLayer.database.DatabaseAccess;
import sample.dataAccessLayer.database.IDataAccess;
import sample.models.Tour;
import sample.views.MainWindowController;
import java.io.IOException;
import java.sql.SQLException;

public class HomeWindowViewModel {

    @Getter private final StringProperty searchInputTours = new SimpleStringProperty("");
    @Getter private final StringProperty searchInputLogs = new SimpleStringProperty("");
    @Getter private final StringProperty outputNameTour = new SimpleStringProperty("Titel: ");
    @Getter private final StringProperty outputTourLogs = new SimpleStringProperty("Logs for Tour: ");
    @Getter private ObservableList<Tour> tourListItems = FXCollections.observableArrayList();

    IDataAccess dataAccess = new DatabaseAccess();

    public Tour currentTour;
    public int selectedIndex = -1;
    // display the selected tour to these Labels
    public void diplaySelect(ListView<Tour> listView){
        String movie = listView.getSelectionModel().getSelectedItem().getT_Name();
        this.outputNameTour.set("Titel: "+movie);
        this.outputTourLogs.set("Logs for "+movie+": ");
    }

    /**
     * the following method takes
     * @param listView as the ListView Panel where all the tour will be shown
     * and delete the selected tour
     * */
    //delete the selected tour
    public void deleteSelectedTour(ListView<Tour> listView) throws SQLException {
        selectedIndex = listView.getSelectionModel().getSelectedIndex();
        listView.getItems().remove(selectedIndex);
        //remove also from database
        //String ident = listView.getSelectionModel().getSelectedItem().getIdentification();
        //dataAccess.deleteTourData(ident);
    }

    //set the tour items into the ListView
    /**
     * the following method takes
     * @param listView as the ListView Panel where all the tour will be shown
     * and inserts all of the created tour in this List
     * */
    public void setToursToList(ListView<Tour> listView){
        //tourListItems = FXCollections.observableArrayList();
        //tourListItems.addAll();
        listView.getItems().addAll(tourListItems);
        //listView.setItems(addTourViewModel.getTourListItems());

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

    public void newWindow(String windowName,String windowTitle){
        Parent root=null;
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(MainWindowController.class.getResource(windowName+".fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setTitle(windowTitle);
        stage.setScene(new Scene(root));
        stage.show();
    }
}
