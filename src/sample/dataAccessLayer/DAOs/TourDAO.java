package sample.dataAccessLayer.DAOs;

import javafx.collections.ObservableList;
import sample.dataAccessLayer.database.DatabaseAccess;
import sample.dataAccessLayer.database.IDataAccess;
import sample.models.Tour;
import java.sql.SQLException;
import java.util.List;

public class TourDAO {
    IDataAccess dataAccess;

    public TourDAO(){
        dataAccess = new DatabaseAccess();
    }

    public void GetTours(ObservableList<Tour> list) throws SQLException {
        dataAccess.GetTours(list);
    }
    public List<Tour> GetTourWithoutSaving(){
        return dataAccess.GetToursWithoutSaving();
    }

    public void addTourToDB(Tour tour) throws SQLException {
        dataAccess.addTourData(tour);
    }

    public void editTourInDB(Tour tour, String id){
        dataAccess.editTourData(tour, id);
    }

    public void deleteTourFromDB(String id) throws SQLException {
        dataAccess.deleteTourData(id);
    }

}
