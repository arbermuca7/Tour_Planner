package sample.businessLayer.javaApp;

import sample.models.Tour;

import java.util.List;

public interface JavaAppManager {

    public void SetTourItems(Tour tour);
    public List<Tour> GetTourItems();
    public List<Tour> searchTourItem(String tourName, boolean caseSensitive);
}
