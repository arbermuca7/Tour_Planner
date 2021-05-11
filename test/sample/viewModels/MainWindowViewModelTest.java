package sample.viewModels;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sample.models.Log;
import sample.models.Tour;

import static org.junit.jupiter.api.Assertions.*;

class MainWindowViewModelTest {

    @Test
    @DisplayName("See if it takes the tour data that should be set in editWindow")
    void setTourDataToEditTest() {
        MainWindowViewModel mainWindowViewModel = new MainWindowViewModel();
        Tour tour = new Tour("sha12b","tour1","its fine","Vienna","Innsbruck",455.6);

        String expected = "sha12b,tour1,Vienna,Innsbruck,its fine,455.6";
        String actual = mainWindowViewModel.setTourDataToEdit(tour);
        //assert
        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("See if it takes the log data that should be set in editWindow")
    void setLogDataToEditTest() {
        MainWindowViewModel mainWindowViewModel = new MainWindowViewModel();
        Log log = new Log("toVacation","12.2.2012","6:20:24",455.6,
                120,69.7F,"fastest",4,"auto",true,true);

        String expected = "toVacation,12.2.2012,6:20:24,455.6,120,69.7,t,auto,fastest,4,t";
        String actual = mainWindowViewModel.setLogDataToEdit(log);
        //assert
        assertEquals(expected,actual);
    }
}