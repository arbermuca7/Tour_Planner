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
/*
    @Getter private final StringProperty searchInputTours = new SimpleStringProperty("");
    @Getter private final StringProperty searchInputLogs = new SimpleStringProperty("");
    @Getter private final StringProperty outputNameTour = new SimpleStringProperty("Titel: ");
    @Getter private final StringProperty outputTourLogs = new SimpleStringProperty("Logs for Tour: ");
    @Getter private final StringProperty outputDescription = new SimpleStringProperty("..");
    @Getter private final ObservableList<Tour> tourListItems = FXCollections.observableArrayList();

    IDataAccess dataAccess = new DatabaseAccess();

    public Tour currentTour;
    public int selectedIndex = -1;
    // display the selected tour to these Labels
    public void diplaySelect(ListView<Tour> listView){
        String movie = listView.getSelectionModel().getSelectedItem().getT_Name();
        if(movie != null || !movie.isEmpty()){
            this.outputNameTour.set("Titel: "+movie);
            this.outputTourLogs.set("Logs for "+movie+": ");
        }else{
            System.out.println("nothing selected");
        }
    }

    public void readDescription(ListView<Tour> list){
        String id = list.getSelectionModel().getSelectedItem().getIdentification();
        String name = list.getSelectionModel().getSelectedItem().getT_Name();
        String start = list.getSelectionModel().getSelectedItem().getStartPoint();
        String destination = list.getSelectionModel().getSelectedItem().getDestination();
        double distance = list.getSelectionModel().getSelectedItem().getT_Distance();
        String description = list.getSelectionModel().getSelectedItem().getDescription();
        if(!start.isEmpty() && !destination.isEmpty() && distance!=0){
            this.outputDescription.set(
                    "The Trip "+name+" started in "+start+" and finished in "+destination+"\n"
                            +"The road length was "+distance+" km \n"
                            +"The Trip description: "+description);
        }
    }

    //delete the selected tour
    public void deleteSelectedTour(ListView<Tour> listView) throws SQLException {
        selectedIndex = listView.getSelectionModel().getSelectedIndex();
        listView.getItems().remove(selectedIndex);
        //remove also from database
        //String ident = listView.getSelectionModel().getSelectedItem().getIdentification();
        //dataAccess.deleteTourData(ident);
    }

    //set the tour items into the ListView
    public void setToursToList(ListView<Tour> listView){
        //listView.setItems(tourListItems);
        if(!listView.getItems().isEmpty()){
            System.out.println("List view: "+listView.getItems().get(0).getT_Name());
        }else{
            System.out.println("nothing..................");
        }
        //listView.setItems(addTourViewModel.getTourListItems());
    }


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
    */
}
