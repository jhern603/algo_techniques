package Assignment3;

/**
 @author Jose Hernandez, PID 5712864
 @author Ziad Malik, PID 6174850
 Object class that represents a point on a 2D plane that holds a weight distance between the current point, and the next point
 */
public class Point {
    private final int x;
    private final int y;
    private int weight;

    /**
     * Default constructor: if no parameters, throw an error and exit the program
     */
    public Point() {
        this.x = 0;
        this.y = 0;
        System.err.println("No points entered! Please enter a point int (x, y) format or check your input.");
        System.exit(1);
    }

    /**
     * Constructor that accepts an (x, y) coordinate
     * @param x coordinate
     * @param y coordinate
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Getter method for a coordinate pair
     * @return array of [x, y] coordinate
     */
    public int[] getPoint() {
        return new int[]{this.x, this.y};
    }

    /**
     * Find the geometric difference between two points using the Euclidean Distance formula
     * @param from inital Point Object
     * @param to final Point Object
     * @return Distance between From and To points
     */
    public static int euclideanDistance(Point from, Point to) {
        int differenceX = (int) Math.pow(from.x - to.x, 2);
        int differenceY = (int) Math.pow(from.y - to.y, 2);
        return (int) Math.sqrt(differenceX + differenceY);
    }


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
