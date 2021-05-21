package sample.businessLayer.ExportFile;

import sample.models.Tour;

import java.util.List;

public interface IExport {

    boolean export(List<Tour> tourList, String ordner);
}
