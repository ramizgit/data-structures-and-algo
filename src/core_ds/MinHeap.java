package core_ds;

public class MinHeap {

    private int[] heap;
    private int size;

    public MinHeap(int capacity)
    {
        this.heap = new int[capacity];
        size = 0;
    }

    //add
    public void add(int item)
    {
        this.heap[size] = item;
        this.size++;

        //upheapify
    }

    //remove
    public int remove()
    {
        int val = this.heap[0];
        this.heap[0] = this.heap[size-1];
        this.size--;

        //downheapify

        return val;
    }

    //peek
    public int peek()
    {
        return this.heap[0];
    }

    //upheapify
    private void upHeapify()
    {
        int curr = size-1;

        while(curr > 0 && heap[curr] < heap[parent(curr)]){
            //swap
            swap(heap[curr], heap[parent(curr)]);
            curr = parent(curr);
        }
    }

    //downheapify
    private void downHeapify()
    {
        int curr = 0;

        while (true) {
            int left = left(curr);
            int right = right(curr);
            int smallest = curr;

            if (left < size && heap[left] < heap[smallest]) {
                smallest = left;
            }

            if (right < size && heap[right] < heap[smallest]) {
                smallest = right;
            }

            if (smallest == curr) {
                break; // heap property satisfied
            }

            swap(curr, smallest);
            curr = smallest;
        }
    }

    // ---------- Helper methods ----------
    private int parent(int i) { return (i - 1) / 2; }
    private int left(int i) { return 2 * i + 1; }
    private int right(int i) { return 2 * i + 2; }
    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
}
