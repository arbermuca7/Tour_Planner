package sample.views;

import javafx.beans.binding.Bindings;
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
import sample.viewModels.MainWindowViewModel;
import sample.viewModels.TourVM.EditTourViewModel;
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

    public MainWindowViewModel mainWindowViewModel = new MainWindowViewModel();
    
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
        mainWindowViewModel.setToursToList(TourListView,tourListItems);
        //associate logData with the table columns
        connectDataWithColumns();
        //set all the logs into the observable list and then in tho the Table view, when we get them from database
        manager.GetAllLogs(logsTableItems);
        mainWindowViewModel.setLogsToTable(LogTableView,logsTableItems);
        //format cells to show name
        mainWindowViewModel.setFormatCells(TourListView);
        // set the listener to the tour
        mainWindowViewModel.setTourListener(TourListView);
        //insert to choicebox
        mainWindowViewModel.insertToChoicebox(SearchChoicebox,choiceboxItems);

        //Binding the Controller Components with the VM Properties
        InputTextField.textProperty().bindBidirectional(mainWindowViewModel.getSearchInput());
        Bindings.bindBidirectional(OutputNameLabel.textProperty(), mainWindowViewModel.getOutputNameTour());
        Bindings.bindBidirectional(tourLogs.textProperty(), mainWindowViewModel.getOutputTourLogs());
        Bindings.bindBidirectional(DescriptionLabel.textProperty(), mainWindowViewModel.getOutputDescription());
    }

    @FXML
    public void selectedTour(MouseEvent mouseEvent) {
        mainWindowViewModel.diplaySelect(TourListView);
        mainWindowViewModel.readDescription(TourListView);
        //get the logs for a certain tour
        mainWindowViewModel.showOnlyLogsOfTour(TourListView,logsTableItems,manager);
    }

    //---------------------------------------delete a tour/log from the list/table-------------------------------------
    /** you can delete a certain Tour */
    @FXML
    public void deleteTour(MouseEvent mouseEvent) throws SQLException {
       mainWindowViewModel.deleteSelectedTour(TourListView,logsTableItems,manager);
    }

    /** you can delete a certain Log */
    public void deleteLog(MouseEvent mouseEvent) throws SQLException {
       mainWindowViewModel.deleteSelectedLog(LogTableView,manager);
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

    //----------------------------------------Connect different Windows------------------------------------------------

    /**
     * the method which connects the main window with
     * the addTour window, when you click the add button
     * */
    public void addTourWindow(ActionEvent actionEvent) {
        AddTourController addTourController = (AddTourController) mainWindowViewModel.newWindow("TourViews/addTour","Add Tour");
        addTourController.mainWindowController = this;
    }

    /**
     * The method is used to connect the main window with
     * the editTour window, when you click the edit button
     * */
    public void editTourWindow(ActionEvent actionEvent) {
        EditTourController editTourController = (EditTourController) mainWindowViewModel.newWindow("TourViews/editTour","Edit Tour");
        editTourController.mainWindowController = this;

        tourG = TourListView.getSelectionModel().getSelectedItem();
        String getInfoToEdit = mainWindowViewModel.setTourDataToEdit(tourG);
        //show the certain tour data in the edit window
        editTourController.editTourViewModel.initEdit(getInfoToEdit);
    }

    /**
     * The method is used to open the addLog window,
     * when you click at the edit button
     **/
    public void addLogWindow(ActionEvent actionEvent) {
        AddLogController addLogController = (AddLogController) mainWindowViewModel.newWindow("LogViews/addLog","Add Log");
        addLogController.mainWindowController = this;

        tourG = TourListView.getSelectionModel().getSelectedItem();
        String getInfoToEdit = mainWindowViewModel.setTourDataToEdit(tourG);
        addLogController.addLogViewModel.getClickedID(getInfoToEdit);
    }

    /**
     * The method is used to open the editLog window,
     * when you click at the edit button
     **/
    public void editLogWindow(ActionEvent actionEvent) {
        EditLogController editLogController = (EditLogController) mainWindowViewModel.newWindow("LogViews/editLog","Edit Log");
        editLogController.mainWindowController = this;

        logG = LogTableView.getSelectionModel().getSelectedItem();
        String logInfoToEdit = mainWindowViewModel.setLogDataToEdit(logG);
        editLogController.editLogViewModel.initTheEdit(logInfoToEdit);
    }

    public void instructionOpen(ActionEvent actionEvent) {
        InstructionController instructionController = (InstructionController) mainWindowViewModel.newWindow("instructions","Help");
        instructionController.mainWindowController = this;
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
        mainWindowViewModel.clearSearchField(SearchChoicebox,manager,tourListItems,logsTableItems);
    }

    /**
     * this method is used to search for a certain tour or tour log in the List
     * depending on the choicebox
     * */
    @FXML
    public void searchItems(ActionEvent actionEvent) throws SQLException {
        mainWindowViewModel.searchTheInput(SearchChoicebox,manager,tourListItems,logsTableItems);
    }
}
