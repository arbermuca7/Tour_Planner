package sample.businessLayer.javaApp;

import javafx.collections.ObservableList;
import sample.models.Log;
import sample.models.Tour;

import java.sql.SQLException;
import java.util.List;

public interface JavaAppManager {

    //Tours methods
    void SetDataItems(Tour tour) throws SQLException;
    List<Tour> GetTourItems();
    void GetData(ObservableList<Tour> tour) throws SQLException;
    boolean delData(String id) throws SQLException;
    boolean editData(Tour tour, String id);

    //Log methods
    void GetAllLogs(ObservableList<Log> logs) throws SQLException;
    void GetLogsForTour(ObservableList<Log> logObservableList,String id);
    List<Log> ReportGetLogsForTour(String id);
    List<Log> GetLogItems();
    void setLogItems(Log logItems, String id) throws SQLException;
    boolean editLogItems(Log log);
    boolean delLogItem(String name) throws SQLException;
    boolean checkIfTourHasLog(ObservableList<Log> logObservableList, String id);
    boolean deleteTheLogsOfTour(String id);

    //Search Methods
    List<Tour> searchTourItem(String tourName, boolean caseSensitive) throws SQLException;
    List<Log> searchLogItem(String logName, boolean caseSensitive) throws SQLException;
}
