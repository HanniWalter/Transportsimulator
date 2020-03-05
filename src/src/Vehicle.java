import static java.lang.Math.min;
import static java.lang.Math.pow;

public class Vehicle extends GameObject{

    private Line line;

    public Line getLine() {
        return line;
    }

    private Intersection nextIntersection;

    public Vehicle(Line line) {
        super(0, 0);

        this.line = line;
    }

    private Intersection findNextIntersection(Intersection start, Intersection end){
        return null;
    }


    @Override
    public int getX() { return Driving.getInstance().getLocationX(this); }

    @Override
    public int getY() {
        return Driving.getInstance().getLocationY(this);
    }
}
