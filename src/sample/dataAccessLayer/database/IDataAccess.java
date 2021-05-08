package sample.dataAccessLayer.database;

import javafx.collections.ObservableList;
import sample.models.Log;
import sample.models.Tour;

import java.sql.Connection;
import java.util.List;

public interface IDataAccess {
    Connection getConnection();

    void GetTours(ObservableList<Tour> tourObservableList);
    List<Tour> GetToursWithoutSaving();

    void addTourData(Tour tour);
    void editTourData(Tour tour, String id);
    void deleteTourData(String id);

    void GetAllLogs(ObservableList<Log> tourObservableList);
    void GetLogsForTour(ObservableList<Log> logObservableList,String id);
    void addLogData(Log logs,String identific);
    void editLogData(Log logs);
    void deleteLogData(String name);

}
