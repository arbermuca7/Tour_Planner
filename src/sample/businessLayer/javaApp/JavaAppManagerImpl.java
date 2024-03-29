package sample.businessLayer.javaApp;

import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import sample.businessLayer.ExportFile.Export;
import sample.businessLayer.ExportFile.Import;
import sample.businessLayer.mapQuest.IMapQuest;
import sample.businessLayer.mapQuest.MapQuest;
import sample.businessLayer.reporting.IReportGeneration;
import sample.businessLayer.reporting.ReportGeneration;
import sample.dataAccessLayer.DAOs.*;
import sample.dataAccessLayer.fileAccess.FileAccess;
import sample.dataAccessLayer.fileAccess.IFileAccess;
import sample.models.Log;
import sample.models.Tour;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class JavaAppManagerImpl implements JavaAppManager{

    IDAO<Tour> daoTour = DAOFactory.getInstance("tour");
    IDAO<Log> daoLog = DAOFactory.getInstance("log");

    IReportGeneration reportGeneration = new ReportGeneration();

    IMapQuest map = new MapQuest();

    IFileAccess fileAccess = new FileAccess();

    Export exportFile = new Export();
    Import importFile = new Import();


    private static final Logger logger = LogManager.getLogger(JavaAppManagerImpl.class);

    LogsDAO logsDAO = new LogsDAO();

    /**
     * @param tour takes a Tour as parameter
     * and saves that tour to the database
     * */
    @Override
    public void SetDataItems(Tour tour) throws SQLException {
        String id = "";
        daoTour.addToDB(tour,id);
    }

    /**
     * takes the tours from the database but it doesn't saves them in a ObservableList
     * @return the list which contains all the Tour in the database
     * it is used for the searching method
     * */
    @Override
    public List<Tour> GetTourItems() {
        return daoTour.GetDataWithoutSave();
    }

    /**
     * takes the tours + logs from the database but it doesn't saves them in a List
     * @return the list which contains all the Tour in the database
     * it is used for the searching method
     * */
    @Override
    public List<Tour> GetToursWithLogs() {
        return daoTour.GetTWithL();
    }

    /**
     * @param tour as a ObservableList
     * takes the tours from the database and saves them in the ObservableList
     * */
    @Override
    public void GetData(ObservableList<Tour> tour) throws SQLException {
        daoTour.GetDatas(tour);
    }

    /**
     * @param id as the Tour Identification,
     * so it can be deleted from the database
     * */
    public boolean delData(String id) throws SQLException {
        return daoTour.deleteFromDB(id);
    }

    /**
     * @param tour as a Tour you want to modify
     * @param id as the Tour Identification
     * so you can update it in the database
     * */
    @Override
    public boolean editData(Tour tour, String id) {
        return daoTour.editInDB(tour, id);
    }

    /**
     * @param logs as a ObservableList
     * takes the logs from the database and saves them in the ObservableList
     * */
    @Override
    public void GetAllLogs(ObservableList<Log> logs) throws SQLException {
        daoLog.GetDatas(logs);
    }

    /**
     * @param logObservableList as the observable list
     * @param id as the tour id,
     * and wherewith we will select the logs for a certain tour
     * */
    @Override
    public void GetLogsForTour(ObservableList<Log> logObservableList, String id) {
        logsDAO.GetLogsForTour(logObservableList, id);
    }
    /**
     * @param id as the tour id,
     * and wherewith we will select the logs for a certain tour
     * */
    @Override
    public List<Log> ReportGetLogsForTour(String id){
        return logsDAO.ReportGetLogs(id);
    }

    /**
     * takes the logs from the database but it doesn't saves them in a ObservableList
     * @return the list which contains all the logs in the database
     * */
    @Override
    public List<Log> GetLogItems(){
        return daoLog.GetDataWithoutSave();
    }

    /**
     * @param logItems as a Log you want to modify
     * @param id as the Tour Identification,
     * so you can add a Log to a certain Tour in the Database and TableView
     * */
    @Override
    public void setLogItems(Log logItems, String id) throws SQLException {
        daoLog.addToDB(logItems,id);
    }

    /**
     * @param log as a Log you want to modify
     * so you can update it in the database
     * */
    @Override
    public boolean editLogItems(Log log) {
        String id = "";
        return daoLog.editInDB(log,id);
    }

    /**
     * @param name as the Log name,
     * so it can be deleted from the database
     * */
    @Override
    public boolean delLogItem(String name) throws SQLException {
       return daoLog.deleteFromDB(name);
    }

    /**
     * @param logObservableList as the observable list
     * @param id as the tour id,
     * and wherewith we will look if the certain tour has logs or not
     * @return true if there are logs connected to the certain tour
     * */
    @Override
    public boolean checkIfTourHasLog(ObservableList<Log> logObservableList, String id) {
        return logsDAO.checkIfTourHasLog(logObservableList,id);
    }

    /**
     * @param id as the tour id,
     * so it can be deleted from the database the log with a tour id
     * */
    @Override
    public boolean deleteTheLogsOfTour(String id) {
        return logsDAO.deleteTheLogsOfTour(id);
    }

    /**
     * this method searches for certain tours from ListView
     * @param tourName the String you want to search for
     * @param caseSensitive the boolean to tell the program if u want to search caseSensitive or not
     * @return List of the Tours which matches the String you want to search
     * */
    @Override
    public List<Tour> searchTourItem(String tourName, boolean caseSensitive) throws SQLException {
        //get TourList from DB but not saving in the ObservableList, but in a normal List
        List<Tour> tourList = GetTourItems();
        if(caseSensitive){
            logger.info("searching caseSensitive words in Tours");
            return tourList
                    .stream()
                    .filter(x -> x.getT_Name().contains(tourName))
                    .collect(Collectors.toList());
        }
        logger.info("searching non-caseSensitive words in Tours");
        return tourList
                .stream()
                .filter(x -> x.getT_Name().toLowerCase().contains(tourName.toLowerCase()))
                .collect(Collectors.toList());
    }
    /**
     * this method searches for certain log from ListView
     * @param logName the String you want to search for
     * @param caseSensitive the boolean to tell the program if u want to search caseSensitive or not
     * @return List of the logs which matches the String you want to search
     * */
    @Override
    public List<Log> searchLogItem(String logName, boolean caseSensitive) throws SQLException {
        //get TourList from DB but not saving in the ObservableList, but in a normal List
        List<Log> logList = GetLogItems();
        if(caseSensitive){
            logger.info("searching caseSensitive words in Log");
            return logList
                    .stream()
                    .filter(x -> x.getName().contains(logName))
                    .collect(Collectors.toList());
        }
        logger.info("searching non-caseSensitive words in Log");
        return logList
                .stream()
                .filter(x -> x.getName().toLowerCase().contains(logName.toLowerCase()))
                .collect(Collectors.toList());
    }

    /**
     * this method generates a report for a certain tour
     * @param tourListView ListView with tours
     * @param savingFolder the path where i can save it
     * @param manager JavaAppManager
     * */
    @Override
    public boolean genReport(ListView<Tour> tourListView, String savingFolder, JavaAppManager manager) {
        return reportGeneration.report(tourListView, savingFolder, manager);
    }

    /**
     * this method deletes the generated tourReport
     * @param tour the Tour, of which the report will be deleted
     * */
    @Override
    public boolean deleteReport(Tour tour) {
        return fileAccess.deletePDFReport(tour);
    }

    /**
     * this method gets the image from MapQuest and saves in the folder
     * @param tour the tour to which the image belongs
     * */
    @Override
    public void getImageFromMap(Tour tour) {
        map.getImageFromMapQuest(tour);
    }
    @Override
    public Image showImage(Tour tour) throws IOException {
        return map.showImageIn(tour);
    }

    /**
     * this method deletes the image of the tour
     * @param tour the Tour, of which the image will be deleted
     * */
    @Override
    public boolean deleteImage(Tour tour){
        return fileAccess.deleteMapImage(tour);
    }

    @Override
    public boolean exportToJSON(List<Tour> tourList, String ordner) {
        return exportFile.ExportFile(tourList, ordner);
    }

    @Override
    public List<Tour> importToJSON(String ordner) {
        return importFile.Import(ordner);
    }
}
