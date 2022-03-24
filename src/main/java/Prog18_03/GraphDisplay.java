package Prog18_03;

import javax.swing.*;
import java.awt.*;

/**
 * Defines a panel the drawings will be made in.
 *
 * @author Prof. Antonio Hernandez
 */
public class GraphDisplay extends JPanel {
    /**
     * Paints the graph example.
     *
     * @param g graphics context
     */
    public void paint(Graphics g) {
        // Dimensions for boundary box of circle
        int leftX = 100;
        int topY = 100;
        // radius of circle
        int radius = 50;
        // coordinate for circle content
        int labelX = 17;
        int labelY = 31;
        // How much to space each circle apart
        int gridWidth = 120;

        int numElements = 16;

        int horizCenter = leftX + radius / 2;
        int vertCenter = topY + radius / 2;

        /*
        x & y should represent the position in the adjacency matrix
         */
        int row = 0;
        for (int i = 0, j = 0; i < numElements; i++) {
            if(i % (int) Math.sqrt(numElements) == 0){
                j++;
                row = 0;
            }
            row++;
            drawEdge(
                    g,
                    radius * i,
                    radius * row,
                    horizCenter + (gridWidth * i),
                    vertCenter + (gridWidth * row));

            drawVertex(
                    g,
                    leftX + (gridWidth * j),
                    topY + (gridWidth * row),
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
            int num) {

        String content = String.valueOf(num);
        g.setColor(Color.ORANGE);
        g.fillOval(leftX, topY, width, height);
        g.setColor(Color.BLACK);
        g.drawOval(leftX, topY, width, height);
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
        g.drawString(content, leftX + labelX, topY + labelY);
    }

    private void drawEdge(Graphics g, int x1, int y1, int x2, int y2) {
        g.setColor(Color.BLACK);
        g.drawLine(x1, y1, x2, y2);
    }
}
