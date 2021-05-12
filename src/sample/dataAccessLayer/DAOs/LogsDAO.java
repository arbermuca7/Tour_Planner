package sample.dataAccessLayer.DAOs;

import javafx.collections.ObservableList;
import sample.dataAccessLayer.database.DatabaseAccess;
import sample.dataAccessLayer.database.IDataAccess;
import sample.models.Log;

import java.sql.SQLException;
import java.util.List;

public class LogsDAO implements IDAO<Log>{
    IDataAccess dataAccess;

    public LogsDAO(){
        dataAccess = new DatabaseAccess();
    }

    /**
     * @param logObservableList as a ObservableList
     * takes the logs from the database and saves them in the ObservableList
     * */
    public void GetDatas(ObservableList<Log> logObservableList){
        dataAccess.GetAllLogs(logObservableList);
    }

    /**
     * takes the logs from the database but it doesn't saves them in a ObservableList
     * @return the list which contains all the logs in the database
     * */
    public List<Log> GetDataWithoutSave(){
        return dataAccess.GetLogsWithoutSaving();
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
     * @param id as the tour id,
     * and wherewith we will select the logs for a certain tour
     * */
    public List<Log> ReportGetLogs(String id){
        return dataAccess.ReportGetLogs(id);
    }

    /**
     * @param logs as a Log you want to modify
     * @param ident as the Tour Identification,
     * so you can add a Log to a certain Tour in the Database and TableView
     * */
    public void addToDB(Log logs, String ident){
        dataAccess.addLogData(logs, ident);
    }

    /**
     * @param logs as a Log you want to modify
     * so you can update it in the database
     * */
    public boolean editInDB(Log logs, String id){
        return dataAccess.editLogData(logs);
    }

    /**
     * @param name as the Log name,
     * so it can be deleted from the database
     * */
    public boolean deleteFromDB(String name) throws SQLException {
        return dataAccess.deleteLogData(name);
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
    public boolean deleteTheLogsOfTour(String id){
        return dataAccess.delTheLogsOfTour(id);
    }
}
