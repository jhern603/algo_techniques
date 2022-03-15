package Prog16_01;
/**
 * Queue class. Implements a queue of nodes.
 *
 * @author A. Hernandez. COP4534 Algorithm Techniques
 */
public class Queue {

    public Queue() {
        size = 100;
        list = new Node[size];

        front = 0;
        back = size - 1;

        count = 0;
    }

    public Queue(int s) {
        size = s;
        list = new Node[size];

        front = 0;
        back = size - 1;

        count = 0;
    }

    public void enqueue(Node c) {
        back = (back + 1) % size;
        list[back] = c;
        count++;
    }

    public void dequeue() {
        front = (front + 1) % size;
        count--;
    }

    public Node getFront() {
        return list[front];
    }

    public boolean isEmpty() {
        return count == 0;
    }

    private Node[] list;
    private int size;
    private int count;
    private int front, back;
}
