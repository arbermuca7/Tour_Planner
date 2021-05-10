package sample.views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.SneakyThrows;
import sample.businessLayer.javaApp.JavaAppManager;
import sample.businessLayer.javaApp.JavaAppManagerFactory;
import sample.models.Log;
import sample.models.Tour;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import sample.views.LogViews.AddLogController;
import sample.views.LogViews.EditLogController;
import sample.views.TourViews.AddTourController;
import sample.views.TourViews.EditTourController;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable{

    public TextField InputTextField;
    public Label tourLogs;
    public Label OutputNameLabel;
    public MenuItem CloseAppMenuItem;
    public ChoiceBox<String> SearchChoicebox;
    public ListView<Tour> TourListView = new ListView<>();
    public TableView<Log> LogTableView;
    public Tab DescriptionTab;
    public Tab RouteTab;
    public Label DescriptionLabel;

    //TableColumns add
    public TableColumn<Log, String> date;
    public TableColumn<Log, String> logName;
    public TableColumn<Log, String> duration;
    public TableColumn<Log, Double> logDistance;
    public TableColumn<Log, Integer> avgSpeed;
    public TableColumn<Log, Float> fuel;
    public TableColumn<Log, String> routeType;
    public TableColumn<Log, Integer> rating;
    public TableColumn<Log, String> travelMode;
    public TableColumn<Log, Boolean> tollRoads;
    public TableColumn<Log, Boolean> restingPlace;


    @Getter private final ObservableList<String> choiceboxItems = FXCollections.observableArrayList();
    @Getter private final ObservableList<Tour> tourListItems = FXCollections.observableArrayList();
    @Getter private final ObservableList<Log> logsTableItems = FXCollections.observableArrayList();


    @Getter private JavaAppManager manager;

    //@Getter private String identification = "";
    @Getter private Tour tourG;
    @Getter private Log logG;

    public Tour currentTour;
    public int selectedIndex = -1;

    public MainWindowController(){}

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("-->MainWindowController init");
        manager = JavaAppManagerFactory.GetManager();
        //set the tour items into the ListView and take them from DB
        manager.GetData(tourListItems);
        setToursToList();
        //associate logData with the table columns
        connectDataWithColumns();
        //set all the logs into the observable list and then in tho the Table view, when we get them from database
        manager.GetAllLogs(logsTableItems);
        setLogsToTable();
        //format cells to show name
        setFormatCells();
        // set the listener to the tour
        setTourListener();

        //insert to choicebox
        insertToChoicebox(SearchChoicebox);
    }

    @FXML
    public void selectedTour(MouseEvent mouseEvent) {
        diplaySelect(TourListView);
        readDescription(TourListView);
        //get the logs for a certain tour
        showOnlyLogsOfTour(TourListView);
    }

    /**
     * this method takes as
     * @param listView as ListView and when you select a certain
     * tour the Label which are defined will be set with the tour name
     * */
    // display the selected tour to these Labels
    public void diplaySelect(ListView<Tour> listView){
        String name = listView.getSelectionModel().getSelectedItem().getT_Name();
        if(name != null || !name.isEmpty()){
            this.OutputNameLabel.textProperty().set("Titel: "+name);
            this.tourLogs.textProperty().set("Logs for "+name+": ");
        }else{
            System.out.println("nothing selected");
        }
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
            this.DescriptionLabel.setText(
                    "The Trip \""+tour.getT_Name()+"\" started in "+tour.getStartPoint()+" and finished in "+tour.getDestination()+"\n"
                            +"The road length was "+tour.getT_Distance()+" km \n"
                            +"The Trip description: "+tour.getDescription());
        }
    }

    /**
     * the following method makes it possible
     * to show only the log connected to a certain Tour
     * @param toursList  the List of Tours
     * */
    public void showOnlyLogsOfTour(ListView<Tour> toursList){
        Tour tour = toursList.getSelectionModel().getSelectedItem();
        String identificationTour = tour.getIdentification();
        //clear the observable list
        logsTableItems.clear();
        //insert again the other to the logs
        manager.GetLogsForTour(logsTableItems,identificationTour);
    }

    //---------------------------------------delete a tour/log from the list/table-------------------------------------
    /** you can delete a certain Tour */
    @FXML
    public void deleteTour(MouseEvent mouseEvent) throws SQLException {
        deleteSelectedTour(TourListView);
    }
    /**
     * the following method takes
     * @param listView as the ListView Panel where all the tour will be shown
     * and delete the selected tour
     * */
    //delete the selected tour
    public void deleteSelectedTour(ListView<Tour> listView) throws SQLException {
        selectedIndex = listView.getSelectionModel().getSelectedIndex();
        String ident = listView.getSelectionModel().getSelectedItem().getIdentification();
        listView.getItems().remove(selectedIndex);
        //remove also from database
        System.out.println("id of the selected tour to del:" + ident);
        if(manager.checkIfTourHasLog(logsTableItems,ident)){
            //remove the logs of this tour
            manager.deleteTheLogsOfTour(ident);
        }
        manager.delData(ident);
    }

    /** you can delete a certain Log */
    public void deleteLog(MouseEvent mouseEvent) throws SQLException {
       deleteSelectedLog(LogTableView);
    }

    /**
     * @param tableView Table view where the logs are shown
     * and delete the selected log
     * */
    public void deleteSelectedLog(TableView<Log> tableView) throws SQLException {
        selectedIndex = tableView.getSelectionModel().getSelectedIndex();
        String name = tableView.getSelectionModel().getSelectedItem().getName();
        tableView.getItems().remove(selectedIndex);
        //remove from db the log
        System.out.println("Name of Log to be deleted: "+name);
        manager.delLogItem(name);
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


        return logG.getName()+","+logG.getDate()+","
                +logG.getDuration()+","+distance+","+speed+","+fuel+","+tollRoad+","+logG.getTravel_mode()+","
                +logG.getRoute_type()+","+rating+","+rest;
    }

    //-----------------------------------Format and set the tours to list----------------------------------------------
    //set the tour items into the ListView
    /**
     * the following method takes
     * as the ListView Panel where all the tour will be shown
     * and inserts all of the created tour in this List
     * */
    public void setToursToList(){
        TourListView.setItems(tourListItems);
    }

    /**
     * the method which formats the cells to show the name
     * and has as a parameter the list of all tours
     * */
    //format cells to show name
    public void setFormatCells(){
        TourListView.setCellFactory((param -> new ListCell<Tour>(){
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

    /** this method sets the listener to the tour */
    //set the listener to the tour
    public void setTourListener(){
        TourListView.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if(newValue!=null && oldValue != newValue) {
                currentTour = newValue;
            }
        }));
    }

    //----------------------------------------Log management-----------------------------------------------------------

    /** this method connects the log data with the table columns */
    public void connectDataWithColumns(){
        date.setCellValueFactory(new PropertyValueFactory<Log, String>("date"));
        logName.setCellValueFactory(new PropertyValueFactory<Log, String>("name"));
        duration.setCellValueFactory(new PropertyValueFactory<Log, String>("duration"));
        logDistance.setCellValueFactory(new PropertyValueFactory<Log, Double>("distance"));
        avgSpeed.setCellValueFactory(new PropertyValueFactory<Log, Integer>("avg_speed"));
        fuel.setCellValueFactory(new PropertyValueFactory<Log, Float>("fuel_cost"));
        tollRoads.setCellValueFactory(new PropertyValueFactory<Log, Boolean>("toll_roads"));
        travelMode.setCellValueFactory(new PropertyValueFactory<Log, String>("travel_mode"));
        routeType.setCellValueFactory(new PropertyValueFactory<Log, String>("route_type"));
        rating.setCellValueFactory(new PropertyValueFactory<Log, Integer>("rating"));
        restingPlace.setCellValueFactory(new PropertyValueFactory<Log, Boolean>("resting_place"));
    }

    /**
     * this method takes all the Log inserted in the ObservableList
     * and adds them to the Tableview
     * */
    public void setLogsToTable(){
        LogTableView.setItems(logsTableItems);
    }
    //----------------------------------------Connect different Windows------------------------------------------------

    /**
     * the method which connects the main window with
     * the addTour window, when you click the add button
     * */
    public void addTourWindow(ActionEvent actionEvent) {
        AddTourController addTourController = (AddTourController) newWindow("TourViews/addTour","Add Tour");
        addTourController.mainWindowController = this;
    }

    /**
     * The method is used to connect the main window with
     * the editTour window, when you click the edit button
     * */
    public void editTourWindow(ActionEvent actionEvent) {
        EditTourController editTourController = (EditTourController) newWindow("TourViews/editTour","Edit Tour");
        editTourController.mainWindowController = this;

        tourG = TourListView.getSelectionModel().getSelectedItem();
        String getInfoToEdit = setTourDataToEdit(tourG);
        //identification = TourListView.getSelectionModel().getSelectedItem().getIdentification();
        //show the certain tour data in the edit window
        editTourController.initEdit(getInfoToEdit);
    }

    /**
     * The method is used to open the addLog window,
     * when you click at the edit button
     **/
    public void addLogWindow(ActionEvent actionEvent) {
        AddLogController addLogController = (AddLogController) newWindow("LogViews/addLog","Add Log");
        addLogController.mainWindowController = this;

        tourG = TourListView.getSelectionModel().getSelectedItem();
        String getInfoToEdit = setTourDataToEdit(tourG);
        addLogController.getClickedID(getInfoToEdit);
    }

    /**
     * The method is used to open the editLog window,
     * when you click at the edit button
     **/
    public void editLogWindow(ActionEvent actionEvent) {
        EditLogController editLogController = (EditLogController) newWindow("LogViews/editLog","Edit Log");
        editLogController.mainWindowController = this;

        logG = LogTableView.getSelectionModel().getSelectedItem();
        String logInfoToEdit = setLogDataToEdit(logG);
        editLogController.initTheEdit(logInfoToEdit);
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
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage stage = new Stage();
        stage.setTitle(windowTitle);
        stage.setScene(new Scene(root));
        stage.show();
        return loader.getController();
    }

    /**
     * you can close the application by clicking
     * in the Menu item "Exit"
     * */
    @FXML
    public void closeProgram(ActionEvent actionEvent) {
        CloseAppMenuItem.setOnAction(e -> {
            System.exit(0);
        });
    }

    //----------------------------------------Search Option------------------------------------------------------------

    /**
     * this method makes it possible to clear the content
     * which can be in the TextField
     * through a button
     * */
    @FXML
    public void clearInput(ActionEvent actionEvent) throws SQLException {
        if(SearchChoicebox.getValue().equals("Tours")){
            //clear the list with the searched Items
            tourListItems.clear();
            //clear the input field
            InputTextField.textProperty().setValue("");
            //get tours form db and save them in ObservableList
            manager.GetData(tourListItems);
        }else if(SearchChoicebox.getValue().equals("Logs")){
            //clear the list with the searched Items
            logsTableItems.clear();
            //clear the input field
            InputTextField.textProperty().setValue("");
            //get tours form db and save them in ObservableList
            manager.GetAllLogs(logsTableItems);
        }

    }

    /**
     * this method is used to search for a certain tour or tour log in the List
     * depending on the choicebox
     * */
    @FXML
    public void searchItems(ActionEvent actionEvent) throws SQLException {

        if(SearchChoicebox.getValue().equals("Tours")){
            //clear the ObservableList
            tourListItems.clear();
            // add to a list all the searched tours
            List<Tour> tours = manager.searchTourItem(InputTextField.textProperty().getValue(),false);
            //insert these searched tours to the ObservableList
            tourListItems.addAll(tours);
        }else if(SearchChoicebox.getValue().equals("Logs")){
            //clear the ObservableList
            logsTableItems.clear();
            // add to a list all the searched tours
            List<Log> logs = manager.searchLogItem(InputTextField.textProperty().getValue(),false);
            //insert these searched tours to the ObservableList
            logsTableItems.addAll(logs);
        }
    }

    /**
     * insert items to choice box, so you can choose where you want to search
     * @param search the choicebox
     * */
    public void insertToChoicebox(ChoiceBox<String> search){
        String t = "Tours";
        String l = "Logs";
        choiceboxItems.addAll(t,l);
        search.getItems().addAll(choiceboxItems);
    }

    public void instructionOpen(ActionEvent actionEvent) {
        InstructionController instructionController = (InstructionController) newWindow("instructions","Help");
        instructionController.mainWindowController = this;
    }
}
