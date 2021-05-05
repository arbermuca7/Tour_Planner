package sample.dataAccessLayer.database;
import javafx.collections.ObservableList;
import sample.models.Log;
import sample.models.Tour;

import java.sql.*;

public class DatabaseAccess implements IDataAccess {

    String url = "jdbc:postgresql://localhost:5432/postgres";
    String user = "postgres";
    String pwd = "admin";

    public DatabaseAccess(){}
    @Override
     public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, pwd);
            System.out.println("-->Connection Successful");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public void openConnection(String url, String user, String pass) {
        /*try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url,user,pass);
            System.out.println("-->Connection Successfully Opened");
        }catch (ClassNotFoundException | SQLException e){
            e.getStackTrace();
        }*/
    }
    @Override
    public void closeConnection(){
        /*try {
            connection.close();
            System.out.println("-->Connection Successfully Closed");
        }catch (SQLException e){
            e.getStackTrace();
        }*/

    }


    public void GetTours(ObservableList<Tour> tourObservableList){
        String query = "SELECT * FROM tour";
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
                //System.out.println("ID: "+ident+", Name: "+name+", Description: "+desc+", Distance: "+dist+", Start: "+start+", Destin: "+dest);

                Tour tour = new Tour(ident,name,desc,start,dest,dist);

                /*System.out.println("-->Tour-->ID: "+tour.getIdentification()+", Name: "+tour.getT_Name()+", Description: "+tour.getDescription()
                        +", Distance: "+tour.getT_Distance()+", Start: "+tour.getStartPoint()+", Destin: "+tour.getDestination());*/

                //add the database tours to observable list
                tourObservableList.add(tour);
            }
            stmt.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    //add the tour to the databaseTable
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
    @Override
    public void editTourData(Tour tour, String id) {
        try (Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement("UPDATE tour SET bezeichnung=?, description=?, distance=?, startpoint=?, destination=? WHERE identification=?;\n");
            statement.setString(1,tour.getT_Name());
            statement.setString(2, tour.getDescription());
            statement.setDouble(3,tour.getT_Distance());
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
    public void deleteTourData(String id){
        try (Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement("DELETE FROM tour WHERE identification = ?; ");
            statement.setString(1,id);
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void addLogData(Log logs,String identific) {
        try (Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement("INSERT INTO logs VALUES (?,?,?,?,?,?,?,?,?,?,?); ");
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
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void editLogData(Log logs) {
        try (Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement("UPDATE logs SET log_name=?, l_date=?, duration=?, distance=?, avg_speed=?" +
                    ", fuel_costs=?, route_type=?, rating=?, travel_mode=?, toll_roads=? WHERE log_name=?;\n");
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
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

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
