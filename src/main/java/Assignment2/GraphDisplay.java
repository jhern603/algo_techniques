package Assignment2;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Arrays;
import java.util.Random;


public class GraphDisplay extends javax.swing.JPanel {


//EXPLORE: print the whole graph WITH THE WEIGHTS, then just reprint the lines in a different color to depict shortest path
    public void paint(Graphics g) {
        Graph input = new Graph("src/main/java/Assignment2/5by5Graph.txt");
        int target = 5;
        int src = 0;
        int[] shortestPath = input.dijkstra(src, target);
        System.out.printf("Shortest path between %d and %d is : ", src, target);
        for (int i = 0; i < shortestPath.length; i++) {
            System.out.print(i + ", ");
        }
        System.out.println();
        System.out.println("Distance between each vertex");
        System.out.println(Arrays.toString(shortestPath));
        int numElements = shortestPath.length;
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
        int[][] coords = new int[numElements][2];
        int graphSize = (int) Math.ceil(Math.sqrt(numElements));
        setCoordinates(gridWidth, numElements, y, x, coords, graphSize);

        //Next, print the weighted graph
        for (int i = src + 1, j =0; i < numElements; i++) {
                int xFrom = coords[i - 1][0] + radius / 2;
                int yFrom = coords[i - 1][1] + radius / 2;
                int xTo = coords[i][0] + radius / 2;
                int yTo = coords[i][1] + radius / 2;
//                drawWeight(g, (xFrom + xTo) / 2, (yFrom + yTo) / 2, String.valueOf(input.getWeights()[i]));

                drawEdge(g, xFrom, yFrom, xTo, yTo);
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
                    i) ;
        }
    }

    private void setCoordinates(int gridWidth, int numElements, int y, int x, int[][] coords, int graphSize) {
        Random r = new Random();

        for (int i = 0; i < numElements; i++) {
            if (x == graphSize) {
                x = 0;
                y++;
            }
            //x
            coords[i][0] = (gridWidth * x++) + r.nextInt(numElements * 4 * graphSize);
            //y
            coords[i][1] = (gridWidth * y) + r.nextInt(numElements * 4 * graphSize);
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
        g.setColor(Color.MAGENTA);
        g.fillOval(leftX, topY, width, height);
        g.setColor(Color.BLACK);
//        g.drawOval(leftX, topY, width, height);
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
