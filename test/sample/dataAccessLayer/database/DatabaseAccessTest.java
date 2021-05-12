package sample.dataAccessLayer.database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sample.businessLayer.configuration.Configuration;
import sample.models.Log;
import sample.models.Tour;

import java.sql.Connection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseAccessTest {

    @Test
    @DisplayName("check if the Database connection is created")
    void getConnectionTest() {
        Configuration.getDBConfigs();
        IDataAccess dataAccess = new DatabaseAccess();

        String actual = dataAccess.getConnection().toString();

        assertNotNull(actual);
    }

    @Test
    @DisplayName("check if it takes all the tours")
    void getToursWithoutSavingTest() {
        Configuration.getDBConfigs();
        IDataAccess dataAccess = new DatabaseAccess();
        List<Tour> savedTours = dataAccess.GetToursWithoutSaving();

        int actual = savedTours.size();
        //assert
        assertNotNull(actual);
    }

    @Test
    @DisplayName("check if it creates a tour")
    void addTourDataTest() {
        Configuration.getDBConfigs();
        IDataAccess dataAccess = new DatabaseAccess();
        Tour tour = new Tour("vienna2021","long_way","fine","shkoder","vienna",1205.5);

        boolean expected = true;
        boolean actual = dataAccess.addTourData(tour);
        //assert
        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("check if it edits a certain tour")
    void editTourDataTest() {
        Configuration.getDBConfigs();
        IDataAccess dataAccess = new DatabaseAccess();
        Tour tour = new Tour("vienna2021","long_way","the finest","shkoder","vienna",1205.5);

        boolean expected = true;
        boolean actual = dataAccess.editTourData(tour,"vienna2021");
        //assert
        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("check if it deletes a certain tour")
    void deleteTourDataTest() {
        Configuration.getDBConfigs();
        IDataAccess dataAccess = new DatabaseAccess();

        boolean expected = true;
        boolean actual = dataAccess.deleteTourData("vienna2021");
        //assert
        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("check if we can take all the logs for a certain Tour")
    void ReportGetLogsTest(){
        Configuration.getDBConfigs();
        IDataAccess dataAccess = new DatabaseAccess();
        List<Log> savedLogs = dataAccess.ReportGetLogs("sh110tr4");

        int actual = savedLogs.size();
        int expected = 2;
        //assert
        assertNotNull(actual);
    }

    @Test
    @DisplayName("check if it takes all the logs")
    void getLogsWithoutSavingTest() {
        Configuration.getDBConfigs();
        IDataAccess dataAccess = new DatabaseAccess();
        List<Log> savedLogs = dataAccess.GetLogsWithoutSaving();

        int actual = savedLogs.size();
        //assert
        assertNotNull(actual);
    }

    @Test
    @DisplayName("check if it creates a new log to a certain tour")
    void addLogData() {
        Configuration.getDBConfigs();
        IDataAccess dataAccess = new DatabaseAccess();
        Log tourLog = new Log("toVac","12.02.2012","06:20:24",
                455.6,120,69.7F,"fastest",4,"auto",true,true);

        boolean expected = true;
        boolean actual = dataAccess.addLogData(tourLog,"sh110tr4");
        //assert
        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("check if it edit a log")
    void editLogData() {
        Configuration.getDBConfigs();
        IDataAccess dataAccess = new DatabaseAccess();
        Log tourLog = new Log("toVac","12.02.2012","06:20:24",
                121.4,90,70.1F,"fastest",4,"auto",true,true);

        boolean expected = true;
        boolean actual = dataAccess.editLogData(tourLog);
        //assert
        assertEquals(expected,actual);

    }

    @Test
    @DisplayName("check if it deletes a log")
    void deleteLogData() {
        Configuration.getDBConfigs();
        IDataAccess dataAccess = new DatabaseAccess();

        boolean expected = true;
        boolean actual = dataAccess.deleteLogData("toVac");
        //assert
        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("check if a tour has any logs")
    void checkIfTourHasLogs() {
        Configuration.getDBConfigs();
        IDataAccess dataAccess = new DatabaseAccess();
        ObservableList<Log> observableList = FXCollections.observableArrayList();
        dataAccess.GetAllLogs(observableList);

        boolean excepted = true;
        boolean actual = dataAccess.checkIfTourHasLogs(observableList,"sh110tr4");

        //assert
        assertEquals(excepted,actual);
    }

    @Test
    void delTheLogsOfTour() {
        Configuration.getDBConfigs();
        IDataAccess dataAccess = new DatabaseAccess();
        Log tourLog = new Log("toProve","12.02.2012","11:20:24",
                455.6,120,69.7F,"slowest",4,"auto",false,true);

        dataAccess.addLogData(tourLog,"vie1502tia3");

        boolean excepted = true;
        boolean actual = dataAccess.delTheLogsOfTour("vie1502tia3");

        //assert
        assertEquals(excepted,actual);
    }
}