import static java.lang.Math.min;
import static java.lang.Math.pow;

public class Vehicle extends GameObject{

    Line line;
    int stopInLine = 0;
    double unitsSinceIntersection = 0;
    GameObject location;
    long watingNanos = 10;
    Intersection nextIntersection;

    public Vehicle(Line line) {
        super(0, 0);

        this.line = line;
        location = line.getIntersections().get(0);
    }

    protected void update(long nanos){

    }

    private int drive(double speed, long nanos){
        double distance = speed*nanos;
        
        if (((Track) location).getLength()-unitsSinceIntersection > distance){
            unitsSinceIntersection += distance;
            return 0;
        }else{
            unitsSinceIntersection = 0;
            double overdistance = distance - (((Track) location).getLength() - unitsSinceIntersection);
            location = nextIntersection;
            return (int) (overdistance/speed);
        }
    }

    private long StationWait(long nanos){
        int WAITINGTIME = (int) pow(10,10);
        watingNanos = Math.max(watingNanos-nanos,0);
        if (watingNanos == 0){
            watingNanos = WAITINGTIME;
            //setze das Ziel auf den nächsten halt
            stopInLine += 1;
            stopInLine %= line.size();
            //nächsten track finden
            Dijkstra dijkstra = new Dijkstra((Intersection) location,line.getIntersections().get(stopInLine));
            nextIntersection = dijkstra.getNextIntersection();
            location = dijkstra.getNextTrack();
            unitsSinceIntersection = 0.0;
        }
        return Math.max(nanos-watingNanos,0);
    }

    private Intersection findNextIntersection(Intersection start, Intersection end){
        return null;
    }


    @Override
    public int getX() {
        return location.getX();
    }

    @Override
    public int getY() {
        return location.getY();
    }
}
