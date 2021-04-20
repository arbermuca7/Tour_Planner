package sample.models;

import lombok.Getter;
import lombok.Setter;

public class Tour {
    @Getter @Setter private String t_Name;
    @Getter @Setter private double t_Distance;
    @Getter @Setter private String startPoint;
    @Getter @Setter private String destination;
    @Getter @Setter private String description;

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
    public Tour(String name, String desc, String start, String dest, double distance){

        t_Name = name;
        description = desc;
        startPoint = start;
        destination = dest;
        t_Distance = distance;
    }
}
