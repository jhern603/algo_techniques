package Assignment3;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/*
Assignment 3 Done in joint Team Collaboration Between Jose Hernandez and Ziad Malik
PID: 5712864, 6174850
This Class is for the Displaying of the window that will Visualize our Graphs for us. 
The Graph is passed into this Class where it is then painted and displayed for the user.
 */
public class DisplayPlane extends javax.swing.JPanel {

    ArrayList<Point> points;
    int[][] coords;

    public DisplayPlane(ArrayList<Point> points) {
        this.points = points;
    }

    public void paint(Graphics g) {
        int[] solution = Assignment3.getSolutionRoute();
        // Dimensions for boundary box of circle
        int diameter = 100;
        // radius of circle
        int radius = diameter / 2;
        // coordinate for circle content
        int labelX = 17;
        int labelY = 31;
        int sizePlane = this.points.size();
        coords = new int[sizePlane][2];


        //Draws Vertices with Edges
        for (int i = 1; i < sizePlane; i++) {
            coords[i][0] = this.points.get(i).getPoint()[0] + radius + new Random().nextInt(100) + 100;
            coords[i][1] = this.points.get(i).getPoint()[1] + radius + new Random().nextInt(100) + 100;
            int fromX = coords[i - 1][0] + radius / 2;
            int fromY = coords[i - 1][1] + radius / 2;
            int toX = coords[i][0] + radius / 2;
            int toY = coords[i][1] + radius / 2;
            drawEdge(g, fromX, fromY, toX, toY, Color.BLACK);
        }

        //Draw Edge Method Call
        drawEdge(g, coords[sizePlane - 1][0] + radius / 2, coords[sizePlane - 1][1] + radius / 2, coords[0][0] + radius / 2, coords[0][1] + radius / 2, Color.BLACK);

        for (int i = 1; i < sizePlane; i++) {
            int fromX = coords[i - 1][0] + radius / 2;
            int fromY = coords[i - 1][1] + radius / 2;
            int toX = coords[i][0] + radius / 2;
            int toY = coords[i][1] + radius / 2;
            drawWeight(g, (fromX + toX) / 2, (fromY + toY) / 2, String.valueOf(points.get(i - 1).getWeight()));
        }
        drawWeight(g, ((coords[sizePlane - 1][0] + radius / 2) + (coords[0][0] + radius / 2)) / 2, ((coords[sizePlane - 1][1] + radius / 2) + (coords[0][1] + radius / 2)) / 2, String.valueOf(points.get(points.size() - 1).getWeight()));

        for (int i = 0; i < sizePlane; i++) {
            drawVertex(g, coords[i][0], coords[i][1], radius, radius, labelX, labelY, solution[i], Color.ORANGE);
        }

        int n = 0;
        for (int i = 1; i < points.size(); i++) {
            n += Point.euclideanDistance(points.get(solution[i - 1]), points.get(solution[i]));
        }
        String output = "The cost for the shortest path is " + n;
        String path = "Shortest path: " + Arrays.toString(solution);
        String timeToSolution;
        if ((Assignment3.endTime - Assignment3.startTime) >= 1000000000) {
            timeToSolution = "Time to solution: " + (Assignment3.endTime - Assignment3.startTime) / 1000000000 + "s";
        } else if ((Assignment3.endTime - Assignment3.startTime) >= 1000000) {
            timeToSolution = "Time to solution: " + (Assignment3.endTime - Assignment3.startTime) / 1000000 + "ms";
        } else {
            timeToSolution = "Time to solution: " + (Assignment3.endTime - Assignment3.startTime) + "ns";
        }
        g.setColor(Color.BLACK);
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        g.drawString(output, 50, 50);
        g.drawString(path, 50, 75);
        g.drawString(timeToSolution, 50, 100);

    }

    //Drawing Edge Void Method Class that takes parameters of points in.
    //Followed by other methods that are used to help the visulaization of the Graph
    private void drawEdge(Graphics g, int x1, int y1, int x2, int y2, Color color) {
        g.setColor(color);
        g.drawLine(x1, y1, x2, y2);
    }

    private void drawVertex(
            Graphics g,
            int leftX,
            int topY,
            int width,
            int height,
            int labelX,
            int labelY,
            int i,
            Color color) {
        g.setColor(color);
        g.fillOval(leftX, topY, width, height);
        g.setColor(Color.BLACK);
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
        g.drawString(String.valueOf(i), leftX + labelX, topY + labelY);
    }

    private void drawWeight(Graphics g, int x, int y, String weight) {
        g.setColor(Color.BLACK);
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
        g.drawString(weight, x + 2, y + 2);
    }

}
