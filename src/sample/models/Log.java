package sample.models;

import lombok.Getter;
import lombok.Setter;

public class Log {
    @Getter @Setter private String date;
    @Getter @Setter private String duration;
    @Getter @Setter private double distance;
    @Getter @Setter private int avg_speed;
    @Getter @Setter private float fuel_cost;
    @Getter @Setter private boolean toll_roads;
    @Getter @Setter private String travel_mode; //e.g. auto, bus, plane....
    @Getter @Setter private String route_type; //e.g. fastest
    @Getter @Setter private int rating;

    /**
     * The standard constructor takes
     * some random placeholders
     * */
    public Log(){
        date = "2021-01-01";
        duration = "02:40:56";
        distance = 120.5;
        avg_speed = 80;
        fuel_cost = 75.6F;
        route_type = "fastest";
        rating = 3;
        travel_mode = "auto";
        toll_roads = true;
    }
    /**
     * The parameter constructor takes as parameter
     * @param date as the date when we saved the log
     * @param duration how long did the tour last
     * @param distance as the interval of the tour in km
     * @param avg_speed as the speed of the vehicle
     * @param  fuel_cost how much does the fuel for this tour cost
     * @param route_type as the type of the way(road) you took
     * @param rating rate if the route was the best
     * @param travel_mode with what type of vehicle you drove
     * @param toll_roads should you pay for the road
     * and in that way the logs can be saved*/
    public Log(String date, String duration, double distance, int avg_speed, float fuel_cost,
        String route_type, int rating, String travel_mode,boolean toll_roads){
        this.date = date;
        this.duration = duration;
        this.distance = distance;
        this.avg_speed = avg_speed;
        this.fuel_cost = fuel_cost;
        this.route_type = route_type;
        this.rating = rating;
        this.travel_mode = travel_mode;
        this.toll_roads = toll_roads;
    }
}
