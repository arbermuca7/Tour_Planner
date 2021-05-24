package sample.businessLayer.ExportFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import sample.models.Tour;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Import {
    private static final Logger logger = LogManager.getLogger(Import.class);

    public List<Tour> Import(String folder) {
        // create object mapper instance
        ObjectMapper mapper = new ObjectMapper();
        // convert JSON string to Book object
        List<Tour> tourList = null;
        try {
            tourList = Arrays.asList(mapper.readValue(Paths.get(folder)
                    .toFile(), Tour[].class));

        } catch (IOException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return tourList;
    }
}
