package Prog18_06;

import Prog18_05.Graph;

public class Prog18_06 {
    Graph g;

    Prog18_06() {
        g = new Graph("src/main/java/Prog18_05/Prog18_05 - graph 01.txt");
        DFT(11);
    }

    public static void main(String[] args) {
        new Prog18_06();
    }

    public void DFT(int v) {
        int verticesNumber = g.getVerticesNumber();
        boolean[] visited = new boolean[verticesNumber];

        for (int i = 0; i < verticesNumber; i++) {
            visited[i] = false;
        }

        recursiveDFT(v, visited);

        for (int u = 0; u < verticesNumber; u++) {
            if (!visited[u]) recursiveDFT(v, visited);
        }
        System.out.println();
    }

    private void recursiveDFT(int v, boolean[] visited) {
        visited[v] = true;
        System.out.print(v + " ");
        int[] adj = g.findAdjacencyVertices(v);

        for (int u : adj) {
            if (!visited[u]) recursiveDFT(u, visited);
        }
    }
}
