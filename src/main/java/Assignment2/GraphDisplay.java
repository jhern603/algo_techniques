package Assignment2;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Arrays;
import java.util.Random;

/**
 * @author Jose Hernandez, PID: 5712864
 * @author Ziad Malik
 */

public class GraphDisplay extends JPanel {
    Graph input = new Graph("src/main/java/Assignment2/5by5Graph.txt");
    boolean[][] drawn;

    /**
     * Overwritten paint method from JPanel
     *
     * @param g Graphics object
     */
    public void paint(Graphics g) {

        int numElements, graphSize;
        int diameter = 100;
        int radius = diameter / 2;
        int labelX = 17;
        int labelY = 31;
        int gridWidth = 150;
        int[][] coords;

        graphSize = input.getVerticesNumber() + 1;
        numElements = (int) Math.pow(graphSize, 2);
        coords = new int[numElements][2];

        setCoordinates(gridWidth, numElements, coords, graphSize);

        drawCompleteGraph(g, radius, labelX, labelY, coords, graphSize);
        drawDijkstraGraph(g, input, radius, labelX, labelY, coords);
    }

    /**
     * Draws a complete graph
     *
     * @param g         Graphics object
     * @param radius    Radius of vertex
     * @param labelX    Upper left x position of vertex
     * @param labelY    Upper left y position of vertex
     * @param coords    Array of coordinates for each vertex
     * @param graphSize Number of vertices in the graph
     */
    private void drawCompleteGraph(
            java.awt.Graphics g,
            int radius,
            int labelX,
            int labelY,
            int[][] coords,
            int graphSize) {
        drawn = new boolean[graphSize][graphSize];
        for (int i = 1; i < graphSize; i++) {
            for (int j = 1; j < graphSize; j++) {
                int xFrom = coords[i - 1][0] + radius / 2;
                int yFrom = coords[i - 1][1] + radius / 2;
                int xTo = coords[j][0] + radius / 2;
                int yTo = coords[j][1] + radius / 2;
                String weight = String.valueOf(input.getMatrix()[i - 1][j - 1]);
                if (Integer.parseInt(weight) != 0
                        && (!drawn[i - 1][j - 1] && !drawn[j - 1][i - 1])) {
                    drawn[i - 1][j - 1] = true;
                    drawn[j - 1][i - 1] = true;
                    drawWeight(g, (xFrom + xTo) / 2, (yFrom + yTo) / 2, weight);
                    drawEdge(g, xFrom, yFrom, xTo, yTo, Color.BLACK);
                }
            }
        }

        for (int i = 0; i < graphSize; i++) {
            drawVertex(
                    g, coords[i][0], coords[i][1], radius, radius, labelX, labelY, i, Color.ORANGE);
        }
    }

    /**
     * Draws a superimposed graph representing the shortest possible path between two vertices
     *
     * @param g      Graphics object
     * @param input  the input (original) graph
     * @param radius The radius of the vertex
     * @param labelX Upper left x position of vertex
     * @param labelY Upper left y position of vertex
     * @param coords Array of coordinates for each vertex
     */
    private void drawDijkstraGraph(
            Graphics g, Graph input, int radius, int labelX, int labelY, int[][] coords) {
        int numElements;
        int target = input.getBounds()[1] + 1;
        int src = input.getBounds()[0];
        int[] shortestPath = input.dijkstra(src, target);
        numElements = shortestPath.length;

        int sum = 0;
        for (int n : shortestPath
        ) {
            sum += n;
        }


        for (int i = src + 1; i < numElements; i++) {
            drawEdge(g, coords[i - 1][0] + radius / 2, coords[i - 1][1] + radius / 2, coords[i][0] + radius / 2, coords[i][1] + radius / 2, Color.MAGENTA);
        }
        for (int i = src; i < numElements; i++) {
            drawVertex(
                    g,
                    coords[i][0],
                    coords[i][1],
                    radius,
                    radius,
                    labelX,
                    labelY,
                    i,
                    Color.MAGENTA);
        }


//        for (int i = src + 1; i < target; i++) {
//            for (int j = i; j < target; j++) {
//                int xFrom = coords[i - 1][0] + radius / 2;
//                int yFrom = coords[i - 1][1] + radius / 2;
//                int xTo = coords[j][0] + radius / 2;
//                int yTo = coords[j][1] + radius / 2;
//                String weight = String.valueOf(input.getMatrix()[i - 1][j]);
//                if (Integer.parseInt(weight) != 0) {
//                    drawEdge(g, xFrom, yFrom, xTo, yTo, Color.MAGENTA);
//                }
//            }
//        }
        String output = "The cost for the shortest path between " + src + " and " + (target - 1) + " is " + sum;
        String path = "Shortest path: " + Arrays.toString(shortestPath);
        g.setColor(Color.BLACK);
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        g.drawString(output, 50, 50);
        g.drawString(path, 50, 75);
    }

    /**
     * Draws an edge between two vertices
     *
     * @param g     Graphics object
     * @param x1    X-coordinate of the first vertex
     * @param y1    Y-coordinate of the first vertex
     * @param x2    X-coordinate of the second vertex
     * @param y2    Y-coordinate of the second vertex
     * @param color Color for the edge
     */
    private void drawEdge(Graphics g, int x1, int y1, int x2, int y2, Color color) {
        g.setColor(color);
        g.drawLine(x1, y1, x2, y2);
    }

    /**
     * Draws a vertex
     *
     * @param g      Graphics object
     * @param leftX  Upper left x position of vertex
     * @param topY   Upper left y position of vertex
     * @param width  Width of the bounding box for the vertex
     * @param height Height of the bounding box for the vertex
     * @param labelX X-Coordinate for the label of the vertex
     * @param labelY Y-Coordinate for the label of the vertex
     * @param i      The sequential number for the label of the vertex
     * @param color  Color of the vertex
     */
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

    /**
     * Draws the weight of an edge on the graph
     *
     * @param g      Graphics object
     * @param x      X-Coordinate of the weight label
     * @param y      Y-Coordinate of the weight label
     * @param weight The weight to be drawn
     */
    private void drawWeight(Graphics g, int x, int y, String weight) {
        g.setColor(Color.BLACK);
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
        g.drawString(weight, x + 2, y + 2);
    }

    /**
     * Calculates the coordinates for each vertex on a graph
     *
     * @param gridWidth   Amount of space between each vertex
     * @param numElements Num of elements in the graph
     * @param coords      An array for coordinates to be stored
     * @param graphSize   Number of vertices in the graph
     */
    private void setCoordinates(
            int gridWidth, int numElements, int[][] coords, int graphSize) {
        Random r = new Random();
        int x = 0;
        int y = 0;
        for (int i = 0; i < numElements; i++) {

            if (x == graphSize) {
                x = 0;
                y++;
            }
            // x
            coords[i][0] = (gridWidth * x++) + r.nextInt(graphSize - 1);
            // y
            coords[i][1] = (gridWidth * y * 2) + r.nextInt((int) Math.pow(graphSize - 1, 4));
        }
    }

}
