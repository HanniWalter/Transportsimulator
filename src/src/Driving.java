import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Math.pow;

public class Driving {
    static Driving instance;

    private List<Vehicle> vehicles;
    World world= World.getInstance();

    private Map<Vehicle,GameObject> location = new HashMap<>();
    //watingnanos
    private Map<Vehicle, Long> unitsSinceIntersection = new HashMap<>();
    private Map<Vehicle, Intersection> nextIntersection = new HashMap<>();
    private Map<Vehicle, Integer> stopInLine = new HashMap<>();
    private Map<Vehicle, Long> watingNanos = new HashMap<>();

    static Driving getInstance(){
        if (instance==null) instance = new Driving();
        return instance;
    }

    protected void test(){
        for(Vehicle v: vehicles){


            System.out.println("Location: "+getLocationGO(v));
            System.out.print("X:");
            System.out.print(v.getX());
            System.out.print("     Y:");
            System.out.println(v.getY());
            System.out.print("distance since last stop:    ");
            System.out.println(unitsSinceIntersection.get(v));
        }
    }

    private Driving(){
        vehicles = world.getVehicles();

        for (Vehicle v:vehicles) {
            this.location.put(v,v.getLine().getIntersections().get(0));
            this.unitsSinceIntersection.put(v,0L);
            this.stopInLine.put(v,new Integer(0));
            this.watingNanos.put(v,170L);
            Dijkstra dijkstra = new Dijkstra(
                    (Intersection) location.get(v),
                    v.getLine().getIntersections().get(stopInLine.get(v)));
            this.nextIntersection.put(v, dijkstra.getNextIntersection());
        }
    }

    void update(long nanos){
        for(Vehicle v:vehicles){
            while (nanos != 0){
                System.out.println(nanos);
                if (location.get(v) instanceof Track){
                    nanos = drive(v,nanos);
                }else if(location.get(v) instanceof Intersection){

                    if(location.get(v) == v.getLine().getIntersections().get(stopInLine.get(v))){

                        //Fall das er an einer Haltestelle ist
                        if (watingNanos.get(v) == 0){
                            //setze das Ziel auf den nächsten halt
                            increaseStopsInLine(v);
                            //nächsten track finden
                            findNextIntersection(v);
                        }else{
                            nanos = StationWait(v,nanos);
                        }
                    }else{
                        //nächsten track finden
                        findNextIntersection(v);
                    }
                }
            }
        }
    }

    private void increaseStopsInLine(Vehicle v){
        stopInLine.replace(v,(stopInLine.get(v)+1)%v.getLine().size());
    }

    private long drive(Vehicle v, long nanos){
        double SPEED = 0.00001; //units per nano;
        double distance = SPEED*nanos;



        if (((Track) location.get(v)).getLength()-unitsSinceIntersection.get(v) > distance){
            unitsSinceIntersection.replace(v, ((long) distance) + unitsSinceIntersection.get(v));

            return 0;
        }else{
            long overdistance = (long) (distance - (((Track) location.get(v)).getLength() - unitsSinceIntersection.get(v)));////
            unitsSinceIntersection.replace(v, 0L);
            location.replace(v,nextIntersection.get(v));

            return (int) (overdistance/SPEED);
        }
    }

    private void findNextIntersection(Vehicle v){
        Dijkstra dijkstra = new Dijkstra(
                (Intersection) location.get(v),
                v.getLine().getIntersections().get(stopInLine.get(v)));
        nextIntersection.replace(v,dijkstra.getNextIntersection());
        location.replace(v, dijkstra.getNextTrack());
        unitsSinceIntersection.replace(v,0L);
    }

    private long StationWait(Vehicle v,long nanos){
        System.out.println("angekommen an: "+ location.get(v));
        long WAITINGTIME = (long) pow(10,6);
        watingNanos.replace(v,Math.max(watingNanos.get(v)-nanos,0L));
        if (watingNanos.get(v) == 0L){
            watingNanos.replace(v, WAITINGTIME);
            //setze das Ziel auf den nächsten halt
            increaseStopsInLine(v);
            //nächsten track finden
            findNextIntersection(v);
            return Math.max(nanos-watingNanos.get(v),0);
        }
        return 0L;
    }

    int getLocationX(Vehicle v) {
        return getLocationGO(v).getX();
    }

    int getLocationY(Vehicle v) {
        return getLocationGO(v).getY();
    }

    GameObject getLocationGO(Vehicle v){return location.get(v);}

}
