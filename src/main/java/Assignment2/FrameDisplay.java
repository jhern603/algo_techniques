package Assignment2;

/**
 * Defines a frame a panel with the drawings will be added to.
 *
 * @author Prof. Antonio Hernandez
 */
<<<<<<< HEAD
public class FrameDisplay extends javax.swing.JFrame {
    int WIDTH = 864;
    int HEIGHT = 864;
=======
public class FrameDisplay extends javax.swing.JFrame
{
    int WIDTH = 850;
    int HEIGHT = 720;
>>>>>>> cca2f843655f7ff29df5ef5fbf1fe5e40188643e

    public FrameDisplay() {
        setTitle("2D Plane Display");
        setSize(WIDTH, HEIGHT);

        GraphDisplay panel = new GraphDisplay();
        add(panel);
    }
}
