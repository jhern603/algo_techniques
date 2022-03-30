package Assignment2;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Arrays;
import java.util.Random;

public class GraphDisplay extends javax.swing.JPanel {

    // EXPLORE: print the whole graph WITH THE WEIGHTS, then just reprint the lines in a different
    // color to depict shortest path
    public void paint(Graphics g) {
        Graph input = new Graph("src/main/java/Assignment2/5by5Graph.txt");

        int numElements;
        // Dimensions for boundary box of circle
        int diameter = 100;
        // radius of circle
        int radius = diameter / 2;
        // coordinate for circle content
        int labelX = 17;
        int labelY = 31;
        // How much to space each circle apart
        int gridWidth = 150;

        int y = 0;
        int x = 0;
        int[][] coords;
        int graphSize;

        numElements = (int) Math.pow(input.getVerticesNumber(), 2);
        graphSize = input.getVerticesNumber();
        coords = new int[numElements][2];
        setCoordinates(gridWidth, numElements, y, x, coords, graphSize);
        drawEdges(g, 0, graphSize, radius, coords, Color.BLACK);
        for (int i = 0; i < graphSize; i++) {
            drawVertex(g, coords[i][0], coords[i][1], radius, radius, labelX, labelY, i, java.awt.Color.ORANGE);
        }

        for (int i = 1; i < graphSize; i++) {
            for (int j = 1; j < graphSize; j++) {
                int xFrom = coords[i - 1][0] + radius / 2;
                int yFrom = coords[i - 1][1] + radius / 2;
                int xTo = coords[j][0] + radius / 2;
                int yTo = coords[j][1] + radius / 2;
                if (input.getMatrix()[(i - 1) % graphSize][(j - 1) % graphSize] != 0)
                    drawWeight(g, (xFrom + xTo) / 2, (yFrom + yTo) / 2,
                        String.valueOf(input.getMatrix()[i][j]));
            }
        }


        drawDijkstraGraph( g, input, radius, labelX, labelY, coords );
    }

private void drawDijkstraGraph( Graphics g, Graph input, int radius, int labelX,
                        int labelY, int[][] coords ){
    int numElements;
    int target = 5;
    int src = 2;
    int[] shortestPath = input.dijkstra(src, target);
    numElements = shortestPath.length;
    drawEdges( g, src, numElements, radius, coords, java.awt.Color.MAGENTA);
    for (int i = src; i < numElements; i++) {
        drawVertex( g, coords[i][0], coords[i][1], radius, radius, labelX, labelY, i, java.awt.Color.MAGENTA);
    }
}

private void drawEdges(
            java.awt.Graphics g, int src, int numElements, int radius, int[][] coords, Color color) {
        for (int i = src + 1; i < numElements; i++) {
            int xFrom = coords[i - 1][0] + radius / 2;
            int yFrom = coords[i - 1][1] + radius / 2;
            int xTo = coords[i][0] + radius / 2;
            int yTo = coords[i][1] + radius / 2;
            g.setColor(color);
            g.drawLine(xFrom, yFrom, xTo, yTo);
        }
    }

    private void setCoordinates(
            int gridWidth, int numElements, int y, int x, int[][] coords, int graphSize) {
        Random r = new Random();

        for (int i = 0; i < numElements; i++) {
            if (x == graphSize) {
                x = 0;
                y++;
            }
            // x
            coords[i][0] = (gridWidth * x++) + r.nextInt(numElements * graphSize);
            // y
            coords[i][1] = (gridWidth * y) + r.nextInt(numElements * graphSize);
        }
    }

    private void drawVertex(
            Graphics g, int leftX, int topY, int width, int height, int labelX, int labelY, int i, Color color) {
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
/*
        System.out.printf("Shortest path between %d and %d is : ", src, target);
        for (int i = 0; i < shortestPath.length; i++) {
            System.out.print(i + ", ");
        }
        System.out.println();
        System.out.println("Distance between each vertex");
        System.out.println(Arrays.toString(shortestPath));

 */