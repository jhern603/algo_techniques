package Assignment3;

import Prog18_05.PermutationNeighborhood;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Assignment3 {
    int numPoints;
    ArrayList<Point> points;
    static int[] solutionRoute;
    public static void main(String[] args) {
        new Assignment3();
    }

    public Assignment3() {
        points = new ArrayList<>();

        String pathToFile = "src/main/java/Assignment3/points.txt";
        numPoints = getPointsFromFile(points, pathToFile);

        System.out.println("====Distance between points before TSP====");
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
            System.out.printf("Distance between %d and %d: %d\n", solutionRoute[i], solutionRoute[i+1], Point.euclideanDistance(points.get(solutionRoute[i]), points.get(solutionRoute[i + 1])));
            points.get(i).setWeight(Point.euclideanDistance(points.get(solutionRoute[i]), points.get(solutionRoute[i + 1])));
        }
        points.get(numPoints - 1).setWeight(Point.euclideanDistance(points.get(solutionRoute[0]), points.get(solutionRoute[numPoints - 1])));
        System.out.printf("Distance between %d and %d: %d\n", solutionRoute[numPoints-1], solutionRoute[0], points.get(numPoints-1).getWeight());



        System.out.println("Number of possible connections: " + (numPoints * (numPoints - 1)) / 2);
        System.out.println("Number of points: " + numPoints);

        FrameDisplay frame = new FrameDisplay(points);
        frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static int[] getSolutionRoute(){
        return solutionRoute;
    }

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
        return numPoints;
    }

    public int TSP_localsearch(ArrayList<Point> shortestRoute) {
        int bestDistance;
        int[] shortRoute = new int[numPoints];
        int[] a = new int[numPoints];
        for (int i = 0; i < numPoints; i++) {
            shortRoute[i] = shortestRoute.get(i).getWeight();
        }

        randomPermutation(a);
        System.arraycopy(a, 0, shortRoute, 0 , numPoints);
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

    int totalDistance(int[] a) {
        int n = numPoints - 1;
        int totalWeight = 0;
        for (int i = 0; i < n; i++) {
            totalWeight += Point.euclideanDistance(points.get(i), points.get(i + 1));
        }
        return totalWeight;
    }

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
