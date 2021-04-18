package sample.models;

import lombok.Getter;
import lombok.Setter;

public class Tour {
    @Getter @Setter private String t_Name;
    @Getter @Setter private double t_Distance;
    @Getter @Setter private String startPoint;
    @Getter @Setter private String destination;
    @Getter @Setter private String description;

    Tour(){}
    Tour(String name, String desc, String start, String dest, double distance){

        t_Name = name;
        description = desc;
        startPoint = start;
        destination = dest;
        t_Distance = distance;
    }
}
