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

        drawEdge(
            g,
            leftX + width,
            topY + height / 2,
            leftX + gridWidth,
            topY + height / 2);
        drawEdge(
                g,
                leftX + width / 2,
                topY + height,
                leftX + gridWidth,
                topY + height / 2 + gridWidth);

        // draw vertex 1
        drawVertex( g, leftX, topY, width, height, labelX, labelY, "1" );
        drawVertex( g, leftX + gridWidth, topY, width, height, labelX, labelY, "2" );
        drawVertex( g, leftX + gridWidth, topY + gridWidth, width, height, labelX, labelY, "3" );


    }

private void drawVertex( java.awt.Graphics g, int leftX, int topY, int width, int height, int labelX, int labelY,
                         String content ){
    g.setColor( java.awt.Color.ORANGE);
    g.fillOval( leftX, topY, width, height );
    g.setColor( java.awt.Color.BLACK);
    g.drawOval( leftX, topY, width, height );
    g.setFont(new java.awt.Font( java.awt.Font.SANS_SERIF, java.awt.Font.BOLD, 24));
    g.drawString(content, leftX + labelX, topY + labelY );
}

private void drawEdge(java.awt.Graphics g, int x1, int y1, int x2, int y2) {
        g.setColor(java.awt.Color.BLACK);
        g.drawLine(x1, y1, x2, y2);
    }
}
