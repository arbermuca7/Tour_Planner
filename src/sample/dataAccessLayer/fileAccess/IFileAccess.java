package sample.dataAccessLayer.fileAccess;

import sample.models.Tour;

public interface IFileAccess {
    void saveMapImage(String path, String tourName);
    boolean deleteMapImage(Tour tour);
    boolean deletePDFReport(Tour tour);
}
