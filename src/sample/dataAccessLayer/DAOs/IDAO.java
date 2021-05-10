package sample.dataAccessLayer.DAOs;

import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

public interface IDAO<T> {

    void GetDatas(ObservableList<T> list) throws SQLException;
    List<T> GetDataWithoutSave();
    void deleteFromDB(String sth) throws SQLException;
}
