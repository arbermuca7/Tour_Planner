package sample.dataAccessLayer.database;

import sample.models.Log;
import sample.models.Tour;

import java.sql.SQLException;
import java.util.List;

public interface IDataAccess {
    void openConnection() throws SQLException;
    void closeConnection() throws SQLException;

    public List<Tour> GetTours();

    void addTourData(Tour tour);
    void editTourData(Tour tour, String id);
    void deleteTourData(String id) throws SQLException;

    void addLogData(Log logs,Tour tour);
    void editLogData(Log logs);
    void deleteLogData(String name) throws SQLException;

}
