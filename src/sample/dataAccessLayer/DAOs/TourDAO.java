package sample.dataAccessLayer.DAOs;

import javafx.collections.ObservableList;
import sample.dataAccessLayer.database.DatabaseAccess;
import sample.dataAccessLayer.database.IDataAccess;
import sample.models.Log;
import sample.models.Tour;
import java.sql.SQLException;
import java.util.List;

public class TourDAO implements IDAO<Tour> {
    IDataAccess dataAccess;

    public TourDAO(){
        dataAccess = new DatabaseAccess();
    }

    /**
     * @param list as a ObservableList
     * takes the tours from the database and saves them in the ObservableList
     * */
    public void GetDatas(ObservableList<Tour> list) throws SQLException {
        dataAccess.GetTours(list);
    }

    /**
     * takes the tours from the database but it doesn't saves them in a ObservableList
     * @return the list which contains all the Tour in the database
     * */
    public List<Tour> GetDataWithoutSave(){
        return dataAccess.GetToursWithoutSaving();
    }

    /**
     * takes the tours + logs from the database but it doesn't saves them in a List
     * @return the list which contains all the Tour in the database
     * it is used for the searching method
     * */
    @Override
    public List<Tour> GetTWithL() {
        return dataAccess.GetToursWithLogs();
    }

    /**
     * @param tour takes a Tour as parameter
     * and saves that tour to the database
     * */
    public void addToDB(Tour tour, String id) throws SQLException {
        dataAccess.addTourData(tour);
    }

    /**
     * @param tour as a Tour you want to modify
     * @param id as the Tour Identification
     * so you can update it in the database
     * */
    public boolean editInDB(Tour tour, String id){
        return dataAccess.editTourData(tour, id);
    }

    /**
     * @param id as the Tour Identification,
     * so it can be deleted from the database
     * */
    public boolean deleteFromDB(String id) throws SQLException {
        return dataAccess.deleteTourData(id);
    }

}
