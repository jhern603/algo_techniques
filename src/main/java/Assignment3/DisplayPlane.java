package Assignment3;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class DisplayPlane extends javax.swing.JPanel {

    ArrayList<Point> points;
    int[][] coords;

    public DisplayPlane(ArrayList<Point> points) {
        this.points = points;
    }


    public void paint(Graphics g) {

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
            coords[i][0] = this.points.get(i).getPoint()[0] + new Random().nextInt(200);
            coords[i][1] = this.points.get(i).getPoint()[1] + new Random().nextInt(200);
            drawEdge(g, coords[i - 1][0] + radius / 2, coords[i - 1][1] + radius / 2, coords[i][0] + radius / 2, coords[i][1] + radius / 2, Color.BLACK);
        }
        for (int i = 0; i < sizePlane; i++) {
            drawVertex(g, coords[i][0], coords[i][1], radius, radius, labelX, labelY, i, Color.ORANGE);
        }


    }


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

}
