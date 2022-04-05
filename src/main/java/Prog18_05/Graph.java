package Prog18_05;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Implements a Graph. Uses an adjacency matrix to represent the graph.
 *
 * @author Prof. Antonio Hernandez
 */
public class Graph implements Prog18_01.GraphInterface {

    private int verticesNumber;
    private int[][] matrix; // adjacency matrix

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

        while (in.hasNextLine()) {
            verticesNumber = in.nextInt();
            matrix = new int[verticesNumber][verticesNumber];

            for (int i = 0; i < verticesNumber; i++) {
                for (int j = 0; j < verticesNumber; j++) {
                    matrix[i][j] = in.nextInt();
                }
            }
        }

        in.close();
    }

    public int getVerticesNumber() {
        return verticesNumber;
    }

    public void addEdge(int v, int w) {
        matrix[v][w] = 1;
        matrix[w][v] = 1;
    }

    public void removeEdge(int v, int w) {
        matrix[v][w] = 0;
        matrix[w][v] = 0;
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

    public void BFT(int v) {
        boolean[] visited = new boolean[verticesNumber];

        for (int i = 0; i < verticesNumber; i++) {
            visited[i] = false;
        }

        Queue vertexQueue = new Queue();
        vertexQueue.enqueue(v);
        visited[v] = true;

        while (!vertexQueue.isEmpty()) {
            int w = vertexQueue.getFront();
            System.out.println(w);
            vertexQueue.dequeue();

            int[] adj = findAdjacencyVertices(w);

            for (int u : adj) {
                if (!visited[u]) {
                    vertexQueue.enqueue(u);
                    visited[u] = true;
                }
            }
        }
    }

    private int minDistance(boolean[] visited, int[] distance) {
        int index = -1;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < verticesNumber; i++) {
            if (!visited[i]) {
                if (distance[i] <= min) {
                    min = distance[i];
                    index = i;
                }
            }
        }
        return index;
    }

    public void allShortestPaths(int[] p, int[] d, int v) {
        boolean[] visited = new boolean[verticesNumber];

        for (int i = 0; i < verticesNumber; i++) {
            visited[i] = false;
            p[i] = -1;
            d[i] = Integer.MAX_VALUE;
        }

        d[v] = 0;

        for (int i = 0; i < verticesNumber - 1; i++) {
            int w = minDistance(visited, d);
            visited[w] = true;

            int[] adj = findAdjacencyVertices(w);
            for (int u : adj) {
                if (!visited[u]) {
                    if (d[w] + matrix[w][u] < d[u]) {
                        d[u] = d[w] + matrix[w][u];
                        p[u] = w;
                    }
                }
            }
        }
    }

    public int TSP_exhaustiveSearch(int[] shortestRoute) {
        for (int i = 0; i < verticesNumber; i++) {
            shortestRoute[i] = i;
        }

        int[] a = new int[verticesNumber];
        TSP_exhaustiveSearch(shortestRoute, a, 0);
        return totalDistance(shortestRoute);
    }

    int totalDistance(int[] a) {
        int n = verticesNumber;
        int totalWeight = 0;
        for (int i = 0; i < n; i++) {
            int weight = matrix[a[i]][a[(i + 1) % n]];
            totalWeight += weight;
        }
        return totalWeight;
    }

    private void TSP_exhaustiveSearch(int[] shortestRoute, int[] a, int k) {
        if (k == a.length) {
            if (totalDistance(a) < totalDistance(shortestRoute))
                System.arraycopy(a, 0, shortestRoute, 0, verticesNumber);
        } else {
            ArrayList<Integer> Sk = constructCandidateSet(a, k);
            for (int s : Sk) {
                a[k] = s;
                TSP_exhaustiveSearch(shortestRoute, a, k + 1);
            }
        }
    }

    private ArrayList<Integer> constructCandidateSet(int[] a, int k) {
        ArrayList<Integer> candidates = new ArrayList<>();
        boolean[] b = new boolean[a.length];

        for (int i = 0; i < k; i++) {
            b[a[i]] = true;
        }
        for (int i = 0; i < a.length; i++) {
            if (!b[i]) candidates.add(i);
        }
        return candidates;
    }

    private void printArray(int[] a) {
        for (int v : a) {
            System.out.println(v + " ");
        }
        System.out.println();
    }

    int minDistance(int[] dist, boolean[] sptSet, int target) {
        int min = Integer.MAX_VALUE, min_index = -1;
        for (int v = 0; v < target; v++)
            if (!sptSet[v] && dist[v] <= min) {
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

                    if (!spt[vertex_V] && matrix[vertex_U][vertex_V] != INFINITY) {
                        int newKey = matrix[vertex_U][vertex_V] + distance[vertex_U];
                        if (newKey < distance[vertex_V]) distance[vertex_V] = newKey;
                    }
                }
            }
        }

        return Arrays.copyOf(distance, target);
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
