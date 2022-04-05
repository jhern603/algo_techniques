package Prog14_01;

import java.util.Arrays;
import java.util.Random;

public class Prog14_01 {

    private int comparisonCount;

    /**
     * Tester.
     */
    public Prog14_01() {
        Random r = new Random();

        int[] list = {6, 0, 0, 4, 1, 2, 6, 6};
        //      new int[ 10 ];
        //  fillArray( list );
        printArray(list);
        Arrays.sort(list);
        printArray(list);

        int x = 9;
        //      r.nextInt( 10 );
        System.out.println(x + " is in the array: " + binarySearch(list, x));
        System.out.println("Number of comparisons: " + comparisonCount);
    }

    public static void main(String[] args) {
        new Prog14_01();
    }

    /**
     * Fills given array with random numbers in [0, 9].
     */
    public void fillArray(int[] list) {
        Random r = new Random();
        for (int i = 0; i < list.length; i++) {
            list[i] = r.nextInt(10);
        }
    }

    /**
     * Prints given array
     */
    public void printArray(int[] list) {
        for (int j : list) {
            System.out.print(j + " ");
        }
        System.out.println();
    }

    /**
     * Binary Search method
     *
     * @param list sorted array
     * @param x    search element
     * @return true if x is in list; false otherwise
     */
    public boolean binarySearch(int[] list, int x) {
        return binarySearch(list, 0, list.length - 1, x);
    }

    /**
     * Determines if search element is in locations [first, last] of given array.
     *
     * @param list  sorted array
     * @param first left index
     * @param last  right index
     * @param x     search element
     * @return true if x is in location [first, last] of array; false otherwise
     */
    private boolean binarySearch(int[] list, int first, int last, int x) {
        boolean found;
        comparisonCount++;

        if (first > last) {
            found = false;
        } else {
            int mid = (first + last) / 2;
            comparisonCount++;
            if (list[mid] == x) {
                found = true;
            } else {
                comparisonCount++;
                if (x < list[mid]) {
                    found = binarySearch(list, first, mid - 1, x);
                } else {
                    found = binarySearch(list, mid + 1, last, x);
                }
            }
        }
        return found;
    }
}
