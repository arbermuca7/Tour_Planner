package sample.dataAccessLayer.DAOs;

import javafx.collections.ObservableList;
import sample.dataAccessLayer.database.DatabaseAccess;
import sample.dataAccessLayer.database.IDataAccess;
import sample.models.Log;

import java.sql.SQLException;

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

    public void GetLogsForTour(ObservableList<Log> logObservableList,String id){
        dataAccess.GetLogsForTour(logObservableList, id);
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
}
