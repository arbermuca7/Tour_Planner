package sample.businessLayer.javaApp;

import javafx.collections.ObservableList;
import sample.models.Tour;

import java.sql.SQLException;
import java.util.List;

public interface JavaAppManager {

    void SetTourItems(Tour tour) throws SQLException;
    void GetTour(ObservableList<Tour> tour) throws SQLException;
    List<Tour> GetTourItems();
    List<Tour> searchTourItem(String tourName, boolean caseSensitive) throws SQLException;

    //void GetTour(ObservableList<Tour> tourListItems) throws SQLException;
}
