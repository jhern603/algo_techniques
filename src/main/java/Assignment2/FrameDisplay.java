package Assignment2;

/**
 * Defines a frame a panel with the drawings will be added to.
 * 
 * @author Prof. Antonio Hernandez
 */
public class FrameDisplay extends javax.swing.JFrame
{
    int WIDTH = 450;
    int HEIGHT = 450;

    public FrameDisplay()
    {
        setTitle("Graph Display");
        setSize(WIDTH, HEIGHT);

        GraphDisplay panel = new GraphDisplay();
        add(panel);
    }
}