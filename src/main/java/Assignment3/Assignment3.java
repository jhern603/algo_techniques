package Assignment3;

import Prog18_05.PermutationNeighborhood;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Jose Hernandez, PID 5712864
 * @author Ziad Malik, PID 6174850
 * Tester class to find the Optimal Solution to the TSP Problem using Local Search
 */
public class Assignment3 {
    int numPoints;
    ArrayList<Point> points;
    static int[] solutionRoute;
    static long startTime;
    static long endTime;

    public static void main(String[] args) {
        new Assignment3();
    }

    /**
     * Entrypoint of tester
     */
    public Assignment3() {
        points = new ArrayList<>();
        String pathToFile = "src/main/java/Assignment3/points.txt";
        numPoints = getPointsFromFile(points, pathToFile);
        FrameDisplay frame = new FrameDisplay(points);

        startTime = System.nanoTime();
        TSP_localsearch(points);
        endTime = System.nanoTime();

        for (int i = 0; i < numPoints - 1; i++) {
            System.out.printf("Distance between %d and %d: %d\n", i, i + 1, Point.euclideanDistance(points.get(i), points.get(i + 1)));
            points.get(i).setWeight(Point.euclideanDistance(points.get(i), points.get(i + 1)));
        }
        points.get(numPoints - 1).setWeight(Point.euclideanDistance(points.get(0), points.get(numPoints - 1)));
        System.out.printf("Distance between %d and %d: %d\n", numPoints - 1, 0, Point.euclideanDistance(points.get(numPoints - 1), points.get(0)));


        TSP_localsearch(points);

        System.out.println("====Distance between points after TSP====");
        //Recalculate weights
        for (int i = 0; i < points.size() - 1; i++) {
            System.out.printf("Distance between %d and %d: %d\n", solutionRoute[i], solutionRoute[i + 1], Point.euclideanDistance(points.get(solutionRoute[i]), points.get(solutionRoute[i + 1])));
            points.get(i).setWeight(Point.euclideanDistance(points.get(solutionRoute[i]), points.get(solutionRoute[i + 1])));
        }
        points.get(numPoints - 1).setWeight(Point.euclideanDistance(points.get(solutionRoute[0]), points.get(solutionRoute[numPoints - 1])));

        verboseStatistics(startTime, endTime);

        frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * Prints statistics for the points in the program's input that includes:
     * Distance between points both before, and after executing the TSP algorithm
     * Number of points in the input
     * Execution time
     * Weights of points before, and after executing the TSP algorithm
     * Number of possible connections for each point
     * The shortest route for TSP
     *
     * @param startTime initial machine time before TSP
     * @param endTime   final machine time after TSP
     */
    private void verboseStatistics(long startTime, long endTime) {
        System.out.println("====Distance between points before TSP====");
        for (int i = 1; i < numPoints - 1; i++) {
            System.out.printf("Distance between %d and %d: %d\n", i - 1, i, Point.euclideanDistance(points.get(i - 1), points.get(i)));
        }
        System.out.printf("Distance between %d and %d: %d\n", numPoints - 1, 0, Point.euclideanDistance(points.get(numPoints - 1), points.get(0)));

        System.out.println("====Distance between points after TSP local search====");
        for (int i = 1; i < numPoints - 1; i++) {
            System.out.printf("Distance between %d and %d: %d\n", solutionRoute[i - 1], solutionRoute[i], Point.euclideanDistance(points.get(solutionRoute[i - 1]), points.get(solutionRoute[i])));
        }
        System.out.printf("Distance between %d and %d: %d\n", solutionRoute[numPoints - 1], solutionRoute[0], points.get(numPoints - 1).getWeight());

        System.out.println("====Statistics on 2D Plane====");
        System.out.println("Number of possible connections: " + (numPoints * (numPoints - 1)) / 2);
        System.out.println("Number of points: " + numPoints);
        int n = 0;
        for (int i = 0; i < numPoints; i++) {
            n += points.get(i).getWeight();
        }
        System.out.println("Total distance of original plane: " + n);
        n = 0;
        for (int i = 1; i < numPoints; i++) {
            n += Point.euclideanDistance(points.get(solutionRoute[i - 1]), points.get(solutionRoute[i]));
        }
        System.out.println("Total distance for TSP plane: " + n);
        if ((endTime - startTime) >= 1000000000) {
            System.out.println("Time to find solution: " + (endTime - startTime) / 1000000000 + "s");
        } else if ((endTime - startTime) >= 1000000) {
            System.out.println("Time to find solution: " + (endTime - startTime) / 1000000 + "ms");
        } else {
            System.out.println("Time to find solution: " + (endTime - startTime) + "ns");
        }
        System.out.println("Shortest route found in local search: " + Arrays.toString(solutionRoute));
    }

    /**
     * Getter method for the solution path in array format
     *
     * @return solution array
     */
    public static int[] getSolutionRoute() {
        return solutionRoute;
    }

    /**
     * Reads a text file for points in a 2D plane and converts it into an ArrayList of Point objects
     *
     * @param points     Arraylist to store each point in
     * @param pathToFile Fully qualified path for the input text file
     * @return Number of points in the file
     */
    private static int getPointsFromFile(ArrayList<Point> points, String pathToFile) {
        int numPoints;
        File input = new File(pathToFile);

        Scanner in = null;
        try {
            in = new Scanner(input);
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            System.exit(0);
        }
        numPoints = in.nextInt();
        while (in.hasNextLine()) {
            points.add(new Point(in.nextInt(), in.nextInt()));
        }
        in.close();

        for (int i = 0; i < numPoints - 1; i++) {
            points.get(i).setWeight(Point.euclideanDistance(points.get(i), points.get(i + 1)));
        }
        points.get(numPoints - 1).setWeight(Point.euclideanDistance(points.get(0), points.get(numPoints - 1)));

        return numPoints;
    }

    /**
     * Local Search Done in order to find a Solution. This algorithm searches within the Locality of its Initial Start point for Better Solutions If No Neighbors are "Better", we return the Solution
     * NOTE: Could Return Local Minimum, not Global Minimum.
     *
     * @param shortestRoute ArrayList of Point to perform algorithm on
     * @return The best calculated distance
     * @see Prog19_05
     */
    public int TSP_localsearch(ArrayList<Point> shortestRoute) {
        int bestDistance;
        int[] shortRoute = new int[numPoints];
        int[] a = new int[numPoints];
        for (int i = 0; i < numPoints; i++) {
            shortRoute[i] = shortestRoute.get(i).getWeight();
        }

        randomPermutation(a);
        System.arraycopy(a, 0, shortRoute, 0, numPoints);
        bestDistance = totalDistance(a);

        boolean betterSolutionFound;
        do {
            betterSolutionFound = false;
            PermutationNeighborhood pn = new PermutationNeighborhood(shortRoute);

            while (pn.hasNext()) {
                a = pn.next();
                int currentDistance = totalDistance(a);
                if (currentDistance < bestDistance) {
                    System.arraycopy(a, 0, shortRoute, 0, numPoints);
                    bestDistance = currentDistance;
                    betterSolutionFound = true;
                }
            }
        } while (betterSolutionFound);
        solutionRoute = a;
        return bestDistance;
    }

    /**
     * Calculates the total distance between points in an array of distances
     *
     * @param a array of points
     * @return total distance between points
     * @see Prog19_05
     */
    int totalDistance(int[] a) {
        int n = numPoints - 1;
        int totalWeight = 0;
        for (int i = 0; i < n; i++) {
            totalWeight += a[i];
        }
        return totalWeight;
    }

    /**
     * Random Permutation Selection from All Permutations Method which we discussed in class.
     *
     * @param a array of points by weight
     * @see Prog19_05
     */
    public void randomPermutation(int[] a) {
        for (int i = 0; i < a.length; i++) {
            a[i] = i;
        }
        Random r = new Random();
        for (int i = a.length - 1; i > 0; i--) {
            int randLoc = r.nextInt(i + 1);
            if (randLoc != i) {
                int temp = a[i];
                a[i] = a[randLoc];
                a[randLoc] = temp;
            }
        }
    }

}
