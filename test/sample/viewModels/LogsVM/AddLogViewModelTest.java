package sample.viewModels.LogsVM;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sample.models.Log;

import static org.junit.jupiter.api.Assertions.*;

class AddLogViewModelTest {

    @Test
    @DisplayName("take the text from FieldText and wherewith create a tourLog")
    void logDataTest() {
        AddLogViewModel addLogViewModel = new AddLogViewModel();

        addLogViewModel.getInputName().setValue("toVacation");
        addLogViewModel.getInputDate().setValue("12.2.2012");
        addLogViewModel.getInputDuration().setValue("6:20:24");
        addLogViewModel.getInputDistance().setValue("455.6");
        addLogViewModel.getInputAvgSpeed().setValue("120");
        addLogViewModel.getInputFuelCosts().setValue("69.7");
        addLogViewModel.getInputRouteType().setValue("fastest");
        addLogViewModel.getInputTravelMode().setValue("auto");
        addLogViewModel.getInputRating().setValue("4");
        addLogViewModel.getInputTollRoads().setValue("t");
        addLogViewModel.getInputRestingPlace().setValue("t");

        Log log = addLogViewModel.logData();

        String expected = "toVacation,12.2.2012,6:20:24,455.6,120,69.7,true,auto,fastest,4,true";
        String actual = log.getName()+","+log.getDate()+","+log.getDuration()+","+log.getDistance()+","+log.getAvg_speed()
                +","+log.getFuel_cost()+","+log.isToll_roads()+","+log.getTravel_mode()+","+log.getRoute_type()
                +","+log.getRating()+","+log.isResting_place();
        //assert
        assertEquals(expected,actual);


    }

    @Test
    @DisplayName("get the id of the selected tour, so you can create a tourLog")
    void getIdTest() {
        AddLogViewModel addLogViewModel = new AddLogViewModel();
        String info = "sha12b,tour1";
        addLogViewModel.getClickedID(info);

        String expected = "sha12b";
        String actual = addLogViewModel.getId();

        //assert
        assertEquals(expected,actual);
    }
}