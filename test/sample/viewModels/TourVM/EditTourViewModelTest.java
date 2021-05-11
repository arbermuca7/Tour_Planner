package sample.viewModels.TourVM;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EditTourViewModelTest {

    @Test
    @DisplayName("take the text from FieldText and wherewith edit the certain tour")
    void EditTourDataTest() {
        EditTourViewModel edit = new EditTourViewModel();

        edit.getInputName().setValue("tour1");
        edit.getInputStart().setValue("Vienna");
        edit.getInputDestination().setValue("Innsbruck");
        edit.getInputDescription().setValue("its fine");
        edit.getInputDistance().setValue("455.6");

        String actual = edit.tourData();
        String expected = "tour1,Vienna,Innsbruck,455.6,its fine";
        //assert
        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("set the selected tour data in the fields, so you can edit it")
    void initEdit(){
        EditTourViewModel edit = new EditTourViewModel();
        String info = "sha12b,tour1,Vienna,Innsbruck,its fine,455.6";
        edit.initEdit(info);

        String expected = "sha12b,tour1,Vienna,Innsbruck,its fine,455.6";
        String actual = edit.getId()+","+edit.getInputName().getValue()+","+edit.getInputStart().getValue()+","+edit.getInputDestination().getValue()
                +","+edit.getInputDescription().getValue()+","+edit.getInputDistance().getValue();
        //assert
        assertEquals(expected,actual);

    }
}