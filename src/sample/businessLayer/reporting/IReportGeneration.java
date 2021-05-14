package sample.businessLayer.reporting;

import com.itextpdf.layout.Document;
import javafx.scene.control.ListView;
import sample.businessLayer.javaApp.JavaAppManager;
import sample.models.Log;
import sample.models.Tour;

import java.io.IOException;
import java.util.List;


public interface IReportGeneration {

    boolean report(ListView<Tour> tourListView, String savingFolder, JavaAppManager manager);

    void titleCreate(Document document, Tour tour) throws IOException;
    void basicInfo(Document document, Tour tour) throws IOException;
    void tourImage(Document document, String tour) throws IOException;
    boolean tourLogsInfo(Document document, List<Log> logs) throws IOException;

}
