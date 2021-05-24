package sample.businessLayer.javaApp;

import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import sample.models.Log;
import sample.models.Tour;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface JavaAppManager {

    //Tours methods
    void SetDataItems(Tour tour) throws SQLException;
    List<Tour> GetTourItems();
    List<Tour> GetToursWithLogs();
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

    //generate Report
    boolean genReport(ListView<Tour> tourListView, String savingFolder, JavaAppManager manager);
    boolean deleteReport(Tour tour);
    void getImageFromMap(Tour tour);
    Image showImage(Tour tour) throws IOException;
    boolean deleteImage(Tour tour);

    //export tours to json file
    boolean exportToJSON(List<Tour> tourList, String ordner);
    List<Tour> importToJSON(String ordner);

}
