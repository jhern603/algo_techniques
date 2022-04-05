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

    int minDistance(int[] path_array, boolean[] sptSet, int target) {
        // Initialize min value
        int min = Integer.MAX_VALUE, min_index = -1;
        for (int v = 0; v < target; v++)
            if (sptSet[v] == false && path_array[v] <= min) {
                min = path_array[v];
                min_index = v;
            }

        return min_index;
    }

    int[] dijkstra(int src, int target) {

        int[] path_array = new int[target]; // The output array. dist[i] will hold
        // the shortest distance from src to i

        // spt (shortest path set) contains vertices that have shortest path
        boolean[] sptSet = new boolean[target];

        // Initially all the distances are INFINITE and stpSet[] is set to false
        for (int i = 0; i < target; i++) {
            path_array[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }

        // Path between vertex and itself is always 0
        path_array[src] = 0;
        // now find shortest path for all vertices
        for (int count = 0; count < target - 1; count++) {
            // call minDistance method to find the vertex with min distance
            int u = minDistance(path_array, sptSet, target);
            // the current vertex u is processed
            sptSet[u] = true;
            // process adjacent nodes of the current vertex
            for (int v = 0; v < target; v++)

                // if vertex v not in sptset then update it
                if (!sptSet[v] && matrix[u][v] != 0 && path_array[u] !=
                        Integer.MAX_VALUE && path_array[u]
                        + matrix[u][v] < path_array[v])
                    path_array[v] = path_array[u] + matrix[u][v];
        }

        return path_array;
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
