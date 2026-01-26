package core_ds;

public class CircularBuffer {

    private int[] buffer;
    private int head;
    private int tail;
    private int size;
    private int capacity;

    public CircularBuffer(int capacity)
    {
        this.buffer = new int[capacity];
        this.head = 0;
        this.tail = 0;
        this.size = 0;
        this.capacity = capacity;
    }

    //write
    public void write(int value)
    {
        if(isFull()){
            throw new IllegalStateException("Buffer is full");
        }

        this.buffer[tail] = value;
        this.tail = (this.tail + 1) % capacity;
        this.size++;
    }

    //read
    public int read()
    {
        if(isEmpty()){
            throw new IllegalStateException("Buffer is empty");
        }
        int value = this.buffer[head];
        this.head = (this.head + 1) % capacity;
        this.size--;

        return value;
    }

    //size
    public int size()
    {
        return this.size;
    }

    public boolean isFull()
    {
        return this.size == this.capacity;
    }

    public boolean isEmpty()
    {
        return this.size == 0;
    }
}
