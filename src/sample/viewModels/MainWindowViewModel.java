package sample.viewModels;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import lombok.Getter;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import sample.businessLayer.javaApp.JavaAppManager;
import sample.dataAccessLayer.database.DatabaseAccess;
import sample.dataAccessLayer.database.IDataAccess;
import sample.models.Log;
import sample.models.Tour;
import sample.views.MainWindowController;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class MainWindowViewModel {

    private static final Logger logger = LogManager.getLogger(MainWindowViewModel.class);

    @Getter private final StringProperty searchInput = new SimpleStringProperty("");
    @Getter private final StringProperty outputNameTour = new SimpleStringProperty("Titel: ");
    @Getter private final StringProperty outputTourLogs = new SimpleStringProperty("Logs for Tour: ");
    @Getter private final StringProperty outputDescription = new SimpleStringProperty("..");

    public Tour currentTour;
    public int selectedIndex = -1;

    /**
     * this method takes as
     * @param listView as ListView and when you select a certain
     * tour the Label which are defined will be set with the tour name
     * */
    // display the selected tour to these Labels
    public void diplaySelect(ListView<Tour> listView){
        String name = listView.getSelectionModel().getSelectedItem().getT_Name();
        if(name != null || !name.isEmpty()){
            this.outputNameTour.set("Titel: "+name);
            this.outputTourLogs.set("Logs for "+name+": ");
            //this.OutputNameLabel.textProperty().set("Titel: "+name);
            //this.tourLogs.textProperty().set("Logs for "+name+": ");
        }else{
            System.out.println("nothing selected");
        }
        logger.info("Title of the Tour visualized");
    }

    //it is a method that shows us what is the description of a certain tour
    /**
     * the following method takes
     * @param list as the ListView Panel where all the tour will be shown
     * and when we select a tour it shows us all the tour description in the Tab
     * */
    public void readDescription(ListView<Tour> list){
        Tour tour = list.getSelectionModel().getSelectedItem();
        if(!tour.getStartPoint().isEmpty() && !tour.getDestination().isEmpty() && tour.getT_Distance()!=0){
            this.outputDescription.set(
                    "The Trip \""+tour.getT_Name()+"\" started in "+tour.getStartPoint()+" and finished in "+tour.getDestination()+"\n"
                            +"The road length was "+tour.getT_Distance()+" km \n"
                            +"The Trip description: "+tour.getDescription());
        }
        logger.info("tour description created and visualized");
    }

    /**
     * the following method makes it possible
     * to show only the log connected to a certain Tour
     * @param toursList  the List of Tours
     * @param logTable the logs ObservableList
     * @param mg the JavaAppManager
     * */
    public void showOnlyLogsOfTour(ListView<Tour> toursList, ObservableList<Log> logTable, JavaAppManager mg){
        Tour tour = toursList.getSelectionModel().getSelectedItem();
        String identificationTour = tour.getIdentification();
        //clear the observable list
        logTable.clear();
        //insert again the other to the logs
        mg.GetLogsForTour(logTable,identificationTour);
        logger.info("Logs of a certain Tour showed");
    }

    /**
     * the following method takes
     * @param listView as the ListView Panel where all the tour will be shown
     * @param logsTableObs the logs ObservableList
     * @param manager the JavaAppManager
     * and delete the selected tour
     * */
    //delete the selected tour
    public void deleteSelectedTour(ListView<Tour> listView,ObservableList<Log> logsTableObs, JavaAppManager manager) throws SQLException {
        selectedIndex = listView.getSelectionModel().getSelectedIndex();
        String ident = listView.getSelectionModel().getSelectedItem().getIdentification();
        listView.getItems().remove(selectedIndex);
        //remove also from database
        System.out.println("id of the selected tour to del:" + ident);
        if(manager.checkIfTourHasLog(logsTableObs,ident)){
            //remove the logs of this tour
            manager.deleteTheLogsOfTour(ident);
        }
        manager.delData(ident);
        logger.info("Selected tour deleted");
    }

    /**
     * @param tableView Table view where the logs are shown
     * @param manager the JavaAppManager
     * and delete the selected log
     * */
    public void deleteSelectedLog(TableView<Log> tableView,JavaAppManager manager) throws SQLException {
        selectedIndex = tableView.getSelectionModel().getSelectedIndex();
        String name = tableView.getSelectionModel().getSelectedItem().getName();
        tableView.getItems().remove(selectedIndex);
        //remove from db the log
        System.out.println("Name of Log to be deleted: "+name);
        manager.delLogItem(name);
        logger.info("selected Log deleted");
    }
    //---------------------------------------edit a tour from the list-----------------------------------------------
    /**
     * @param tourG a Tour, with its help we set
     * all the fields with the selected tour values
     * */
    public String setTourDataToEdit(Tour tourG){
        String distance = "";
        if (tourG.getT_Distance()!=0)
            distance = Double.toString(tourG.getT_Distance());

        logger.info("fill the textfields in the tour Window with the text that's going to be edited");

        return tourG.getIdentification()+","
                +tourG.getT_Name()+","+tourG.getStartPoint()+","
                +tourG.getDestination()+","+tourG.getDescription()+","+distance;
    }

    /**
     * @param logG a Log, with its help we set
     * all the fields with the selected log values
     * */
    public String setLogDataToEdit(Log logG){
        String distance = "";
        if (logG.getDistance()!=0)
            distance = Double.toString(logG.getDistance());

        String speed = "";
        if(logG.getAvg_speed()!=0)
            speed = Integer.toString(logG.getAvg_speed());

        String fuel = "";
        if(logG.getFuel_cost()!=0)
            fuel = Float.toString(logG.getFuel_cost());

        String tollRoad;
        if (logG.isToll_roads()){ tollRoad = "t"; }
        else{ tollRoad = "f"; }

        String rating = "";
        if(logG.getRating()!=0)
            rating = Integer.toString(logG.getRating());

        String rest = "";
        if (logG.isResting_place()){ rest = "t"; }
        else{ rest = "f"; }

        logger.info("fill the textfields in the log Window with the text thats going to be edited");
        return logG.getName()+","+logG.getDate()+","
                +logG.getDuration()+","+distance+","+speed+","+fuel+","+tollRoad+","+logG.getTravel_mode()+","
                +logG.getRoute_type()+","+rating+","+rest;
    }

    /**
     * this method takes all the Log inserted in the ObservableList
     * and adds them to the Tableview
     * */
    public void setLogsToTable(TableView<Log> logTable, ObservableList<Log> items){
        logTable.setItems(items);
        logger.info("Logs saved into the table");
    }
    //-----------------------------------Format and set the tours to list----------------------------------------------

    //set the tour items into the ListView
    /**
     * the following method takes
     * as the ListView Panel where all the tour will be shown
     * and inserts all of the created tour in this List
     * @param listView List of Tours
     * @param items the ObservableList where we save the tour from DB
     * */
    public void setToursToList(ListView<Tour> listView, ObservableList<Tour> items){
        listView.setItems(items);
        logger.info("Tour sved into the Listview");
    }

    /**
     * insert items to choice box, so you can choose where you want to search
     * @param search the choicebox
     * */
    public void insertToChoicebox(ChoiceBox<String> search, ObservableList<String> items){
        String t = "Tours";
        String l = "Logs";
        items.addAll(t,l);
        search.getItems().addAll(items);
        logger.info("items to ChoiceBox inserted");
    }

    /**
     * the method which formats the cells to show the name
     * and has as a parameter the list of all tours#
     * @param listView List of Tours
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
        logger.info("ListView Cells formated");
    }

    /**
     * this method sets the listener to the tour
     * @param listView List of Tours
     * */
    //set the listener to the tour
    public void setTourListener(ListView<Tour> listView){
        listView.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if(newValue!=null && oldValue != newValue) {
                currentTour = newValue;
            }
        }));
        logger.info("tourListener set");
    }

    /**
     * the following method takes
     * @param windowName as String which contains the url to the new window
     * @param windowTitle as String whicht contains the title of the window
     * */
    public Initializable newWindow(String windowName, String windowTitle){
        Parent root=null;

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainWindowController.class.getResource(windowName+".fxml"));

        try {
            root = loader.load();
            logger.info("FMXLLoader loaded successfully");
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("could not load the FMXLLoader!!");
        }

        Stage stage = new Stage();
        stage.setTitle(windowTitle);
        stage.setScene(new Scene(root));
        stage.show();
        logger.info(windowName + "window was created");
        return loader.getController();
    }

    /**
     * this method makes it possible to clear the content
     * which can be in the search TextField
     * through a button
     * @param choice Choicebox for Search
     * @param manager JavaAppManager
     * @param tourItem Tour Observable List
     * @param logItem Log Observable List
     * */
    public void clearSearchField(ChoiceBox<String> choice, JavaAppManager manager,ObservableList<Tour> tourItem
            ,ObservableList<Log> logItem) throws SQLException {
        if(choice.getValue().equals("Tours")){
            //clear the list with the searched Items
            tourItem.clear();
            //clear the input field
            searchInput.setValue("");
            //get tours form db and save them in ObservableList
            manager.GetData(tourItem);
        }else if(choice.getValue().equals("Logs")){
            //clear the list with the searched Items
            logItem.clear();
            //clear the input field
            searchInput.setValue("");
            //get tours form db and save them in ObservableList
            manager.GetAllLogs(logItem);
        }
        logger.info("the input in the search field cleared");
    }
    /**
     * this method makes it possible to search the content
     * which is typed in the search TextField
     * through a button
     * @param choice Choicebox for Search
     * @param manager JavaAppManager
     * @param tourItem Tour Observable List
     * @param logItem Log Observable List
     * */
    public void searchTheInput(ChoiceBox<String> choice, JavaAppManager manager,ObservableList<Tour> tourItem
            ,ObservableList<Log> logItem) throws SQLException {
        if(choice.getValue().equals("Tours")){
            //clear the list with the searched Items
            tourItem.clear();
            // add to a list all the searched tours
            List<Tour> tours = manager.searchTourItem(searchInput.getValue(),false);
            //insert these searched tours to the ObservableList
            tourItem.addAll(tours);

        }else if(choice.getValue().equals("Logs")){
            //clear the ObservableList
            logItem.clear();
            // add to a list all the searched tours
            List<Log> logs = manager.searchLogItem(searchInput.getValue(),false);
            //insert these searched tours to the ObservableList
            logItem.addAll(logs);
        }
        logger.info("search for the inserted input executed");
    }
}
