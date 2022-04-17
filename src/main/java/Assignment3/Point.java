package Assignment3;
/*
Assignment 3 Done in joint Team Collaboration Between Jose Hernandez and Ziad Malik
PID: 5712864, 6174850
This Class is The Class for Our Points. each Point can have its Distance found from another Point Using
Euclidean Distance. Along with this, each Point has a Weight which will play a part in finding the Best and
most Optimal Solution to our TSP Problem.
 */
public class Point {
    private final int x;
    private final int y;
    private int weight;

    public Point() {
        this.x = 0;
        this.y = 0;
        System.err.println("No points entered! Please enter a point int (x, y) format or check your input.");
        System.exit(1);
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int[] getPoint() {
        return new int[]{this.x, this.y};
    }

    //Euclidean Distance Method to Find Distance Between Two Points using Geometry.
    public static int euclideanDistance(Point from, Point to) {
        int differenceX = (int) Math.pow(from.x - to.x, 2);
        int differenceY = (int) Math.pow(from.y - to.y, 2);
        return (int) Math.sqrt(differenceX + differenceY);
    }

    //Weight Setters and Getters.
    public int getWeight() {
        return this.weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "(" + this.x + "," + this.y + "), weight:" + this.weight;
    }
}
