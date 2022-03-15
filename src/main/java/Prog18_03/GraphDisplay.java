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
        //Dimensions for boundary box of circle
        int leftX = 100;
        int topY = 100;
        //radius of circle
        int radius = 50;
        //coordinate for circle content
        int labelX = 17;
        int labelY = 31;
        //How much to space each circle apart
        int gridWidth = 150;


        int numRows = 0;
        int numColumns;
        int numElements=20;

        for (int i = 0; i < numElements; i++) {

            if (i % 5 == 0)
                if (i == 0) {
                    numRows = 0;
                } else {
                    numRows += 1;
                }

            numColumns = i % 5 == 0 ? 0 : i % 5;
            int x = 0;
            int y;

            for (int j = 0; j < numElements; j++) {
                if (j % 4 == 0)
                    if (j == 0) {
                        x = 0;
                    } else {
                        x += 1;
                    }
                y = j % 4;
                drawEdge(
                        g,
                        leftX + radius / 2 + (gridWidth * x),
                        topY + radius / 2 + (gridWidth * y),
                        leftX + radius / 2 + (gridWidth * numColumns),
                        topY + radius / 2 + (gridWidth * numRows));

            }
            drawVertex(g, leftX + (gridWidth * numColumns), topY + (gridWidth * numRows), radius, radius, labelX, labelY, i + 1);
        }


    }

    private void drawVertex(java.awt.Graphics g, int leftX, int topY, int width, int height, int labelX, int labelY,
                            int num) {

        String content = String.valueOf(num);
        g.setColor(java.awt.Color.ORANGE);
        g.fillOval(leftX, topY, width, height);
        g.setColor(java.awt.Color.BLACK);
        g.drawOval(leftX, topY, width, height);
        g.setFont(new java.awt.Font(java.awt.Font.SANS_SERIF, java.awt.Font.BOLD, 24));
        g.drawString(content, leftX + labelX, topY + labelY);
    }

    private void drawEdge(java.awt.Graphics g, int x1, int y1, int x2, int y2) {
        g.setColor(java.awt.Color.BLACK);
        g.drawLine(x1, y1, x2, y2);
    }
}
