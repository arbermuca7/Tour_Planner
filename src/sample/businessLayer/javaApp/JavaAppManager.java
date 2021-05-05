package sample.businessLayer.javaApp;

import javafx.collections.ObservableList;
import sample.models.Tour;

import java.sql.SQLException;
import java.util.List;

public interface JavaAppManager {

    void SetDataItems(Tour tour) throws SQLException;
    void GetData(ObservableList<Tour> tour) throws SQLException;
    void delData(String id) throws SQLException;
    void editData(Tour tour, String id);
    List<Tour> GetTourItems();
    List<Tour> searchTourItem(String tourName, boolean caseSensitive) throws SQLException;

    //void GetTour(ObservableList<Tour> tourListItems) throws SQLException;
}
