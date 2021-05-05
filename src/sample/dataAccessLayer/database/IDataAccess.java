package sample.dataAccessLayer.database;

import javafx.collections.ObservableList;
import sample.models.Log;
import sample.models.Tour;

import java.sql.Connection;
import java.sql.SQLException;

public interface IDataAccess {
    Connection getConnection();
    void openConnection(String url, String user, String pass);
    void closeConnection() throws SQLException;

    public void GetTours(ObservableList<Tour> tourObservableList);

    void addTourData(Tour tour);
    void editTourData(Tour tour, String id);
    void deleteTourData(String id);

    void addLogData(Log logs,String identific);
    void editLogData(Log logs);
    void deleteLogData(String name);

}
