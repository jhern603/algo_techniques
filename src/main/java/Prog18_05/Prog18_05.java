package Prog18_05;

public class Prog18_05 {
    public static void main(String[] args) {
        Graph g = new Graph("src/main/java/Prog18_05/Prog18_05 - graph 01.txt");

        System.out.println(g.TSP_exhaustiveSearch(g.dijkstra(0, 5)));
    }
}
