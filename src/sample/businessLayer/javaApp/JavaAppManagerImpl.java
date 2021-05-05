package sample.businessLayer.javaApp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.dataAccessLayer.DAOs.TourDAO;
import sample.models.Tour;
import sample.views.MainWindowController;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JavaAppManagerImpl implements JavaAppManager{

    List<Tour> tourItems = new ArrayList<>();
    TourDAO tourDAO = new TourDAO();
    MainWindowController mainWindowController;

    @Override
    public void SetTourItems(Tour tour) throws SQLException {
        tourDAO.addTourToDB(tour);
        //tourItems.add(tour);
    }
    @Override
    public List<Tour> GetTourItems() {
        //tourDAO.GetTours(ObservableList);
        return tourItems;
    }
    @Override
    public void GetTour(ObservableList<Tour> tour) throws SQLException {
        tourDAO.GetTours(tour);
    }
    @Override
    public List<Tour> searchTourItem(String tourName, boolean caseSensitive) throws SQLException {
        ObservableList<Tour> list = FXCollections.observableArrayList();
        list.addAll(mainWindowController.getTourListItems());
        for (Tour tour : list) {
            SetTourItems(tour);
        }
        List<Tour> tourList = GetTourItems();
        if(caseSensitive){
            return tourList
                    .stream()
                    .filter(x -> x.getT_Name().contains(tourName))
                    .collect(Collectors.toList());
        }
        return tourList
                .stream()
                .filter(x -> x.getT_Name().toLowerCase().contains(tourName.toLowerCase()))
                .collect(Collectors.toList());
    }
}
