package sample.businessLayer.mapQuest;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import lombok.Getter;
import sample.businessLayer.configuration.Configuration;
import sample.dataAccessLayer.fileAccess.FileAccess;
import sample.dataAccessLayer.fileAccess.IFileAccess;
import sample.models.Tour;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class MapQuest implements IMapQuest{
    @Getter private final String key = Configuration.getMapQuestKey();

    IFileAccess fileAccess = new FileAccess();

    /**
     * this method gets the image from MapQuest and saves in the folder
     * @param tour the tour to which the image belongs
     * */
    @Override
    public void getImageFromMapQuest(Tour tour) {
        String mapUrl =
                "https://www.mapquestapi.com/staticmap/v5/map?" +
                "start="+tour.getStartPoint() +"&end="+tour.getDestination()+"&size=600,400@2x&key="+key;

        fileAccess.saveMapImage(mapUrl, tour.getT_Name());

    }

    @Override
    public Image showImageIn(Tour tour) throws IOException {
        String mapUrl =
                "https://www.mapquestapi.com/staticmap/v5/map?" +
                        "start="+tour.getStartPoint() +"&end="+tour.getDestination()+"&size=600,400@2x&key="+key;
        BufferedImage image = ImageIO.read(new URL(mapUrl));
        Image img = SwingFXUtils.toFXImage(image,null);
        return img;
    }
}
