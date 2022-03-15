package Prog16_01;

import java.util.Random;

public class Prog16_01 {
    public static void main(String[] args) {
        Random r = new Random();
        BinarySearchTree tree = new BinarySearchTree();
        int rand;
        for (int i = 0; i < 2<<20; i++) {
            rand = r.nextInt();
            System.out.printf("%d ", rand);
            tree.add(rand);
        }
        System.out.println();
        tree.BFS();
        System.out.println( tree );

    }
}
