package Prog18_05;

public class Queue
{

    public Queue()
    {
        size = 100;
        list = new int[size];

        front = 0;
        back = size - 1;

        count = 0;
    }

    public Queue(int s)
    {
        size = s;
        list = new int[size];

        front = 0;
        back = size - 1;

        count = 0;
    }

    public void enqueue(int c)
    {
        back = (back + 1) % size;
        list[back] = c;
        count++;
    }

    public void dequeue()
    {
        front = (front + 1) % size;
        count--;
    }

    public int getFront()
    {
        return list[front];
    }

    public boolean isEmpty()
    {
        return count == 0;
    }

    private int[] list;
    private int size;
    private int count;
    private int front, back;
}
