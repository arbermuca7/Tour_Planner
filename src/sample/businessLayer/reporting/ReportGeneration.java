package sample.businessLayer.reporting;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.TextAlignment;
import javafx.scene.control.ListView;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import sample.businessLayer.javaApp.JavaAppManager;
import sample.models.Log;
import sample.models.Tour;

import java.io.IOException;
import java.util.List;

public class ReportGeneration implements IReportGeneration{

    private static final Logger logger = LogManager.getLogger(ReportGeneration.class);

    @Override
    public boolean report(ListView<Tour> tourListView, String savingFolder, JavaAppManager manager) {

        //get the selected tour
        Tour selectedTour = tourListView.getSelectionModel().getSelectedItem();
        //get the logs of the selected tour
        List<Log> tourLogs = manager.ReportGetLogsForTour(selectedTour.getIdentification());

        String filename = selectedTour.getT_Name().replaceAll(" ","_");

        try{
            PdfWriter pdfWriter = new PdfWriter(savingFolder+filename+".pdf");
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            pdfDocument.addNewPage();

            Document document = new Document(pdfDocument);

            //title of the PDF
            titleCreate(document,selectedTour);

            //tour information
            basicInfo(document,selectedTour);

            //tour image
            tourImage(document);

            //tourLogs information
            boolean what = tourLogsInfo(document,tourLogs);
            //close the document
            document.close();
            logger.info("PDF-Report for the tour \""+selectedTour+"\" generated");
        }catch(Exception e){
            e.printStackTrace();
            logger.error("Couldn't create a PDF-Report. Error message: "+e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public void titleCreate(Document document,Tour tour) throws IOException {
        String tourName = tour.getT_Name();
        Text title = new Text("Report for "+tourName)
                .setBold()
                .setTextAlignment(TextAlignment.CENTER)
                .setFontSize(20)
                .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA));
        Paragraph titlePar = new Paragraph()
                .add(title).setTextAlignment(TextAlignment.CENTER);
        document.add(titlePar);
        logger.info("PDF-File Title inserted");
    }

    @Override
    public void basicInfo(Document document, Tour tour) throws IOException {
        String starting = tour.getStartPoint();
        String destination = tour.getDestination();
        String description = tour.getDescription();
        double distance = tour.getT_Distance();

        Text h1 = new Text("\nGeneral Information: ").setBold().setUnderline();
        Text start = new Text("\n\n-->Starting Point: "+starting);
        Text dest = new Text("\n-->Destination: "+destination);
        Text dist = new Text("\n-->Distance: "+distance);
        Text desc = new Text("\n-->Tour Description: "+description);
        Paragraph tourPar = new Paragraph()
                .add(h1)
                .add(start)
                .add(dest)
                .add(dist)
                .add(desc)
                .setFontSize(14);
        document.add(tourPar);
        logger.info("tour general information to pdf inserted");
    }

    @Override
    public void tourImage(Document document) throws IOException {
        Text image = new Text("\nTour Image: ").setBold().setUnderline();
        Paragraph imagePar = new Paragraph()
                .add(image)
                .setFontSize(14);
        document.add(imagePar);
        //String src = "images\\img.jpg";
        //ImageData img = ImageDataFactory.create(src);
        //Image newimage = new Image(img);
        //add it to the document
        //document.add(newimage);
        logger.info("Image inserted to the pdf");
    }

    @Override
    public boolean tourLogsInfo(Document document, List<Log> Tlogs) throws IOException {
        Text logs = new Text("\nTour Logs: ").setBold().setUnderline();
        Text logs11 = new Text("\n").setBold().setUnderline();
        Paragraph logsPar = new Paragraph()
                .add(logs)
                .add(logs11)
                .setFontSize(14);
        document.add(logsPar);
        if(Tlogs.size()>0){
            float columnWidth[] ={75f,75f,75f,75f,75f,75f,75f,65f,90f,75f,90f};
            Table table = new Table(columnWidth).setFontSize(11);

            Paragraph tableHeader[] = {
                    new Paragraph("Date"),
                    new Paragraph("Name"),
                    new Paragraph("Duration"),
                    new Paragraph("Distance"),
                    new Paragraph("Avg. Speed"),
                    new Paragraph("Fuel Cost"),
                    new Paragraph("Route Type"),
                    new Paragraph("Rating"),
                    new Paragraph("Travel Mode"),
                    new Paragraph("Toll Roads"),
                    new Paragraph("Resting Place").setFontSize(11)
            };
            for (int i = 0; i< tableHeader.length; i++){
                table.addCell(new Cell().add(tableHeader[i]));
            }
            for(int i = 0; i< Tlogs.size();i++){
                table.addCell(new Cell().add(new Paragraph(Tlogs.get(i).getDate())));
                table.addCell(new Cell().add(new Paragraph(Tlogs.get(i).getName())));
                table.addCell(new Cell().add(new Paragraph(Tlogs.get(i).getDuration())));
                table.addCell(new Cell().add(new Paragraph(Double.toString(Tlogs.get(i).getDistance()))));
                table.addCell(new Cell().add(new Paragraph(Integer.toString(Tlogs.get(i).getAvg_speed()))));
                table.addCell(new Cell().add(new Paragraph(Float.toString(Tlogs.get(i).getFuel_cost()))));
                table.addCell(new Cell().add(new Paragraph(Tlogs.get(i).getRoute_type())));
                table.addCell(new Cell().add(new Paragraph(Integer.toString(Tlogs.get(i).getRating()))));
                table.addCell(new Cell().add(new Paragraph(Tlogs.get(i).getTravel_mode())));
                table.addCell(new Cell().add(new Paragraph(Boolean.toString(Tlogs.get(i).isToll_roads()))));
                table.addCell(new Cell().add(new Paragraph(Boolean.toString(Tlogs.get(i).isResting_place()))));
            }
            document.add(table);
        }else{
            Text noLog = new Text("\nNo logs assigned to this tour!!!!").setBold().setFontColor(ColorConstants.RED);
            Paragraph noLogPar = new Paragraph()
                    .add(noLog)
                    .setFontSize(14);
            document.add(noLogPar);
            logger.info("No assigned logs to this tour");
            return false;
        }
        logger.info("tourlogs in the pdf showed inside a table");
        return true;
    }
}