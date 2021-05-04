package sample.businessLayer.javaApp;

import sample.models.Tour;
import sample.views.TourViews.AddTourController;

import java.util.ArrayList;
import java.util.List;

public class JavaAppManagerImpl implements JavaAppManager{

    List<Tour> tourItems = new ArrayList<>();
    @Override
    public void SetTourItems(Tour tour) {
        tourItems.add(tour);
    }
    @Override
    public List<Tour> GetTourItems() {
        return tourItems;
    }


    @Override
    public List<Tour> searchTourItem(String tourName, boolean caseSensitive) {
        return null;
    }
}
