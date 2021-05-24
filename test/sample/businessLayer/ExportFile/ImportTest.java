package sample.businessLayer.ExportFile;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sample.businessLayer.javaApp.JavaAppManager;
import sample.businessLayer.javaApp.JavaAppManagerFactory;
import sample.models.Tour;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ImportTest {

    @Test
    @DisplayName("check if you have imported a list of tours")
    void ImportTest(){
        String path = "C:\\Users\\mucaa\\Documents\\FH_4.Semester\\SWE2\\Project\\toImport.json";
        JavaAppManager manager = JavaAppManagerFactory.GetManager();
        List<Tour> tours = manager.importToJSON(path);

        boolean expected = true;
        boolean actual = false;
        if (!tours.isEmpty()){
            actual = true;
        }

        //assert
        assertEquals(expected,actual);
    }
}