package Assignment3;

import java.util.ArrayList;


/**
 * @author Jose Hernandez, PID 5712864
 * @author Ziad Malik, PID 6174850
 * This Class is the Frame Display Class that Creates the Panel onto which we display the Graph
 */
public class FrameDisplay extends javax.swing.JFrame {
    int WIDTH = 864;
    int HEIGHT = 864;

    public FrameDisplay(ArrayList<Point> points) {
        setTitle("2D Plane Display");
        setSize(WIDTH, HEIGHT);

        DisplayPlane panel = new DisplayPlane(points);
        add(panel);
    }
}