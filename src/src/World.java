import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class World {

    public static void main(String[] args) {
	// write your code here

        World world = new World();


        world.addIntersection(100,200);
        world.addIntersection(200,200);
        world.addIntersection(300,150);
        world.addIntersection(400,100);
        world.addIntersection(300,230);
        world.addIntersection(300,300);
        world.addIntersection(500,350);

        world.addTrack(world.getIntersections().get(0),world.getIntersections().get(1));
        world.addTrack(world.getIntersections().get(1),world.getIntersections().get(2));
        world.addTrack(world.getIntersections().get(2),world.getIntersections().get(3));
        world.addTrack(world.getIntersections().get(1),world.getIntersections().get(4));
        world.addTrack(world.getIntersections().get(2),world.getIntersections().get(4));
        world.addTrack(world.getIntersections().get(4),world.getIntersections().get(5));
        world.addTrack(world.getIntersections().get(5),world.getIntersections().get(6));
        world.addTrack(world.getIntersections().get(3),world.getIntersections().get(6));


        Intersection[] interarr = new Intersection[]{world.getIntersections().get(0),world.getIntersections().get(4),world.getIntersections().get(3)};
        List<Intersection> interlist = Arrays.asList(interarr);
        world.addLine(interlist);

        world.addVehicle(world.getLines().get(0));

        World.instance = world;

        System.out.println("begin");
        world.update(123456789012l);
        world.test();
        world.update(100000000000l);
        world.test();
        world.update(100000000000l);
        world.test();
        world.update(100000000000l);
        world.test();
        world.update(100000000000l);
        world.test();
        world.update(100000000000l);
        world.test();

    }



    LocalDateTime dateTime;
    int x;
    int y;
    List<Track> tracks;
    List<Intersection> intersections;
    List<Line> lines;
    List<Vehicle> vehicles;
    static World instance;

    private void test(){
        System.out.println(getDateTime());
        for(Vehicle v: vehicles){


            System.out.println(v.location);
            System.out.print("X:");
            System.out.print(v.getX());
            System.out.print("     Y:");
            System.out.println(v.getY());
            System.out.print("distance since last stop:    ");
            System.out.println(v.unitsSinceIntersection);
        }
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



    public static World getInstance(){
        return instance;
    }

    public int update(long nanotime){
        LocalDateTime dateTimeold = dateTime;
        dateTime = dateTime.plusNanos(nanotime);

        //hier die updatecalls einrichten

        for (Vehicle v:
             vehicles) {
            v.update(nanotime);
        }

        return 0;
    }

    public World() {
        this.dateTime = LocalDateTime.of(970,1,1,0,0);
        this.x = 100;
        this.y = 100;

        tracks = new ArrayList<Track>();
        intersections = new ArrayList<Intersection>();
        lines = new ArrayList<Line>();
        vehicles = new ArrayList<Vehicle>();

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
