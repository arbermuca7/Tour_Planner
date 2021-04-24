package sample.dataAccessLayer;

import sample.models.Log;
import sample.models.Tour;

public interface IDataAccess {
    void addTourData(Tour tour);
    void editTourData(Tour tour);
    void deleteTourData(Tour tour);

    void addLogData(Log logs);
    void editLogData(Log logs);
    void deleteLogData(Log logs);

}
