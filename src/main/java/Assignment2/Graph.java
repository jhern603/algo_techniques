package Assignment2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Borrowd from Prog15_08
 */
public class Graph {

    private int verticesNumber;
    private int[][] matrix; // adjacency matrix
    private int s;
    private int t;

    public Graph() {
        verticesNumber = 5;
        matrix = new int[verticesNumber][verticesNumber];
    }

    public Graph(int n) {
        verticesNumber = n;
        matrix = new int[verticesNumber][verticesNumber];
    }

    /**
     * Instantiates a graph and initializes it with info from a text file.
     *
     * @param filename text file with graph info
     */
    public Graph(String filename) {
        File input = new File(filename);

        Scanner in = null;
        try {
            in = new Scanner(input);
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            System.exit(0);
        }
        Boolean done = false;
        while (in.hasNextLine()) {
            verticesNumber = in.nextInt();
            matrix = new int[verticesNumber][verticesNumber];
            if (!done)
                for (int i = 0; i < verticesNumber; i++) {
                    for (int j = 0; j < verticesNumber; j++) {
                        matrix[i][j] = in.nextInt();
                    }
                }
            done = true;
            s = in.nextInt();
            t = in.nextInt();
        }

        in.close();
    }

    public int getVerticesNumber() {
        return verticesNumber;
    }

    public int[] getBounds() {
        return new int[] {s, t};
    }

    public int[][] getMatrix(){
        return matrix;
    }

    /**
     * Finds vertices adjacent to a given vertex.
     *
     * @param v given vertex
     * @return list of vertices adjacent to v stored in an array; size of array = number of adjacent
     *     vertices
     */
    public int[] findAdjacencyVertices(int v) {
        int[] vert = new int[verticesNumber];
        int total = 0;

        for (int i = 0; i < verticesNumber; i++) {
            if (matrix[v][i] != 0) {
                vert[total] = i;
                total++;
            }
        }

        return Arrays.copyOf(vert, total);
    }

    public String toString() {
        String s = "";

        for (int i = 0; i < verticesNumber; i++) {
            for (int j = 0; j < verticesNumber; j++) {
                s += matrix[i][j] + " ";
            }
            s += "\n";
        }

        return s;
    }
}
