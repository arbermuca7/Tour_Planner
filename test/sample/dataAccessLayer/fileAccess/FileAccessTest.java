package sample.dataAccessLayer.fileAccess;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sample.businessLayer.configuration.Configuration;
import sample.businessLayer.mapQuest.MapQuest;
import sample.models.Tour;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class FileAccessTest {

    @Test
    @DisplayName("check of the image is correctly taken to be shown")
    void deleteMapImageTest() {
        IFileAccess fileAccess = new FileAccess();
        Tour tour = new Tour("toTirana1020","short trip","the finest","shkoder","lezhe",0);

        boolean expected = true;
        boolean actual = fileAccess.deleteMapImage(tour);

        //assert
        assertEquals(expected,actual);
    }
}