package World;

import java.util.List;

public class Line {
    private List<Intersection> intersections;

    public List<Intersection> getIntersections() {
        return intersections;
    }

    public Intersection get(int i){
        return getIntersections().get(i);
    }

    public int size(){
        return intersections.size();
    }

    public void setIntersections(List<Intersection> intersections) {
        this.intersections = intersections;
    }

    public Line(List<Intersection> intersections) {
        this.intersections = intersections;
    }
}
