package Prog18_03;

import javax.swing.*;

/**
 * Defines a frame a panel with the drawings will be added to.
 *
 * @author Prof. Antonio Hernandez
 */
public class FrameDisplay extends JFrame {
    int WIDTH = 1080;
    int HEIGHT = 1080;

    public FrameDisplay() {
        setTitle("Graph Display");
        setSize(WIDTH, HEIGHT);

        GraphDisplay panel = new GraphDisplay();
        add(panel);
    }
}
