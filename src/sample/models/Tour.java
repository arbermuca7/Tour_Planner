package sample.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Tour {
    @Getter @Setter private String identification;
    @Getter @Setter private String t_Name;
    @Getter @Setter private double t_Distance;
    @Getter @Setter private String startPoint;
    @Getter @Setter private String destination;
    @Getter @Setter private String description;
    @Getter @Setter private List<Log> logsForTour;
    /**
     * The standard constructor takes
     * some random placeholders
     * */
    Tour(){
        t_Name = "TourExp";
        description = "this is my muster tour";
        startPoint = "Street 1";
        destination = "Street 3";
        t_Distance = 10.4;
    }
    /**
     * The parameter constructor takes as parameters
     * @param name as the name of a tour
     * @param desc as the description
     * @param start as the starting point of the tour
     * @param dest as the ending point of the tour
     * @param distance as the distance in km of this tour
     * */
    public Tour(String identification, String name, String desc, String start, String dest, double distance){
        this.identification = identification;
        t_Name = name;
        description = desc;
        startPoint = start;
        destination = dest;
        t_Distance = distance;
    }
    public Tour(String identification, String name, String desc, String start, String dest, double distance,List<Log>logs){
        this.identification = identification;
        t_Name = name;
        description = desc;
        startPoint = start;
        destination = dest;
        t_Distance = distance;
        logsForTour = logs;
    }
}
