package World;

import java.util.ArrayList;
import java.util.List;

public class Intersection extends GameObject {

    public Intersection(int x, int y) {
        super(x, y);
    }

    public List<Track> getTracks(){
        List<Track> tracks = new ArrayList<Track>();
        for (Track t:
            World.getInstance().getTracks()) {
            if((t.getIntersection1() == this) || (t.getIntersection2() == this)){
                tracks.add(t);
            }
        }
        return tracks;
    }

    @Override
    public String toString() {
        return "World.Intersection" + super.toString();
    }
}
