import java.util.*;

public class Dijkstra {
    Intersection start;
    Intersection end;
    World world = World.getInstance();
    List<Intersection> nodes = world.getIntersections();
    Map<Intersection,Double> weigths = new HashMap<Intersection,Double>();
    Map<Intersection,Boolean> visited = new HashMap<Intersection,Boolean>();
    Map<Intersection,Intersection> prev = new HashMap<Intersection,Intersection>();

    Dijkstra(Intersection start, Intersection end){
        //setup
        this.start = start;
        this.end = end;
        for (int i = 0;i<nodes.size();i++){
            weigths.put(nodes.get(i),Double.POSITIVE_INFINITY);
            visited.put(nodes.get(i),false);
            prev.put(nodes.get(i),null);
        }

        weigths.replace(start,0.0);

        //algoritmus
        while(true){
            Intersection lowest = findLowestNod();
            visited.replace(lowest,true);
            if (lowest == null){
                break;
            }
            for (Track track:lowest.getTracks()){
                Intersection nextIntersection = track.getOtherIntersection(lowest);
                double fromHereDistance = weigths.get(lowest)+track.getLength();

                if(weigths.get(nextIntersection)>fromHereDistance){
                    weigths.replace(nextIntersection,fromHereDistance);
                    prev.replace(nextIntersection,lowest);
                }
            }
        }
    }


    Intersection findLowestNod(){
        Intersection returner = null;
        Double returnDistance = Double.POSITIVE_INFINITY;
        for(Intersection n:nodes){
            if (!weigths.get(n).isInfinite()&&!visited.get(n)){
                if (weigths.get(n)<returnDistance){
                    returner = n;
                    returnDistance = weigths.get(n);
                }
            }
        }
        return returner;
    }

    boolean isConnected(){
        return (!(weigths.get(end).isInfinite()));
    }

    Intersection getNextIntersection(){
        if(!(isConnected())) return null;

        Intersection intersection = end;
        while (prev.get(intersection)!=start) intersection = prev.get(intersection);
        return intersection;
    }

    Track getNextTrack(){
        if(!(isConnected())) return null;
        for (Track track:
             world.getTracks()) {
            if(((track.getIntersection1() == start)&&(track.getIntersection2()==getNextIntersection()))||((track.getIntersection2() == start)&&(track.getIntersection1()==getNextIntersection()))){
                return track;
            }
        }
        return null;
    }
}
