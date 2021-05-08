package sample.dataAccessLayer.database;

import javafx.collections.ObservableList;
import sample.models.Log;
import sample.models.Tour;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseAccess implements IDataAccess {

    String url = "jdbc:postgresql://localhost:5432/postgres";
    String user = "postgres";
    String pwd = "admin";

    public DatabaseAccess(){}
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
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * @param tourObservableList as a ObservableList
     * takes the tours from the database and saves them in the ObservableList
     * */
    @Override
    public void GetTours(ObservableList<Tour> tourObservableList){
        String query = "SELECT * FROM tour ORDER BY tourID ASC";
        try (Connection connection = getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet res = stmt.executeQuery(query);
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
            }
            stmt.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    /**
     * takes the tours from the database but it doesn't saves them in a ObservableList
     * @return the list which contains all the Tour in the database
     * */
    @Override
    public List<Tour> GetToursWithoutSaving(){
        String query = "SELECT * FROM tour ORDER BY tourID ASC";
        List<Tour> tourList = new ArrayList<>();
        try (Connection connection = getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet res = stmt.executeQuery(query);
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
            }
            stmt.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return tourList;
    }

    //add the tour to the databaseTable
    /**
     * @param tour takes a Tour as parameter
     * and saves that tour to the database
     * */
    @Override
    public void addTourData(Tour tour) {
        try (Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement("INSERT INTO tour(identification,bezeichnung,description,distance,startpoint,destination) VALUES (?,?,?,?,?,?); ");
            statement.setString(1,tour.getIdentification());
            statement.setString(2,tour.getT_Name());
            statement.setString(3,tour.getDescription());
            statement.setDouble(4,tour.getT_Distance());
            statement.setString(5,tour.getStartPoint());
            statement.setString(6,tour.getDestination());
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    //edit a certain Tour
    /**
     * @param tour as a Tour you want to modify
     * @param id as the Tour Identification
     * so you can update it in the database
     * */
    @Override
    public void editTourData(Tour tour, String id) {
        try (Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement("UPDATE tour SET bezeichnung=?, description=?, distance=?, startpoint=?, destination=? WHERE identification=?;\n");
            statement.setString(1,tour.getT_Name());
            statement.setString(2, tour.getDescription());
            statement.setDouble(3,tour.getT_Distance());
            statement.setString(4, tour.getStartPoint());
            statement.setString(5, tour.getDestination());
            statement.setString(6, id);
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    //delete a certain Tour from database
    /**
     * @param id as the Tour Identification,
     * so it can be deleted from the database
     * */
    @Override
    public void deleteTourData(String id){
        try (Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement("DELETE FROM tour WHERE identification = ?; ");
            statement.setString(1,id);
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * @param logObservableList as a ObservableList
     * takes the logs from the database and saves them in the ObservableList
     * */
    @Override
    public void GetAllLogs(ObservableList<Log> logObservableList){
        String query = "SELECT * FROM logs";
        try (Connection connection = getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet res = stmt.executeQuery(query);
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
            }
            stmt.close();
        }catch (SQLException e){
            e.printStackTrace();
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
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM logs WHERE tour_ident=?");
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

            }
            statement.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * @param logs as a Log you want to modify
     * @param identific as the Tour Identification,
     * so you can add a Log to a certain Tour in the Database and TableView
     * */
    @Override
    public void addLogData(Log logs,String identific) {
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
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * @param logs as a Log you want to modify
     * so you can update it in the database
     * */
    @Override
    public void editLogData(Log logs) {
        try (Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement("UPDATE logs SET log_name=?, l_date=?, duration=?, distance=?, avg_speed=?" +
                    ", fuel_costs=?, route_type=?, rating=?, travel_mode=?, toll_roads=?, resting_place=? WHERE log_name=?;\n");
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
            statement.setBoolean(11,logs.isResting_place());
            statement.setString(12,logs.getName());
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * @param name as the Log name,
     * so it can be deleted from the database
     * */
    @Override
    public void deleteLogData(String name){

        try (Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement("DELETE FROM logs WHERE log_name = ?; ");
            statement.setString(1,name);
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
