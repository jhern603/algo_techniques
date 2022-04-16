package Assignment3;

public class Point {
    private final int x;
    private final int y;
    private int weight;

    public Point() {
        this.x = 0;
        this.y = 0;
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int[] getPoint() {
        return new int[]{this.x, this.y};
    }

    public int euclideanDistance(Point to) {
        int differenceX = (int) Math.pow(this.x - to.x, 2);
        int differenceY = (int) Math.pow(this.y - to.y, 2);
        return (int) Math.sqrt(differenceX + differenceY);
    }

    public static int euclideanDistance(Point from, Point to) {
        int differenceX = (int) Math.pow(from.x - to.x, 2);
        int differenceY = (int) Math.pow(from.y - to.y, 2);
        return (int) Math.sqrt(differenceX + differenceY);
    }

    @Override
    public String toString() {
        return "(" + this.x + "," + this.y + "), weight:" + this.weight;
    }

    public int getWeight() {
        return this.weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
