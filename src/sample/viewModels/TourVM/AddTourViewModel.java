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

public class AddTourViewModel {
/*
    public AddTourViewModel() {
        System.out.println("AddTourViewModel");
    }

    @Getter
    private final StringProperty inputIdentific = new SimpleStringProperty("");
    @Getter
    private final StringProperty inputName = new SimpleStringProperty("");
    @Getter
    private final StringProperty inputStart = new SimpleStringProperty("");
    @Getter
    private final StringProperty inputDestination = new SimpleStringProperty("");
    @Getter
    private final StringProperty inputDistance = new SimpleStringProperty("");
    @Getter
    private final StringProperty inputDescription = new SimpleStringProperty("");

    HomeWindowViewModel homeWindowViewModel = new HomeWindowViewModel();
    MainWindowController mainWindowController = new MainWindowController();
    IDataAccess dataAccess = new DatabaseAccess();

    public String tourData() {
        String ident = this.inputIdentific.get();
        String name = this.inputName.get();
        String start = this.inputStart.get();
        String dest = this.inputDestination.get();
        String dist = this.inputDistance.get();
        String desc = this.inputDescription.get();

        double distance = 0;
        if (!dist.isEmpty())
            distance = Double.parseDouble(dist);

        //create the tour
        Tour tour = new Tour(ident, name, desc, start, dest, distance);

        //add tour to Observeable list in HomeWindowViewModel
        homeWindowViewModel.getTourListItems().addAll(tour);
        //Save tour to listview
        homeWindowViewModel.setToursToList(mainWindowController.TourListView);
        //add the tour to database
        //dataAccess.addTourData(tour);
        return "";
    }
    */
}

