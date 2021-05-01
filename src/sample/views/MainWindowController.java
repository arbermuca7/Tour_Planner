package sample.views;

import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import lombok.Getter;
import sample.models.Log;
import sample.models.Tour;
import sample.viewModels.TourVM.EditTourViewModel;
import sample.viewModels.HomeWindowViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.beans.binding.Bindings;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable{
    public HomeWindowViewModel mainViewModel = new HomeWindowViewModel();
    public TextField InputTextField;
    public Label tourLogs;
    public Label OutputNameLabel;
    public MenuItem CloseAppMenuItem;
    public ListView<Tour> TourListView = new ListView<>();
    public TableView<Log> LogTableView;
    public Tab DescriptionTab;
    public Tab RouteTab;

    @Getter private String identification = "";

    EditTourViewModel editTourViewModel = new EditTourViewModel();

    public MainWindowController()
    {
        System.out.println("Main Controller");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Controller MainWindow init");
        //------------------The init of the tours---------------------------
        //set the tour items into the ListView
        mainViewModel.setToursToList(TourListView);
        //format cells to show name
        mainViewModel.setFormatCells(TourListView);
        // set the listener to the tour
        mainViewModel.setTourListener(TourListView);


        InputTextField.textProperty().bindBidirectional(mainViewModel.getSearchInputTours());
        //------------------------------------------------------------------
        Bindings.bindBidirectional(OutputNameLabel.textProperty(), mainViewModel.getOutputNameTour());
        Bindings.bindBidirectional(tourLogs.textProperty(), mainViewModel.getOutputTourLogs());
    }
    @FXML
    public void displaySelect(MouseEvent mouseEvent) {
        mainViewModel.diplaySelect(TourListView);
    }
    /**
     * you can delete a certain Tour
     * */
    @FXML
    public void deleteTour(MouseEvent mouseEvent) throws SQLException {
        mainViewModel.deleteSelectedTour(TourListView);
    }
    /**
     * you can edit a certain Tour
     * */
    /**
     * the method which connects the main window with
     * the addTour window, when you click the add button
     * */
    public void addTourWindow(ActionEvent actionEvent) {
        mainViewModel.newWindow("TourViews/addTour","Add Tour");
    }
    /**
     * The method is used to connect the main window with
     * the editTour window, when you click the edit button
     * */
    public void editTourWindow(ActionEvent actionEvent) {
        identification = TourListView.getSelectionModel().getSelectedItem().getIdentification();
        mainViewModel.newWindow("TourViews/editTour","Edit Tour");
    }
    /**
     * The method is used to open the addLog window,
     * when you click at the edit button
     **/
    public void addLogWindow(ActionEvent actionEvent) {
        mainViewModel.newWindow("LogViews/addLog","Add Log");
    }

    /**
     * The method is used to open the editLog window,
     * when you click at the edit button
     **/
    public void editLogWindow(ActionEvent actionEvent) {
        mainViewModel.newWindow("LogViews/editLog","Edit Log");
    }
    /**
     * this method makes it possible to clear the content
     * which can be in the TextField
     * through a button
     * */
    @FXML
    public void clearInput(ActionEvent actionEvent) {
        InputTextField.textProperty().setValue("");
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

}
