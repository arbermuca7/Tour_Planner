package sample.businessLayer.javaApp;

import javafx.collections.ObservableList;
import sample.dataAccessLayer.DAOs.*;
import sample.models.Log;
import sample.models.Tour;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class JavaAppManagerImpl implements JavaAppManager{

    IDAO<Tour> daoTour = DAOFactory.getInstance("tour");
    IDAO<Log> daoLog = DAOFactory.getInstance("log");

    TourDAO tourDAO = new TourDAO();
    LogsDAO logsDAO = new LogsDAO();
    /**
     * @param tour takes a Tour as parameter
     * and saves that tour to the database
     * */
    @Override
    public void SetDataItems(Tour tour) throws SQLException {
        tourDAO.addTourToDB(tour);
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
     * @param tour as a ObservableList
     * takes the tours from the database and saves them in the ObservableList and
     * @return the list which contains all the Tour in the database
     * */
    @Override
    public void GetData(ObservableList<Tour> tour) throws SQLException {
        daoTour.GetDatas(tour);
    }

    /**
     * @param id as the Tour Identification,
     * so it can be deleted from the database
     * */
    public void delData(String id) throws SQLException {
        daoTour.deleteFromDB(id);
    }

    /**
     * @param tour as a Tour you want to modify
     * @param id as the Tour Identification
     * so you can update it in the database
     * */
    @Override
    public void editData(Tour tour, String id) {
        tourDAO.editTourInDB(tour, id);
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
    public void setLogItems(Log logItems, String id) {
        logsDAO.addLogToDB(logItems,id);
    }

    /**
     * @param log as a Log you want to modify
     * so you can update it in the database
     * */
    @Override
    public void editLogItems(Log log) {
        logsDAO.editLogInDB(log);
    }

    /**
     * @param name as the Log name,
     * so it can be deleted from the database
     * */
    @Override
    public void delLogItem(String name) throws SQLException {
        daoLog.deleteFromDB(name);
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
    public void deleteTheLogsOfTour(String id) {
        logsDAO.deleteTheLogsOfTour(id);
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
            return tourList
                    .stream()
                    .filter(x -> x.getT_Name().contains(tourName))
                    .collect(Collectors.toList());
        }
        return tourList
                .stream()
                .filter(x -> x.getT_Name().toLowerCase().contains(tourName.toLowerCase()))
                .collect(Collectors.toList());
    }
    @Override
    public List<Log> searchLogItem(String logName, boolean caseSensitive) throws SQLException {
        //get TourList from DB but not saving in the ObservableList, but in a normal List
        List<Log> logList = GetLogItems();
        if(caseSensitive){
            return logList
                    .stream()
                    .filter(x -> x.getName().contains(logName))
                    .collect(Collectors.toList());
        }
        return logList
                .stream()
                .filter(x -> x.getName().toLowerCase().contains(logName.toLowerCase()))
                .collect(Collectors.toList());
    }
}
