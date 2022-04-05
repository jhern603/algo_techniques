package Assignment2;

/**
 * Defines a frame a panel with the drawings will be added to.
 *
 * @author Prof. Antonio Hernandez
 */
public class FrameDisplay extends javax.swing.JFrame
{
    int WIDTH = 850;
    int HEIGHT = 720;

    public FrameDisplay() {
        setTitle("2D Plane Display");
        setSize(WIDTH, HEIGHT);

        GraphDisplay panel = new GraphDisplay();
        add(panel);
    }
}
