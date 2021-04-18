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

    Log(){}
    Log(String date, String duration, double distance, int avg_speed, float fuel_cost,
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
