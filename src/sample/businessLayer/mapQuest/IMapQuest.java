package sample.businessLayer.mapQuest;

import javafx.scene.image.Image;
import sample.models.Tour;

import java.io.IOException;

public interface IMapQuest {
    void getImageFromMapQuest(Tour tour);
    Image showImageIn(Tour tour) throws IOException;
}
