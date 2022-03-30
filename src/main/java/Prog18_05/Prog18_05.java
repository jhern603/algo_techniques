package Prog18_05;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Prog18_05 {
    public static void main(String[] args) {
        Graph g = new Graph("src/main/java/Prog18_05/Prog18_05 - graph 01.txt");
        System.out.println(g);
        g.BFT( 1 );
    }
}
