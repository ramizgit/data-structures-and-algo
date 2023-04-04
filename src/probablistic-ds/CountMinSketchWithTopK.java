import java.util.PriorityQueue;
import java.util.Queue;

public class CountMinSketchWithTopK {

    private static final int DEPTH = 3;
    private static final int WIDTH = 10;
    private static final int TOP_K = 3;
    int[][] sketch;

    Queue<Pair> minheap;

    public CountMinSketchWithTopK() {
        this.sketch = new int[DEPTH][WIDTH];
        this.minheap = new PriorityQueue<>();

        //initialize
        for(int i=0; i<DEPTH; i++){
            for(int j=0; j<WIDTH; j++){
                sketch[i][j] = 0;
            }
        }
    }

    public void populate(String input)
    {
        int hash1 = HashFunction.hash1(input);
        int hash2 = HashFunction.hash2(input);
        int hash3 = HashFunction.hash3(input);

        this.sketch[0][hash1]++;
        this.sketch[1][hash2]++;
        this.sketch[2][hash3]++;

        int frequency =  Math.min(this.sketch[0][hash1], Math.min(this.sketch[1][hash2], this.sketch[2][hash3]));

        minheap.remove(new Pair(input, frequency));
        minheap.add(new Pair(input, frequency));

        if(minheap.size() > TOP_K){
            minheap.remove();
        }
    }

    public void topK()
    {
        //print top k
        System.out.println(this.minheap);
    }

    public static void main(String[] args){
        CountMinSketchWithTopK countMinSketch = new CountMinSketchWithTopK();
        countMinSketch.populate("java");
        countMinSketch.populate("golang");
        countMinSketch.populate("python");
        countMinSketch.populate("php");
        countMinSketch.populate("java");
        countMinSketch.populate("java");
        countMinSketch.populate("nodejs");
        countMinSketch.populate("golang");
        countMinSketch.populate("java");
        countMinSketch.populate("golang");
        countMinSketch.populate("java");
        countMinSketch.populate("java");
        countMinSketch.populate("golang");
        countMinSketch.populate("python");
        countMinSketch.populate("java");

        countMinSketch.topK();
    }
}

class Pair implements Comparable<Pair>{
    String key;
    int value;

    public Pair(String key, int value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public int getValue() {
        return value;
    }

    @Override
    public int compareTo(Pair o) {
        return Integer.compare(this.value, o.getValue());
    }

    @Override
    public int hashCode() {
        return key.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        Pair p = (Pair) obj;
        return this.key.equals(p.key);
    }
}
