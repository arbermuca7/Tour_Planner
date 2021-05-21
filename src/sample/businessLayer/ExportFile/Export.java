package sample.businessLayer.ExportFile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import sample.models.Tour;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Export implements IExport{
    private static final Logger logger = LogManager.getLogger(Export.class);

    @Override
    public boolean export(List<Tour> tourList, String ordner) {

        ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);;

        TypeReference<List<Tour>> typeRef = new TypeReference<List<Tour>>() {};
        try {
            mapper.writerFor(typeRef)
                    .writeValue(new File(ordner+"tours.json"),tourList);
        } catch (IOException e) {
            logger.error(e.getMessage());
            return false;
        }

        return true;
    }
}
