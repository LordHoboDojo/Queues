import java.util.*;
import java.io.*;

public class RingBuffer
{
    private int size = 0, front = 0;
    private double[] queue;

    public RingBuffer(int capacity)
    {
        queue = new double[capacity];
    }

    public int size()
    {
        return size;
    }

    public boolean isEmpty()
    {
        if(size == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean isFull()
    {
        if(size == queue.length)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void enqueue(double x)
    {
        if(this.isFull())
        {
            throw new IllegalStateException("Queue is full");
        }
        else
        {
            queue[(front+size) % queue.length] = x;
            size++;
        }
    }

    public double dequeue()
    {
        if(this.isEmpty())
        {
            throw new IllegalStateException("Queue is empty");
        }
        else
        {
            size--;
            int lastFront = front;
            front = (front+1) % queue.length;
            return queue[lastFront];
        }
    }

    public double peek()
    {
        if(this.isEmpty())
        {
            throw new IllegalStateException("Queue is empty");
        }
        else
        {
            return queue[front];
        }
    }
    public void clear()
    {
        size = 0; front = 0;
    }
}

