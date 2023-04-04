public class CountMinSketch {

    private static final int DEPTH = 3;
    private static final int WIDTH = 10;
    int[][] sketch;

    public CountMinSketch() {
        this.sketch = new int[DEPTH][WIDTH];

        //initialize
        for(int i=0; i<DEPTH; i++){
            for(int j=0; j<WIDTH; j++){
                sketch[i][j] = 0;
            }
        }
    }

    public int count(int num)
    {
        int hash1 = HashFunction.hash1(num);
        int hash2 = HashFunction.hash2(num);
        int hash3 = HashFunction.hash3(num);

        this.sketch[0][hash1]++;
        this.sketch[1][hash2]++;
        this.sketch[2][hash3]++;

        return Math.min(this.sketch[0][hash1], Math.min(this.sketch[1][hash2], this.sketch[2][hash3]));
    }

    public int count(String input)
    {
        int hash1 = HashFunction.hash1(input);
        int hash2 = HashFunction.hash2(input);
        int hash3 = HashFunction.hash3(input);

        this.sketch[0][hash1]++;
        this.sketch[1][hash2]++;
        this.sketch[2][hash3]++;

        return Math.min(this.sketch[0][hash1], Math.min(this.sketch[1][hash2], this.sketch[2][hash3]));
    }

    public static void main(String[] args){
        CountMinSketch countMinSketch = new CountMinSketch();
        System.out.println(countMinSketch.count(10));
        System.out.println(countMinSketch.count(10));
        System.out.println(countMinSketch.count(10));
        System.out.println(countMinSketch.count(11));

        System.out.println("==================");

        System.out.println(countMinSketch.count("java"));
        System.out.println(countMinSketch.count("golang"));
        System.out.println(countMinSketch.count("python"));
        System.out.println(countMinSketch.count("golang"));
        System.out.println(countMinSketch.count("java"));
        System.out.println(countMinSketch.count("java"));
    }
}
