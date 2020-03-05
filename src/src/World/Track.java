package World;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Track extends GameObject{
    private Intersection intersection1;

    public Intersection getIntersection1() {
        return intersection1;
    }

    public Intersection getIntersection2() {
        return intersection2;
    }

    private Intersection intersection2;

    public Track(Intersection intersection1, Intersection intersection2) {
        super((intersection1.getX()+ intersection2.getX())/2, (intersection1.getY()+ intersection2.getY())/2);
        this.intersection1 = intersection1;
        this.intersection2 = intersection2;
    }

    public double getLength(){
        return sqrt( pow(intersection1.getX()-intersection2.getX(),2) + pow(intersection1.getY()-intersection2.getY(),2));
    }

    public Intersection getOtherIntersection(Intersection i1){
        if (i1 == intersection1)return intersection2;
        return  intersection1;
    }

    @Override
    public String toString() {
        return "World.Track{" +
                "intersection1=" + intersection1 +
                ", intersection2=" + intersection2 +
                '}';
    }
}
