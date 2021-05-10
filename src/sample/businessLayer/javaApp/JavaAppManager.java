package sample.businessLayer.javaApp;

import javafx.collections.ObservableList;
import sample.models.Log;
import sample.models.Tour;

import java.sql.SQLException;
import java.util.List;

public interface JavaAppManager {

    void SetDataItems(Tour tour) throws SQLException;
    List<Tour> GetTourItems();
    void GetData(ObservableList<Tour> tour) throws SQLException;
    void delData(String id) throws SQLException;
    void editData(Tour tour, String id);

    void GetAllLogs(ObservableList<Log> logs) throws SQLException;
    void GetLogsForTour(ObservableList<Log> logObservableList,String id);
    List<Log> GetLogItems();
    void setLogItems(Log logItems, String id);
    void editLogItems(Log log);
    void delLogItem(String name) throws SQLException;
    boolean checkIfTourHasLog(ObservableList<Log> logObservableList, String id);
    void deleteTheLogsOfTour(String id);

    List<Tour> searchTourItem(String tourName, boolean caseSensitive) throws SQLException;
    List<Log> searchLogItem(String logName, boolean caseSensitive) throws SQLException;
}
