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

    boolean checkIfTourExists(String id);

    boolean addTourData(Tour tour);
    boolean editTourData(Tour tour, String id);
    boolean deleteTourData(String id);

    void GetAllLogs(ObservableList<Log> tourObservableList);
    void GetLogsForTour(ObservableList<Log> logObservableList,String id);
    List<Log> ReportGetLogs(String id);
    List<Log> GetLogsWithoutSaving();
    boolean addLogData(Log logs,String identific);
    boolean editLogData(Log logs);
    boolean deleteLogData(String name);
    boolean checkIfLogsExists(String name);
    boolean checkIfTourHasLogs(ObservableList<Log> logObservableList, String id);
    boolean delTheLogsOfTour(String id);

}
