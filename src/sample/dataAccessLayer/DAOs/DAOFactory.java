package sample.dataAccessLayer.DAOs;

import sample.models.Log;
import sample.models.Tour;

public class DAOFactory{
    public static IDAO getInstance(String type){
        if(type.equals("tour") || type.equals("Tour") || type.equals("TOUR")){
            IDAO<Tour> dao = new TourDAO();
            return dao;
        }else if(type.equals("log") || type.equals("Log") || type.equals("LOG")){
            IDAO<Log> daoL = new LogsDAO();
            return daoL;
        }
        return null;
    }
}
