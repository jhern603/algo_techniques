package Assignment2;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;


public class GraphDisplay extends javax.swing.JPanel {
    public Graph input = new Graph("src/main/java/Assignment2/5by5Graph.txt");

    public void paint(Graphics g) {

        // Dimensions for boundary box of circle
        int diameter = 100;
        // radius of circle
        int radius = diameter / 2;
        // coordinate for circle content
        int labelX = 17;
        int labelY = 31;
        // How much to space each circle apart
        int gridWidth = 150;
        int numElements = 0;
        for (int i = 0; i < input.getVerticesNumber(); i++) {
            for (int j = 0; j < input.getVerticesNumber(); j++) {
                if (input.getMatrix()[i][j] != 0) numElements++;
            }
        }


        int y = 0;
        int x = 0;
        int[][] coords = new int[numElements][2];
        int graphSize = (int) Math.ceil(Math.sqrt(numElements));
        Random r = new Random();

        for (int i = 0; i < numElements; i++) {
            if (x == graphSize) {
                x = 0;
                y++;
            }
            //x
            coords[i][0] = r.nextInt(numElements * graphSize) + (gridWidth * x++);
            //y
            coords[i][1] = r.nextInt(numElements * graphSize) + (gridWidth * y);
        }

        //Next, print the weighted graph
        for (int i = 1; i < numElements; i++) {
            for (int j = 1; j < numElements; j++) {
                int xFrom = coords[i - 1][0] + radius / 2;
                int yFrom = coords[i - 1][1] + radius / 2;
                int xTo = coords[j][0] + radius / 2;
                int yTo = coords[j][1] + radius / 2;
                if (input.getMatrix()[(i - 1) % graphSize][(j - 1) % graphSize] != 0)
                    drawWeight(g, (xFrom + xTo) / 2, (yFrom + yTo) / 2, String.valueOf(input.getMatrix()[(i - 1) % graphSize][(j - 1) % graphSize]));
                drawEdge(g, xFrom, yFrom, xTo, yTo);
            }
        }

        for (int i = 0; i < numElements; i++) {
            drawVertex(
                    g,
                    coords[i][0],
                    coords[i][1],
                    radius,
                    radius,
                    labelX,
                    labelY,
                    i + 1);
        }
    }

    private void drawVertex(
            Graphics g,
            int leftX,
            int topY,
            int width,
            int height,
            int labelX,
            int labelY,
            int i) {


        g.setColor(Color.ORANGE);
        g.fillOval(leftX, topY, width, height);
        g.setColor(Color.BLACK);
        g.drawOval(leftX, topY, width, height);
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));

        g.drawString(String.valueOf(i), leftX + labelX, topY + labelY);
    }

    private void drawWeight(Graphics g, int x, int y, String weight) {
        g.setColor(Color.BLACK);
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
        g.drawString(weight, x + 2, y + 2);
    }

    private void drawEdge(Graphics g, int x1, int y1, int x2, int y2) {
        g.setColor(Color.BLACK);
        g.drawLine(x1, y1, x2, y2);
    }
}
