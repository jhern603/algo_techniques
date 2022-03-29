package Assignment2;

import java.awt.Graphics;

/**
 * Defines a panel the drawings will be made in.
 *
 * @author Prof. Antonio Hernandez
 */
public class GraphDisplay extends javax.swing.JPanel {
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
        int center = leftX + radius / 2;
        int labelX = 17;
        int labelY = 31;
        // How much to space each circle apart
        int gridWidth = 150;

        int numRows = 0;
        int numElements = 20;
        int numColumns;
        int graphSize = (int) Math.ceil(Math.sqrt(numElements));
        System.out.println(graphSize);
        for (int i = 0; i < numElements; i++) {
            numColumns = i % graphSize;
            if (i % graphSize == 0)  numRows += 1;
            drawVertex(
                    g,
                    leftX + (gridWidth * numColumns),
                    topY + (gridWidth * numRows),
                    radius,
                    radius,
                    labelX,
                    labelY,
                    i + 1);
        }

        int numColumnsTo = 0;
        int numRowsTo = 0;
        for (int i = 0; i < numElements; i++) {
            numColumns = i % graphSize;
            if (i % graphSize == 0)  numRows += 1;

            for (int j = 0; j < numElements; j++) {
                numColumnsTo = i % graphSize;
                if (i % graphSize == 0)  numRowsTo += 1;
                drawEdge(
                    g,
                    leftX + (gridWidth * numColumns),
                    topY + (gridWidth * numRows),
                    leftX + (gridWidth * numColumnsTo),
                    topY + (gridWidth * numRowsTo)
                );
            }
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
        g.setColor(java.awt.Color.ORANGE);
        g.fillOval(leftX, topY, width, height);
        g.setColor(java.awt.Color.BLACK);
        g.drawOval(leftX, topY, width, height);
        g.setFont(new java.awt.Font(java.awt.Font.SANS_SERIF, java.awt.Font.BOLD, 24));
        g.drawString(content, leftX + labelX, topY + labelY);
    }

    private void drawEdge(Graphics g, int x1, int y1, int x2, int y2) {
        g.setColor(java.awt.Color.BLACK);
        g.drawLine(x1, y1, x2, y2);
    }
}
