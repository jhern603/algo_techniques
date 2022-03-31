package Assignment2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/** Borrowd from Prog15_08 */
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

    public int[][] getMatrix() {
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

    int minDistance(int dist[], boolean sptSet[], int target) {
        int min = Integer.MAX_VALUE, min_index = -1;
        for (int v = 0; v < target; v++)
            if (sptSet[v] == false && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }

        return min_index;
    }

    int[] dijkstra(int src, int target) {
        boolean[] spt = new boolean[target];
        int[] distance = new int[target];
        int INFINITY = Integer.MAX_VALUE;

        for (int i = 0; i < target; i++) {
            distance[i] = INFINITY;
        }
        distance[src] = 0;

        for (int i = 0; i < target; i++) {
            int vertex_U = minDistance(distance, spt, target);
            spt[vertex_U] = true;

            for (int vertex_V = 0; vertex_V < target; vertex_V++) {
                if (matrix[vertex_U][vertex_V] > 0) {

                    if (spt[vertex_V] == false && matrix[vertex_U][vertex_V] != INFINITY) {
                        int newKey = matrix[vertex_U][vertex_V] + distance[vertex_U];
                        if (newKey < distance[vertex_V]) distance[vertex_V] = newKey;
                    }
                }
            }
        }

        return Arrays.copyOf(distance, target);
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
