public class GuitarString {
    RingBuffer ringBuffer;
    int tics =0;
   public GuitarString (double frequency) throws Exception {
        ringBuffer = new RingBuffer((int) (44100/frequency));
        for (int i=0; i< (int) (44100/frequency);i++)
        {
            ringBuffer.enqueue(0);
        }
    }
    public GuitarString(double[] arr) throws Exception {
       ringBuffer = new RingBuffer(arr.length);
       for (Double d: arr)
       {
           ringBuffer.enqueue(d);
       }
    }
    public void pluck() throws Exception {
        ringBuffer.clear();
        while(!ringBuffer.isFull()){
            ringBuffer.enqueue(Math.random()-.5);
        }
    }
    public void tic() throws Exception {
        ringBuffer.enqueue((ringBuffer.dequeue()+ringBuffer.peek())/2.0);
        tics++;
    }
    public double sample() throws Exception {
       return ringBuffer.peek();
    }
    public int time()
    {
        return tics;
    }
}
