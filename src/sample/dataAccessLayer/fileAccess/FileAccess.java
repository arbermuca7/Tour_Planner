package sample.dataAccessLayer.fileAccess;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import sample.businessLayer.configuration.Configuration;
import sample.businessLayer.reporting.ReportGeneration;
import sample.models.Tour;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class FileAccess implements IFileAccess{

    private static final Logger logger = LogManager.getLogger(FileAccess.class);

    /**
     * this method gets the image and saves in the folder
     * @param path the url to the MapQuest
     * @param tourName the name of the tour to which it belongs
     * */
    @Override
    public void saveMapImage(String path, String tourName) {

        String imageName = tourName.replaceAll(" ","_");
        String pathNew = path.replaceAll(" ","");
        BufferedImage image = null;
        URL url = null;

        try{
            url = new URL(pathNew);
            logger.info("URL to the mapquest created");
            image = ImageIO.read(url);
            logger.info("Request for the map sent");
            File lastimage = new File(Configuration.getMapPath()+imageName+".jpg");
            ImageIO.write(image,"jpg",lastimage);
            logger.info("image successfully saved");
        }catch(IOException e){
            e.printStackTrace();
            logger.error("Couldn't create the URL or save the image from MapQuest! Error message: "+e.getMessage());
        }
    }

    /**
     * this method deletes the image of the tour from the folder
     * @param tour the tour to which the image belongs
     * */
    @Override
    public boolean deleteMapImage(Tour tour) {
        String imageName = tour.getT_Name().replaceAll(" ","_");
        File fileObject = new File(Configuration.getMapPath()+imageName+".jpg");
        if(fileObject.exists()){
            if(fileObject.delete()){
                logger.info("the image \""+imageName+"\" deleted successfully");
                return true;
            }else{
                logger.error("Couldn't delete the image");
                return false;
            }
        }else{
            logger.warn("No Image found!");
            return false;
        }
    }

    /**
     * this method deletes the report of the tour from the folder
     * @param tour the tour to which the report belongs
     * */
    @Override
    public boolean deletePDFReport(Tour tour) {
        String reportName = tour.getT_Name().replaceAll(" ","_");
        File file = new File(Configuration.getPdfPath()+reportName+".pdf");
        if(file.exists()){
            if(file.delete()){
                logger.info("the report \""+reportName+"\" deleted successfully");
                return true;
            }else{
                logger.error("Couldn't delete the report");
                return false;
            }
        }else{
            logger.warn("No Report found!");
            return false;
        }
    }
}
