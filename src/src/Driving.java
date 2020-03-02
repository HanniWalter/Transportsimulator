import java.util.List;

public class Driving {

    private List<Vehicle> vehicles;
    World world= World.getInstance();



    Driving(){
        vehicles = world.getVehicles();


    }

    void update(long nanos){
        for(Vehicle v:vehicles){
            double SPEED = 0.00001; //units per nano;
            while (nanos != 0){
                if (location instanceof Track){
                    nanos = drive(SPEED,nanos);
                }else if(location instanceof Intersection){
                    if(location == line.getIntersections().get(this.stopInLine)){
                        //Fall das er an einer Haltestelle ist
                        if (watingNanos == 0){
                            //setze das Ziel auf den nächsten halt
                            stopInLine += 1;
                            stopInLine %= line.size();
                            //nächsten track finden
                            Dijkstra dijkstra = new Dijkstra((Intersection) location,line.getIntersections().get(stopInLine));
                            nextIntersection = dijkstra.getNextIntersection();
                            location = dijkstra.getNextTrack();
                            unitsSinceIntersection = 0.0;
                        }else{
                            nanos = StationWait(nanos);
                        }
                    }else{
                        //nächsten track finden
                        Dijkstra dijkstra = new Dijkstra((Intersection) location,line.getIntersections().get(stopInLine));
                        nextIntersection = dijkstra.getNextIntersection();
                        location = dijkstra.getNextTrack();
                        unitsSinceIntersection = 0.0;
                    }
                }
            }
        }

    }



    int getLocationX(Vehicle v) {
        return 0;
    }

    int getLocationY(Vehicle v) {
        return 0;
    }


}
