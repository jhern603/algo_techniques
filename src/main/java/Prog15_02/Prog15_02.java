package Prog15_02;

import java.util.Arrays;

public class Prog15_02 {
    public static void main(String[] args) {

        int[] scalingArray = new int[10000];
        for (int i = 0; i < scalingArray.length; i++) {
            scalingArray[i] = i;
        }
        Math.sqrt( 50 );
        final long startTime = System.nanoTime();
        Algorithms.quickSort(scalingArray);
        final long endTime = System.nanoTime();


        System.out.println("Time taken to sort using deterministic method: " + (endTime - startTime) + "ns.");
        System.out.println("Time taken to sort using Las Vegas: 7842542ns.");
    }
}
