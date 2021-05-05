package sample.businessLayer.javaApp;

import javafx.collections.ObservableList;
import sample.dataAccessLayer.DAOs.TourDAO;
import sample.models.Tour;
import sample.views.MainWindowController;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class JavaAppManagerImpl implements JavaAppManager{

    TourDAO tourDAO = new TourDAO();

    @Override
    public void SetDataItems(Tour tour) throws SQLException {
        tourDAO.addTourToDB(tour);
    }

    @Override
    public List<Tour> GetTourItems() {
        return tourDAO.GetTourWithoutSaving();
    }

    @Override
    public void GetData(ObservableList<Tour> tour) throws SQLException {
        tourDAO.GetTours(tour);
    }

    public void delData(String id) throws SQLException {
        tourDAO.deleteTourFromDB(id);
    }

    @Override
    public void editData(Tour tour, String id) {
        tourDAO.editTourInDB(tour, id);
    }

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
}
