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
        int leftX = 100;
        int topY = 100;
        int width = 50;
        int height = 50;
        int labelX = 17;
        int labelY = 31;
        int gridWidth = 150;

        //1->2
        drawEdge(
                g,
                leftX + width,
                topY + height / 2,
                leftX + gridWidth,
                topY + height / 2);
        //1->4
        drawEdge(
                g,
                leftX + width / 2,
                topY + height,
                leftX + gridWidth,
                topY + height / 2 + gridWidth);
        int numRows = 0;
        int numColumns = 0;
        for (int i = 0; i < 20; i++) {
            numRows += i % 5 == 0 ? 1 : 0;
            numColumns = i % 5 == 0 ? 0 : i % 5;
            drawVertex(g, leftX + (gridWidth * numColumns), topY + (gridWidth * numRows), width, height, labelX, labelY, i + 1);
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
