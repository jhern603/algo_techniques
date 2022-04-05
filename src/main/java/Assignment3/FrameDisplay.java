package Assignment3;

import java.util.ArrayList;

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