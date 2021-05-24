package sample.businessLayer.ExportFile;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sample.businessLayer.configuration.Configuration;
import sample.businessLayer.javaApp.JavaAppManager;
import sample.businessLayer.javaApp.JavaAppManagerFactory;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class ExportTest {

    @Test
    @DisplayName("Check if we can export a file with all tours in json-format")
    void exportTest() {
        Configuration.getDBConfigs();
        JavaAppManager manager = JavaAppManagerFactory.GetManager();
        manager.exportToJSON(manager.GetToursWithLogs(), Configuration.getExportFile());

        File file = new File(Configuration.getExportFile()+"tours.json");

        boolean expected = true;
        boolean actual = file.exists();

        //assert
        assertEquals(expected,actual);

    }
}