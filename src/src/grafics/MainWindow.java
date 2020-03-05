package grafics;

import World.World;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;

public class MainWindow extends JFrame {
    World world = World.getInstance();

    public MainWindow(){
        super("test");
        this.setBounds(10,10,1000,1000);
        super.setVisible(true);
    }

    public void render(){
        //JLabel l = new JLabel(world.getDateTime().format(DateTimeFormatter.ofPattern("dd-MM-yyyy,hh:MM:ss")),JLabel.CENTER);

        //l.setVisible(true);
        //this.add(l);
        this.setTitle(world.getDateTime().format(DateTimeFormatter.ofPattern("dd-MM-yyyy,hh:mm:ss")));
    }
}
