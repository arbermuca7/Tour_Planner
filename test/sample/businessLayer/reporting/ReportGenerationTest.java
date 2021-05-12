package sample.businessLayer.reporting;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sample.businessLayer.configuration.Configuration;
import sample.businessLayer.javaApp.JavaAppManager;
import sample.businessLayer.javaApp.JavaAppManagerFactory;
import sample.models.Log;
import sample.models.Tour;
import sample.viewModels.MainWindowViewModel;
import sample.views.MainWindowController;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReportGenerationTest {

    @Test
    @DisplayName("it doesn't have any logs returns a message")
    void tourLogsInfoTest() {
        try {
            IReportGeneration reportGeneration = new ReportGeneration();
            PdfWriter pdfWriter = new PdfWriter("C:\\Users\\mucaa\\Documents\\FH_4.Semester\\SWE2\\test.pdf");
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            pdfDocument.addNewPage();
            Document doc = new Document(pdfDocument);
            List<Log> list = new ArrayList<>();

            boolean expected = false;
            boolean actual = reportGeneration.tourLogsInfo(doc,list);

            //assert
            assertEquals(expected,actual);

        }catch(Exception e){
            e.printStackTrace();
        }
    }

}