package World;

import grafics.MainWindow;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class World {




    LocalDateTime dateTime;
    int x;
    int y;
    List<Track> tracks;
    List<Intersection> intersections;
    List<Line> lines;
    List<Vehicle> vehicles;
    static World instance;

    public World() {
        this.dateTime = LocalDateTime.of(970,1,1,0,0);
        this.x = 100;
        this.y = 100;

        tracks = new ArrayList<Track>();
        intersections = new ArrayList<Intersection>();
        lines = new ArrayList<Line>();
        vehicles = new ArrayList<Vehicle>();

    }

    public static World getInstance() {
        if (instance == null){
            instance = new World();
        }
        return instance;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public List<Intersection> getIntersections() {
        return intersections;
    }

    public List<Line> getLines() {
        return lines;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public int update(long nanotime){
        LocalDateTime dateTimeold = dateTime;
        dateTime = dateTime.plusNanos(nanotime);

        //hier die updatecalls einrichten

        Driving.getInstance().update(nanotime);

        return 0;
    }

    public boolean addTrack(Intersection intersection1, Intersection intersection2){
        tracks.add(new Track(intersection1,intersection2));

        return true;
    }

    public boolean addIntersection(int x,int y){
        Intersection i = new Intersection(x,y);
        intersections.add(i);

        return true;
    }

    public boolean addLine(List<Intersection> intersections){
        lines.add(new Line(intersections));

        return true;
    }

    public boolean addVehicle(Line line){
        vehicles.add(new Vehicle(line));

        return true;
    }
}
