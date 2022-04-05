package Assignment2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
<<<<<<< HEAD
 * Borrowd from Prog15_08
=======
 * @author Jose Hernandez, PID: 5712864
 * @author Ziad Malik, PID: 6174850
 */

/**
 * Borrowd from 5
>>>>>>> cca2f843655f7ff29df5ef5fbf1fe5e40188643e
 */
public class Graph {

    private int verticesNumber;
    private int[][] matrix;
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
        boolean done = false;
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
        return new int[]{s, t};
    }

    public int[][] getMatrix() {
        return matrix;
    }

    /**
     * Finds vertices adjacent to a given vertex.
     *
     * @param v given vertex
     * @return list of vertices adjacent to v stored in an array; size of array = number of adjacent
     * vertices
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

<<<<<<< HEAD
    int minDistance(int[] path_array, boolean[] sptSet, int target) {
        // Initialize min value
=======
    /**
     * Calculates the minimum distance between all vertices up to target
     *
     * @param path_array Array of distances
     * @param sptSet     Array to annotate whether a vertex has been visited
     * @param target     What vertex to stop at
     * @return an integer minimum distance
     */
    int minDistance(int path_array[], boolean sptSet[], int target) {

>>>>>>> cca2f843655f7ff29df5ef5fbf1fe5e40188643e
        int min = Integer.MAX_VALUE, min_index = -1;
        for (int v = 0; v < target; v++)
            if (!sptSet[v] && path_array[v] <= min) {
                min = path_array[v];
                min_index = v;
            }

        return min_index;
    }

<<<<<<< HEAD
    int[] dijkstra(int src, int target) {

        int[] path_array = new int[target]; // The output array. dist[i] will hold
        // the shortest distance from src to i

        // spt (shortest path set) contains vertices that have shortest path
        boolean[] sptSet = new boolean[target];
=======
    /**
     * Implementation of Dijkstra's shortest path algorithm using a priority queue
     *
     * @param src    The beginning vertex
     * @param target The target vertex
     * @return An array with distance between the src vertex and target vertex
     */
    int[] dijkstra(int src, int target) {

        int path_array[] = new int[target];
        boolean sptSet[] = new boolean[target];
>>>>>>> cca2f843655f7ff29df5ef5fbf1fe5e40188643e

        for (int i = 0; i < target; i++) {
            path_array[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }

        path_array[src] = 0;
        for (int count = 0; count < target - 1; count++) {
            int u = minDistance(path_array, sptSet, target);
            sptSet[u] = true;
            for (int v = 0; v < target; v++)
                if (!sptSet[v] && matrix[u][v] != 0 && path_array[u] !=
                        Integer.MAX_VALUE && path_array[u]
                        + matrix[u][v] < path_array[v])
                    path_array[v] = path_array[u] + matrix[u][v];
        }

        return path_array;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();

        for (int i = 0; i < verticesNumber; i++) {
            for (int j = 0; j < verticesNumber; j++) {
                s.append(matrix[i][j]).append(" ");
            }
            s.append("\n");
        }

        return s.toString();
    }
}
