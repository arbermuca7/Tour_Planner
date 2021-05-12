package sample.dataAccessLayer.DAOs;

import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

public interface IDAO<T> {

    void GetDatas(ObservableList<T> list) throws SQLException;
    List<T> GetDataWithoutSave();
    void addToDB(T st, String sth) throws SQLException;
    boolean editInDB(T st, String sth);
    boolean deleteFromDB(String sth) throws SQLException;
}
