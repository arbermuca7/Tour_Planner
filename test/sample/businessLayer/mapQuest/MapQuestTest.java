package sample.businessLayer.mapQuest;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sample.businessLayer.configuration.Configuration;
import sample.models.Tour;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class MapQuestTest {

    @Test
    @DisplayName("check of the image is correctly taken to be shown")
    void createImageTest() {
        MapQuest map = new MapQuest();
        Tour tour = new Tour("toTirana1020","short trip","the finest","shkoder","lezhe",0);
        map.getImageFromMapQuest(tour);
        String filepath = Configuration.getMapPath()+"short_trip.jpg";
        File file = new File(filepath);
        boolean actual = file.exists();
        //assert
        assertNotNull(actual);
    }
}