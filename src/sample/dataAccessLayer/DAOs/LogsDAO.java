package sample.dataAccessLayer.DAOs;

import javafx.collections.ObservableList;
import sample.dataAccessLayer.database.DatabaseAccess;
import sample.dataAccessLayer.database.IDataAccess;
import sample.models.Log;
import sample.models.Tour;

import java.sql.SQLException;
import java.util.List;

public class LogsDAO {
    IDataAccess dataAccess;

    public LogsDAO(){
        dataAccess = new DatabaseAccess();
    }

    /**
     * @param logObservableList as a ObservableList
     * takes the logs from the database and saves them in the ObservableList
     * */
    public void GetLogs(ObservableList<Log> logObservableList){
        dataAccess.GetAllLogs(logObservableList);
    }

    /**
     * @param logObservableList as the observable list
     * @param id as the tour id,
     * and wherewith we will select the logs for a certain tour
     * */
    public void GetLogsForTour(ObservableList<Log> logObservableList,String id){
        dataAccess.GetLogsForTour(logObservableList, id);
    }

    /**
     * takes the logs from the database but it doesn't saves them in a ObservableList
     * @return the list which contains all the logs in the database
     * */
    public List<Log> GetLogsWithoutSave(){
        return dataAccess.GetLogsWithoutSaving();
    }

    /**
     * @param logs as a Log you want to modify
     * @param ident as the Tour Identification,
     * so you can add a Log to a certain Tour in the Database and TableView
     * */
    public void addLogToDB(Log logs, String ident){
        dataAccess.addLogData(logs, ident);
    }

    /**
     * @param logs as a Log you want to modify
     * so you can update it in the database
     * */
    public void editLogInDB(Log logs){
        dataAccess.editLogData(logs);
    }

    /**
     * @param name as the Log name,
     * so it can be deleted from the database
     * */
    public void deleteLogFromDB(String name) throws SQLException {
        dataAccess.deleteLogData(name);
    }

    /**
     * @param logObservableList as the observable list
     * @param id as the tour id,
     * and wherewith we will look if the certain tour has logs or not
     * @return true if there are logs connected to the certain tour
     * */
    public boolean checkIfTourHasLog(ObservableList<Log> logObservableList, String id){
        return dataAccess.checkIfTourHasLogs(logObservableList, id);
    }

    /**
     * @param id as the tour id,
     * so it can be deleted from the database the log with a tour id
     * */
    public void deleteTheLogsOfTour(String id){
        dataAccess.delTheLogsOfTour(id);
    }
}
