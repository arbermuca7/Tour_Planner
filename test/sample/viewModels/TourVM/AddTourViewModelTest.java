package sample.viewModels.TourVM;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sample.models.Tour;

import static org.junit.jupiter.api.Assertions.*;

class AddTourViewModelTest {

    @Test
    @DisplayName("take the text from FieldText and wherewith create a tour")
    void tourDataTest() {
        AddTourViewModel addTourViewModel = new AddTourViewModel();

        addTourViewModel.getInputIdentific().setValue("sha12b");
        addTourViewModel.getInputName().setValue("tour1");
        addTourViewModel.getInputStart().setValue("Vienna");
        addTourViewModel.getInputDestination().setValue("Innsbruck");
        addTourViewModel.getInputDescription().setValue("its fine");
        addTourViewModel.getInputDistance().setValue("455.6");

        Tour tourG = addTourViewModel.tourData();

        String expected = "sha12b,tour1,Vienna,Innsbruck,its fine,455.6";
        String actual = tourG.getIdentification()+"," +tourG.getT_Name()+","+tourG.getStartPoint()+","
                +tourG.getDestination()+","+tourG.getDescription()+","+tourG.getT_Distance();
        //assert
        assertEquals(expected,actual);
    }
}