package sample.dataAccessLayer.database;

import sample.models.Log;
import sample.models.Tour;

import java.sql.SQLException;

public interface IDataAccess {
    void openConnection() throws SQLException;
    void closeConnection() throws SQLException;

    void addTourData(Tour tour);
    void editTourData(Tour tour, String id);
    void deleteTourData(String id) throws SQLException;

    void addLogData(Log logs,Tour tour);
    void editLogData(Log logs);
    void deleteLogData(String name) throws SQLException;

}
