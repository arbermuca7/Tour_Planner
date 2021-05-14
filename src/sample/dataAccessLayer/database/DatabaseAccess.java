package sample.dataAccessLayer.database;

import javafx.collections.ObservableList;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import sample.businessLayer.configuration.Configuration;
import sample.models.Log;
import sample.models.Tour;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseAccess implements IDataAccess {

    String url  = Configuration.getUrl();
    String user = Configuration.getUsername();
    String pwd  = Configuration.getPassword();
    public DatabaseAccess(){}

    private static final Logger logger = LogManager.getLogger(DatabaseAccess.class);

    /**
     * this method creates a connection with the database
     * @return the connection
     * */
    @Override
     public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, pwd);
            logger.info("Database connection created");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            logger.error("Database connection couldn't be created. Erro message: "+e.getMessage());
        }
        return connection;
    }

    /**
     * @param tourObservableList as a ObservableList
     * takes the tours from the database and saves them in the ObservableList
     * */
    @Override
    public void GetTours(ObservableList<Tour> tourObservableList){
        try (Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM tour ORDER BY tourID ASC");
            ResultSet res = statement.executeQuery();
            while(res.next()){
                String ident = res.getString("identification");
                String name = res.getString("bezeichnung");
                String desc = res.getString("description");
                double dist = res.getDouble("distance");
                String start = res.getString("startpoint");
                String dest = res.getString("destination");

                Tour tour = new Tour(ident,name,desc,start,dest,dist);

                //add the database tours to observable list
                tourObservableList.add(tour);
                logger.info("all the tour in the DB selected and added to the ObservableList");
            }
            statement.close();
        }catch (SQLException e){
            e.printStackTrace();
            logger.error("Error Message: "+e.getMessage());
        }
    }
    /**
     * takes the tours from the database but it doesn't saves them in a ObservableList
     * @return the list which contains all the Tour in the database
     * */
    @Override
    public List<Tour> GetToursWithoutSaving(){
        List<Tour> tourList = new ArrayList<>();
        try (Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM tour ORDER BY tourID ASC");
            ResultSet res = statement.executeQuery();
            while(res.next()){
                String ident = res.getString("identification");
                String name = res.getString("bezeichnung");
                String desc = res.getString("description");
                double dist = res.getDouble("distance");
                String start = res.getString("startpoint");
                String dest = res.getString("destination");

                Tour tour = new Tour(ident,name,desc,start,dest,dist);

                //add the database tours to a List
                tourList.add(tour);
                logger.info("all tours selected and saved into a List");
            }
            statement.close();
        }catch (SQLException e){
            e.printStackTrace();
            logger.error("Error Message: "+e.getMessage());

        }
        return tourList;
    }

    @Override
    public boolean checkIfTourExists(String id) {
        try (Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM tour WHERE identification=?");
            statement.setString(1, id);
            ResultSet res = statement.executeQuery();
            if (res.next()){
                return true;
            }
            statement.close();
            logger.info("checking if a certain tour exists");
        }catch (SQLException e){
            e.printStackTrace();
            logger.error("Error Message: "+e.getMessage());

        }
        return false;
    }

    //add the tour to the databaseTable
    /**
     * @param tour takes a Tour as parameter
     * and saves that tour to the database
     * */
    @Override
    public boolean addTourData(Tour tour) {
        try (Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement("INSERT INTO tour(identification,bezeichnung,description,distance,startpoint,destination) VALUES (?,?,?,?,?,?); ");
            statement.setString(1,tour.getIdentification());
            statement.setString(2,tour.getT_Name());
            statement.setString(3,tour.getDescription());
            statement.setDouble(4,tour.getT_Distance());
            statement.setString(5,tour.getStartPoint());
            statement.setString(6,tour.getDestination());
            statement.execute();
            logger.info("the tour \""+tour.getT_Name()+"\" inserted to database");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error("Error Message: "+throwables.getMessage());

        }
        return checkIfTourExists(tour.getIdentification());
    }
    //edit a certain Tour
    /**
     * @param tour as a Tour you want to modify
     * @param id as the Tour Identification
     * so you can update it in the database
     * */
    @Override
    public boolean editTourData(Tour tour, String id) {
        try (Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement("UPDATE tour SET bezeichnung=?, description=?, distance=?, startpoint=?, destination=? WHERE identification=?;\n");
            statement.setString(1,tour.getT_Name());
            statement.setString(2, tour.getDescription());
            statement.setDouble(3,tour.getT_Distance());
            statement.setString(4, tour.getStartPoint());
            statement.setString(5, tour.getDestination());
            statement.setString(6, id);
            statement.execute();
            logger.info("the tour \""+tour.getT_Name()+"\" was updated into the DB");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error("Error Message: "+throwables.getMessage());
            return false;
        }
        return true;
    }
    //delete a certain Tour from database
    /**
     * @param id as the Tour Identification,
     * so it can be deleted from the database
     * */
    @Override
    public boolean deleteTourData(String id){
        try (Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement("DELETE FROM tour WHERE identification = ?; ");
            statement.setString(1,id);
            statement.execute();
            logger.info("the tour with the id = "+id+" was deleted");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error("Error Message: "+throwables.getMessage());
            return false;
        }
        if(!checkIfTourExists(id)){
            return true;
        }
        return false;
    }

    /**
     * @param logObservableList as a ObservableList
     * takes the logs from the database and saves them in the ObservableList
     * */
    @Override
    public void GetAllLogs(ObservableList<Log> logObservableList){
        try (Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM logs ORDER BY logID ASC");
            ResultSet res = statement.executeQuery();
            while(res.next()){
                String date = res.getString("l_date");
                String name = res.getString("log_name");
                String duration = res.getString("duration");
                double dist = res.getDouble("distance");
                int speed = res.getInt("avg_speed");
                float fuel = res.getFloat("fuel_costs");
                String route = res.getString("route_type");
                int rate = res.getInt("rating");
                String travel = res.getString("travel_mode");
                boolean tollRoad = res.getBoolean("toll_roads");
                boolean restPlace = res.getBoolean("resting_place");

                Log log = new Log(name,date,duration,dist,speed,fuel,route,rate,travel,tollRoad,restPlace);
                //add the database tours to observable list
                logObservableList.add(log);
                logger.info("all logs selected and saved into the ObservableList");

            }
            statement.close();
        }catch (SQLException e){
            e.printStackTrace();
            logger.error("Error Message: "+e.getMessage());
        }
    }
    /**
     * @param logObservableList as the observable list
     * @param id as the tour id,
     * and wherewith we will select the logs for a certain tour
     * */
    @Override
    public void GetLogsForTour(ObservableList<Log> logObservableList, String id){
        try (Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM logs WHERE tour_ident=? ORDER BY logID ASC");
            statement.setString(1, id);
            ResultSet res = statement.executeQuery();
            while(res.next()){
                String date = res.getString("l_date");
                String name = res.getString("log_name");
                String duration = res.getString("duration");
                double dist = res.getDouble("distance");
                int speed = res.getInt("avg_speed");
                float fuel = res.getFloat("fuel_costs");
                String route = res.getString("route_type");
                int rate = res.getInt("rating");
                String travel = res.getString("travel_mode");
                boolean tollRoad = res.getBoolean("toll_roads");
                boolean restPlace = res.getBoolean("resting_place");

                Log log = new Log(name,date,duration,dist,speed,fuel,route,rate,travel,tollRoad,restPlace);
                //add the database tours to observable list
                logObservableList.addAll(log);
                logger.info("all logs for a certain tour selected and saved into the ObservableList");
            }
            statement.close();
        }catch (SQLException e){
            e.printStackTrace();
            logger.error("Error Message: "+e.getMessage());
        }
    }

    /**
     * @param id as the tour id,
     * and wherewith we will select the logs for a certain tour
     * */
    @Override
    public List<Log> ReportGetLogs(String id){
        List<Log> logsForTour = new ArrayList<>();
        try (Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM logs WHERE tour_ident=? ORDER BY logID ASC");
            statement.setString(1, id);
            ResultSet res = statement.executeQuery();
            while(res.next()){
                String date = res.getString("l_date");
                String name = res.getString("log_name");
                String duration = res.getString("duration");
                double dist = res.getDouble("distance");
                int speed = res.getInt("avg_speed");
                float fuel = res.getFloat("fuel_costs");
                String route = res.getString("route_type");
                int rate = res.getInt("rating");
                String travel = res.getString("travel_mode");
                boolean tollRoad = res.getBoolean("toll_roads");
                boolean restPlace = res.getBoolean("resting_place");

                Log log = new Log(name,date,duration,dist,speed,fuel,route,rate,travel,tollRoad,restPlace);
                //add the database tours to observable list
                logsForTour.add(log);
                logger.info("all logs for a certain tour selected and saved into a List for the pdf-Report");
            }
            statement.close();
        }catch (SQLException e){
            e.printStackTrace();
            logger.error("Error Message: "+e.getMessage());
        }
        return logsForTour;
    }

    /**
     * takes the logs from the database but it doesn't saves them in a ObservableList
     * @return the list which contains all the logs in the database
     * */
    @Override
    public List<Log> GetLogsWithoutSaving(){
        String query = "SELECT * FROM logs ORDER BY logID ASC";
        List<Log> logList = new ArrayList<>();
        try (Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet res = statement.executeQuery();
            while(res.next()){
                String date = res.getString("l_date");
                String name = res.getString("log_name");
                String duration = res.getString("duration");
                double dist = res.getDouble("distance");
                int speed = res.getInt("avg_speed");
                float fuel = res.getFloat("fuel_costs");
                String route = res.getString("route_type");
                int rate = res.getInt("rating");
                String travel = res.getString("travel_mode");
                boolean tollRoad = res.getBoolean("toll_roads");
                boolean restPlace = res.getBoolean("resting_place");

                Log log = new Log(name,date,duration,dist,speed,fuel,route,rate,travel,tollRoad,restPlace);
                logger.info("all logs for a certain tour selected and saved into a List");
                //add the database tours to a List
                logList.add(log);

            }
            statement.close();
        }catch (SQLException e){
            e.printStackTrace();
            logger.error("Error Message: "+e.getMessage());

        }
        return logList;
    }

    /**
     * @param logs as a Log you want to modify
     * @param identific as the Tour Identification,
     * so you can add a Log to a certain Tour in the Database and TableView
     * */
    @Override
    public boolean addLogData(Log logs,String identific) {
        try (Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement("INSERT INTO logs(log_name,l_date,duration,distance,avg_speed," +
                    "fuel_costs,route_type,rating,travel_mode,toll_roads,tour_ident,resting_place) VALUES (?,?,?,?,?,?,?,?,?,?,?,?); ");
            statement.setString(1,logs.getName());
            statement.setString(2,logs.getDate());
            statement.setString(3,logs.getDuration());
            statement.setDouble(4,logs.getDistance());
            statement.setInt(5,logs.getAvg_speed());
            statement.setFloat(6,logs.getFuel_cost());
            statement.setString(7,logs.getRoute_type());
            statement.setInt(8,logs.getRating());
            statement.setString(9,logs.getTravel_mode());
            statement.setBoolean(10,logs.isToll_roads());
            statement.setString(11,identific);
            statement.setBoolean(12,logs.isResting_place());
            statement.execute();
            logger.info("the log \""+ logs.getName()+"\" inserted to database");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error("Error Message: "+throwables.getMessage());

        }
        return checkIfLogsExists(logs.getName());
    }

    /**
     * @param logs as a Log you want to modify
     * so you can update it in the database
     * */
    @Override
    public boolean editLogData(Log logs) {
        try (Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement("UPDATE logs SET l_date=?, duration=?, distance=?, avg_speed=?" +
                    ", fuel_costs=?, route_type=?, rating=?, travel_mode=?, toll_roads=?, resting_place=? WHERE log_name=?;\n");
            statement.setString(1,logs.getDate());
            statement.setString(2,logs.getDuration());
            statement.setDouble(3,logs.getDistance());
            statement.setInt(4,logs.getAvg_speed());
            statement.setFloat(5,logs.getFuel_cost());
            statement.setString(6,logs.getRoute_type());
            statement.setInt(7,logs.getRating());
            statement.setString(8,logs.getTravel_mode());
            statement.setBoolean(9,logs.isToll_roads());
            statement.setBoolean(10,logs.isResting_place());
            statement.setString(11,logs.getName());
            statement.execute();
            logger.info("the log \""+ logs.getName()+"\" updated into database");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error("Error Message: "+throwables.getMessage());
            return false;
        }
        return true;
    }

    /**
     * @param name as the Log name,
     * so it can be deleted from the database
     * */
    @Override
    public boolean deleteLogData(String name){

        try (Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement("DELETE FROM logs WHERE log_name = ?; ");
            statement.setString(1,name);
            statement.execute();
            logger.info("the log with the name = \""+name+"\" was deleted");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error("Error Message: "+throwables.getMessage());
        }
        if(!checkIfLogsExists(name)){
            return true;
        }
        return false;
    }
    @Override
    public boolean checkIfLogsExists(String name){
        try (Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM logs WHERE log_name=?");
            statement.setString(1, name);
            ResultSet res = statement.executeQuery();
            if (res.next()){
                return true;
            }
            statement.close();
            logger.info("checking if the log with the name = \""+name+"\" exists in DB");
        }catch (SQLException e){
            e.printStackTrace();
            logger.error("Error Message: "+e.getMessage());
        }
        return false;
    }
    /**
     * @param logObservableList as the observable list
     * @param id as the tour id,
     * and wherewith we will look if the certain tour has logs or not
     * @return true if there are logs connected to the certain tour
     * */
    @Override
    public boolean checkIfTourHasLogs(ObservableList<Log> logObservableList, String id){
        try (Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM logs WHERE tour_ident=?");
            statement.setString(1, id);
            ResultSet res = statement.executeQuery();
            if (res.next()){
                return true;
            }
            statement.close();
            logger.info("checking if the tour with the id = \""+id+"\" has any log in DB");
        }catch (SQLException e){
            e.printStackTrace();
            logger.error("Error Message: "+e.getMessage());
        }
        return false;
    }

    /**
     * @param id as the tour id,
     * so it can be deleted from the database the log with a tour id
     * */
    @Override
    public boolean delTheLogsOfTour(String id) {
        try (Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement("DELETE FROM logs WHERE tour_ident = ?; ");
            statement.setString(1,id);
            statement.execute();
            logger.info("deleting all the log of the tour with the id = \""+id+"\" in DB");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error("Error Message: "+throwables.getMessage());

            return false;
        }
        return true;
    }
}
