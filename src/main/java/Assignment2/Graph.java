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

    public Graph(int[] d, int len) {

            matrix = new int[len][0];
                for (int i = 0; i < len; i++) {
                    for (int j = 0; j < verticesNumber; j++) {
                        matrix[i][j] = d[i];
                    }
                }



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

    int minDistance(int dist[], Boolean sptSet[], int target)
    {
        int min = Integer.MAX_VALUE, min_index = -1;
        for (int v = 0; v < target; v++)
            if (sptSet[v] == false && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }

        return min_index;
    }

    int[] dijkstra(int src, int target)
    {
        int dist[] = new int[target+1];
        Boolean visited[] = new Boolean[target+1];

        for (int i = 0; i < target; i++) {
            dist[i] = Integer.MAX_VALUE;
            visited[i] = false;
        }
        dist[src] = 0;

        for (int count = 0; count < target; count++) {
            int u = minDistance(dist, visited, target);
            visited[u] = true;

            for (int v = 0; v < target; v++) {
                boolean vertexExists = matrix[u][v] != 0;
                if (!visited[v] && vertexExists && dist[u] != Integer.MAX_VALUE && dist[u] + matrix[u][v] < dist[v])
                    dist[v] = dist[u] + matrix[u][v];
            }
        }

        return dist;
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
