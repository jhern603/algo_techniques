package Assignment2;



import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Arrays;
import java.util.Random;

public class GraphDisplay extends javax.swing.JPanel {
    Graph input = new Graph("src/main/java/Assignment2/5by5Graph.txt");
    boolean[][] drawn;

    public void paint(Graphics g) {

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

        graphSize = input.getVerticesNumber() + 1;
        numElements = (int) Math.pow(graphSize, 2);
        coords = new int[numElements][2];

        System.out.println(input.getVerticesNumber());
        for (int i = 0; i < input.getVerticesNumber(); i++) {
            for (int j = 0; j < input.getVerticesNumber(); j++) {
                System.out.print(input.getMatrix()[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println(Arrays.toString(input.getBounds()));

        //Graphical Output
        setCoordinates(gridWidth, numElements, y, x, coords, graphSize);
        drawCompleteGraph(g, radius, labelX, labelY, coords, graphSize);
        drawDijkstraGraph(g, input, radius, labelX, labelY, coords);
    }

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

        String output = "The cost for the shortest path between " + src + " and " + (target-1) + " is " + sum;
        String path = "Shortest path: " + Arrays.toString(shortestPath);
        g.setColor(Color.BLACK);
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        g.drawString(output, 50, 50);
        g.drawString(path, 50, 75);
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

    private void drawWeight(Graphics g, int x, int y, String weight) {
        g.setColor(Color.BLACK);
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
        g.drawString(weight, x + 2, y + 2);
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
            coords[i][0] = (gridWidth * x++) + r.nextInt(graphSize-1);
            // y
            coords[i][1] = (gridWidth * y * 2) + r.nextInt((int) Math.pow(graphSize-1, 4));
        }
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
