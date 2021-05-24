package sample.views;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.SneakyThrows;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import sample.Main;
import sample.businessLayer.ExportFile.Import;
import sample.businessLayer.configuration.Configuration;
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

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable{

    private static final Logger logger = LogManager.getLogger(MainWindowController.class);

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
    public ImageView MapImageView;
    public AnchorPane ImagePane;
    //Buttons
    public Button delTourBtn;
    public Button editTourBtn;
    public Button addLog;
    public Button delLogBtn;
    public Button editLog;
    public Button tourRep;


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


    public MainWindowController(){}

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("-->MainWindowController init");
        logger.info("MainWindowController initialized");
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

        //Disable Buttons when no tour bzw. log selected
        delTourBtn.disableProperty().bind(TourListView.getSelectionModel().selectedItemProperty().isNull());
        editTourBtn.disableProperty().bind(TourListView.getSelectionModel().selectedItemProperty().isNull());
        tourRep.disableProperty().bind(TourListView.getSelectionModel().selectedItemProperty().isNull());
        addLog.disableProperty().bind(TourListView.getSelectionModel().selectedItemProperty().isNull());
        delLogBtn.disableProperty().bind(LogTableView.getSelectionModel().selectedItemProperty().isNull());
        editLog.disableProperty().bind(LogTableView.getSelectionModel().selectedItemProperty().isNull());


    }

    @FXML
    public void selectedTour(MouseEvent mouseEvent) throws IOException {
        mainWindowViewModel.diplaySelect(TourListView);
        mainWindowViewModel.readDescription(TourListView);
        //get the logs for a certain tour
        mainWindowViewModel.showOnlyLogsOfTour(TourListView,logsTableItems,manager);
        mainWindowViewModel.showImage(TourListView,MapImageView, manager);
        logger.info("Tour in the ListView selected");
    }

    //---------------------------------------delete a tour/log from the list/table-------------------------------------
    /** you can delete a certain Tour */
    @FXML
    public void deleteTour(MouseEvent mouseEvent) throws SQLException {
       mainWindowViewModel.deleteSelectedTour(TourListView,logsTableItems,manager);
       logger.info("Delete-Tour Button clicked");
    }

    /** you can delete a certain Log */
    public void deleteLog(MouseEvent mouseEvent) throws SQLException {
       mainWindowViewModel.deleteSelectedLog(LogTableView,manager);
        logger.info("Delete-TourLog Button clicked");
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
        logger.info("Data with Table column connected");
    }

    //----------------------------------------Connect different Windows------------------------------------------------

    /**
     * the method which connects the main window with
     * the addTour window, when you click the add button
     * */
    public void addTourWindow(ActionEvent actionEvent) {
        AddTourController addTourController = (AddTourController) mainWindowViewModel.newWindow("TourViews/addTour","Add Tour");
        addTourController.mainWindowController = this;
        logger.info("addTour window opened");
    }

    /**
     * The method is used to connect the main window with
     * the editTour window, when you click the edit button
     * */
    public void editTourWindow(ActionEvent actionEvent) {
        EditTourController editTourController = (EditTourController) mainWindowViewModel.newWindow("TourViews/editTour","Edit Tour");
        editTourController.mainWindowController = this;
        logger.info("editTour window opened");
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
        logger.info("addTourLog window opened");

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
        logger.info("editTourLog window opened");

        logG = LogTableView.getSelectionModel().getSelectedItem();
        String logInfoToEdit = mainWindowViewModel.setLogDataToEdit(logG);
        editLogController.editLogViewModel.initTheEdit(logInfoToEdit);
    }

    public void instructionOpen(ActionEvent actionEvent) {
        InstructionController instructionController = (InstructionController) mainWindowViewModel.newWindow("instructions","Help");
        instructionController.mainWindowController = this;
        logger.info("Instruction window opened");

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
        logger.info("close the Application button clicked");
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
        logger.info("Clear-Button clicked");
    }

    /**
     * this method is used to search for a certain tour or tour log in the List
     * depending on the choicebox
     * */
    @FXML
    public void searchItems(ActionEvent actionEvent) throws SQLException {
        mainWindowViewModel.searchTheInput(SearchChoicebox,manager,tourListItems,logsTableItems);
        logger.info("Search-Button clicked");

    }

    /**
     * this method is used to generate a report
     * for a certain tour
     * */
    @FXML
    public void createReport(ActionEvent actionEvent) {
        manager.genReport(TourListView, Configuration.getPdfPath(),manager);
        logger.info("Report-Button clicked");

    }
    @FXML
    public void exportToFile(ActionEvent actionEvent) {
        boolean isExported = manager.exportToJSON(manager.GetToursWithLogs(),Configuration.getExportFile());
        if(isExported){
            logger.info("Tours exported as a JSON File");
        }
    }

    @FXML
    public void importToFile(ActionEvent actionEvent) {
        try {
            mainWindowViewModel.ImportFile(manager,tourListItems,TourListView,LogTableView,logsTableItems);
            logger.info("JSON-File imported successfully");
        } catch (SQLException throwables) {
            logger.error(throwables.getMessage());
            throwables.printStackTrace();
        }
    }
}
