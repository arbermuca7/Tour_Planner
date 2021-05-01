package sample.dataAccessLayer.database;
import sample.models.Log;
import sample.models.Tour;

import java.sql.*;

public class DatabaseAccess implements IDataAccess {

    Connection connection;
    public void openConnection() throws SQLException {
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/swe1db","postgres","admin");
    }

    public void closeConnection() throws SQLException {
        connection.close();
        connection = null;
    }
    //add the tour to the databaseTable
    @Override
    public void addTourData(Tour tour) {
        String distance = Double.toString(tour.getT_Distance());
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO fighter VALUES (?,?,?,?,?,?); ");
            statement.setString(1,tour.getIdentification());
            statement.setString(2,tour.getT_Name());
            statement.setString(3,tour.getDescription());
            statement.setString(4,distance);
            statement.setString(5,tour.getStartPoint());
            statement.setString(6,tour.getDestination());
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    //edit a certain Tour
    @Override
    public void editTourData(Tour tour, String id) {
        String distance = Double.toString(tour.getT_Distance());
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE tour SET bezeichnung=?, description=?, distance=?, startpoint=?, destination=? WHERE identification=?;\n");
            statement.setString(1,tour.getT_Name());
            statement.setString(2, tour.getDescription());
            statement.setString(3,distance);
            statement.setString(4, tour.getStartPoint());
            statement.setString(5, tour.getDestination());
            statement.setString(4, id);
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    //delete a certain Tour from database
    @Override
    public void deleteTourData(String id) throws SQLException {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM tour WHERE identification = ?; ");
            statement.setString(1,id);
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void addLogData(Log logs,Tour tour) {
        String distance = Double.toString(logs.getDistance());
        String speed = Integer.toString(logs.getAvg_speed());
        String fuel = Float.toString(logs.getFuel_cost());
        String rate = Integer.toString(logs.getRating());
        String toll = Boolean.toString(logs.isToll_roads());
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO logs VALUES (?,?,?,?,?,?,?,?,?,?,?); ");
            statement.setString(1,logs.getName());
            statement.setString(2,logs.getDate());
            statement.setString(3,logs.getDuration());
            statement.setString(4,distance);
            statement.setString(5,speed);
            statement.setString(6,fuel);
            statement.setString(7,logs.getRoute_type());
            statement.setString(8,rate);
            statement.setString(9,logs.getTravel_mode());
            statement.setString(10,toll);
            statement.setString(11,tour.getIdentification());
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void editLogData(Log logs) {
        String distance = Double.toString(logs.getDistance());
        String speed = Integer.toString(logs.getAvg_speed());
        String fuel = Float.toString(logs.getFuel_cost());
        String rate = Integer.toString(logs.getRating());
        String toll = Boolean.toString(logs.isToll_roads());

        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE logs SET log_name=?, l_date=?, duration=?, distance=?, avg_speed=?" +
                    ", fuel_costs=?, route_type=?, rating=?, travel_mode=?, toll_roads=? WHERE log_name=?;\n");
            statement.setString(1,logs.getName());
            statement.setString(2,logs.getDate());
            statement.setString(3,logs.getDuration());
            statement.setString(4,distance);
            statement.setString(5,speed);
            statement.setString(6,fuel);
            statement.setString(7,logs.getRoute_type());
            statement.setString(8,rate);
            statement.setString(9,logs.getTravel_mode());
            statement.setString(10,toll);
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void deleteLogData(String name) throws SQLException {

        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM logs WHERE log_name = ?; ");
            statement.setString(1,name);
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
