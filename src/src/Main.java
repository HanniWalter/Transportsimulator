import World.*;
import grafics.MainWindow;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // write your code here

        World world = World.getInstance();

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

        MainWindow mainWindow = new MainWindow();

        for (;;){
            world.update(1000000000000l);
            mainWindow.render();
            //World.Driving.getInstance().test();
        }
    }
}
