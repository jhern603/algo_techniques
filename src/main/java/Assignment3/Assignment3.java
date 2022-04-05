package Assignment3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Assignment3 {
    public static void main(String[] args) {
        ArrayList<Point> points = new ArrayList<>();
        int numPoints;
        String pathToFile = "src/main/java/Assignment3/points.txt";
        numPoints = getPointsFromFile(points, pathToFile);

        for (int i = 0; i < numPoints - 1; i++) {
            System.out.println(Point.euclideanDistance(points.get(i), points.get(i + 1)));
        }

        System.out.println("Number of possible connections: " + (numPoints * (numPoints - 1)) / 2);
        System.out.println("Number of points: " + numPoints);

        FrameDisplay frame = new FrameDisplay(points);
        frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
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


}
