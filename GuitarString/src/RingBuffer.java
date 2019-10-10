public class RingBuffer {
    double[] array;
    int first;
    int last;
    int size;
    public RingBuffer(int capacity){
        this.array = new double[capacity];
        this.first = 0;
        this.last=0;
        size =0;
    }

    public int size() {
        return size;
    }
    public boolean isEmpty(){
        return size==0;
    }
    public boolean isFull() {
        return size == this.array.length;
    }
    public void enqueue(double x) throws Exception {
        if (size==array.length) throw new Exception("queue is full");
        if (last==array.length) {
            last = 0;
            array[last] = x;
        }
        else
        {
            array[last++] = x;
        }
        size++;
    }
    public double dequeue() throws Exception {
        if (size==0) throw new Exception("queue is empty");
        if (first==array.length) {
            first =0;
            size--;
            return array[first];
        }
        else
        {
             size--;
             return array[first++];
        }

    }
    public double peek() throws Exception {
        if (size==0) throw new Exception("queue is empty");
        return array[first];

    }
    public void clear()
    {
        first = 0;
        last = 0;
        size=0;
    }


}
