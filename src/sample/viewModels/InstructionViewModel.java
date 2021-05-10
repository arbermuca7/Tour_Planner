package sample.viewModels;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;

public class InstructionViewModel {


    @Getter private final String searchHelp = """
            ---> Search:\n
            ***  You can search a certain Tour or a TourLog.\n
            ***  1. You should choose in the ChoiceBox for Tour or Log, to specify what you want to search.\n
            ***  2. You type in the TextField what you want to search for.\n
            ***  3. Click the \"Search\"-Button and all the Results containing the certain word or letter will be displayed.\n
            ***  4. With the \"Clear\"-Button you can clear the searched results and return the normal list of logs/tours.\n
            """;
    @Getter private final String tourHelp = """
            ---> Tour:\n
            ***  Tours are listen in a ListView\n
            ***  1. Tour(add/remove/edit):\n
            ***  \"+\"-Button opens a window where you can add a tour with its information.\n
            ***  To edit or delete a tour you should first select a tour in the list and then click the \"-\"-Button so you can
                 delete the selected tour or \"[]\"-Button so you can open the window to edit the tour you selected.\n
            ***  2. If you select a tour then in the right side of the Window under the Tab Route you can see a picture of 
                    the route from starting point to the destination.\n
            ***  3. If you select a tour then in the right side of the Window under the Tab Description you can see a text 
                    description of the route.\n
            ***  4. With the \"Report\"-Button if you select a tour you can create a pdf-File which contains the tour,\n 
                    tour-map and all the logs.\n
            """;
    @Getter private final String logHelp = """
            ---> Log:\n
            ***  Logs are listen in a TableView\n
            ***  1. Log(add/remove/edit):\n
            ***  To add a log you should first select a tour in the list and then click the \"+\"-Button so you can 
                 create the log for the selected tour.\n
            ***  To edit or delete a log you should first select a log in the table and then click the \"-\"-Button
                 so you can delete the selected log or \"[]\"-Button so you can open the window to edit the log you selected.\n
            """;

    @Getter private final StringProperty outputHelping = new SimpleStringProperty("");
}
