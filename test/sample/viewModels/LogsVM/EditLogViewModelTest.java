package sample.viewModels.LogsVM;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sample.models.Log;

import static org.junit.jupiter.api.Assertions.*;

class EditLogViewModelTest {

    @Test
    @DisplayName("set the selected log data in the fields, so you can edit it")
    void initTheEdit() {
        EditLogViewModel edit = new EditLogViewModel();
        String info = "toVacation,12.2.2012,6:20:24,455.6,120,69.7,t,auto,fastest,4,t";
        edit.initTheEdit(info);

        String expected = "toVacation,12.2.2012,6:20:24,455.6,120,69.7,t,auto,fastest,4,t";
        String actual = edit.getInputName().getValue()+","+edit.getInputDate().getValue()+","+edit.getInputDuration().getValue()+","+
                edit.getInputDistance().getValue()+","+edit.getInputAvgSpeed().getValue()+","+edit.getInputFuelCosts().getValue()
                +","+edit.getInputTollRoads().getValue()+","+edit.getInputTravelMode().getValue()
                +","+edit.getInputRouteType().getValue()+","+edit.getInputRating().getValue()+","+edit.getInputRestingPlace().getValue();
        //assert
        assertEquals(expected,actual);

    }

    @Test
    @DisplayName("take the text from FieldText and wherewith edit a tourLog")
    void EditLogDataTest() {
        EditLogViewModel editLogViewModel = new EditLogViewModel();

        editLogViewModel.getInputName().setValue("toVacation");
        editLogViewModel.getInputDate().setValue("12.2.2012");
        editLogViewModel.getInputDuration().setValue("6:20:24");
        editLogViewModel.getInputDistance().setValue("455.6");
        editLogViewModel.getInputAvgSpeed().setValue("120");
        editLogViewModel.getInputFuelCosts().setValue("69.7");
        editLogViewModel.getInputRouteType().setValue("fastest");
        editLogViewModel.getInputTravelMode().setValue("auto");
        editLogViewModel.getInputRating().setValue("4");
        editLogViewModel.getInputTollRoads().setValue("t");
        editLogViewModel.getInputRestingPlace().setValue("t");

        Log log = editLogViewModel.logData();

        String expected = "toVacation,12.2.2012,6:20:24,455.6,120,69.7,true,auto,fastest,4,true";
        String actual = log.getName()+","+log.getDate()+","+log.getDuration()+","+log.getDistance()+","+log.getAvg_speed()
                +","+log.getFuel_cost()+","+log.isToll_roads()+","+log.getTravel_mode()+","+log.getRoute_type()
                +","+log.getRating()+","+log.isResting_place();
        //assert
        assertEquals(expected,actual);
    }
}