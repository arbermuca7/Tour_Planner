package sample.views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lombok.Getter;
import sample.businessLayer.javaApp.JavaAppManager;
import sample.businessLayer.javaApp.JavaAppManagerFactory;
import sample.models.Log;
import sample.models.Tour;
import sample.viewModels.TourVM.AddTourViewModel;
import sample.viewModels.TourVM.EditTourViewModel;
import sample.viewModels.HomeWindowViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.beans.binding.Bindings;
import sample.views.TourViews.AddTourController;

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
    public ListView<Tour> TourListView = new ListView<>();
    public TableView<Log> LogTableView;
    public Tab DescriptionTab;
    public Tab RouteTab;
    public Label DescriptionLabel;

    @Getter private final ObservableList<Tour> tourListItems = FXCollections.observableArrayList();
    private JavaAppManager manager;
    @Getter private String identification = "";
    public Tour currentTour;
    public int selectedIndex = -1;

    public MainWindowController()
    {
        System.out.println("Main Controller");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Controller MainWindow init");
        //Tour tour = new Tour("ha12sh","Sample","hey whats up","vienna","graz",220.5);
        //Tour tour1 = new Tour("ha12sh","Tour2","hey whats up","vienna","shkodra",1200.5);
        //Tour tour2 = new Tour("ha12sh","Tour3","hey whats up","shkodra","tirana",120);

        //------------------The init of the tours---------------------------
        //tourListItems.addAll(tour,tour1,tour2);
        //tourListItems.addAll(tour);
        //tourListItems.addAll(addTourController.getTour());
        manager = JavaAppManagerFactory.GetManager();
        //set the tour items into the ListView
        setToursToList();
        //format cells to show name
        setFormatCells();
        // set the listener to the tour
        setTourListener();

    }
    @FXML
    public void displaySelect(MouseEvent mouseEvent) {
        diplaySelect(TourListView);
    }

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

    /**
     * you can read the trip description
     * */
    @FXML
    public void showDescription(Event event) {
        readDescription(TourListView);
    }

    //it is a method that shows us what is the description of a certain tour
    /**
     * the following method takes
     * @param list as the ListView Panel where all the tour will be shown
     * and when we select a tour it shows us all the tour description in the Tab
     * */
    public void readDescription(ListView<Tour> list){
        String name = list.getSelectionModel().getSelectedItem().getT_Name();
        String start = list.getSelectionModel().getSelectedItem().getStartPoint();
        String destination = list.getSelectionModel().getSelectedItem().getDestination();
        double distance = list.getSelectionModel().getSelectedItem().getT_Distance();
        String description = list.getSelectionModel().getSelectedItem().getDescription();
        if(!start.isEmpty() && !destination.isEmpty() && distance!=0){
            this.DescriptionLabel.textProperty().set(
                    "The Trip "+name+" started in "+start+" and finished in "+destination+"\n"
                            +"The road length was "+distance+" km \n"
                            +"The Trip description: "+description);
        }
    }

    //---------------------------------------delete a tour from the list-----------------------------------------------
    /**
     * you can delete a certain Tour
     * */
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
        listView.getItems().remove(selectedIndex);
        //remove also from database
        //String ident = listView.getSelectionModel().getSelectedItem().getIdentification();
        //dataAccess.deleteTourData(ident);
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
        if(!TourListView.getItems().isEmpty()){
            System.out.println("List view: "+TourListView.getItems().get(0).getT_Name());
        }else{
            System.out.println("nothing..................");
        }
        //listView.setItems(addTourViewModel.getTourListItems());
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

    /**
     * this method sets the listener to the tour
     * */
    //set the listener to the tour
    public void setTourListener(){
        TourListView.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if(newValue!=null && oldValue != newValue) {
                currentTour = newValue;
            }
        }));
    }

    //----------------------------------------Connect different Windows-------------------------------------------------
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
        identification = TourListView.getSelectionModel().getSelectedItem().getIdentification();
        newWindow("TourViews/editTour","Edit Tour");
    }
    /**
     * The method is used to open the addLog window,
     * when you click at the edit button
     **/
    public void addLogWindow(ActionEvent actionEvent) {
        newWindow("LogViews/addLog","Add Log");
    }
    /**
     * The method is used to open the editLog window,
     * when you click at the edit button
     **/
    public void editLogWindow(ActionEvent actionEvent) {
        newWindow("LogViews/editLog","Edit Log");
    }
    /**
     * the following method takes
     * @param windowName as String which contains the url to the new window
     * @param windowTitle as String whicht contains the title of the window
     * */
    public Initializable newWindow(String windowName,String windowTitle){
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
     * this method makes it possible to clear the content
     * which can be in the TextField
     * through a button
     * */
    @FXML
    public void clearInput(ActionEvent actionEvent) {
        //ObservableList<Tour> list = FXCollections.observableArrayList();
        //list.addAll(tourListItems);
        //tourListItems.clear();
        InputTextField.textProperty().setValue("");
        /*for (Tour tour : list) {
            manager.SetTourItems(tour);
        }*/
        //List<Tour> touritems = manager.GetTourItems();
        //tourListItems.addAll(touritems);
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

    public void searchItems(ActionEvent actionEvent) {
        //tourListItems.clear();
        //List<Tour> touritems = manager.searchTourItem(InputTextField.textProperty().getValue(),false);
        //tourListItems.addAll(touritems);
    }
}
