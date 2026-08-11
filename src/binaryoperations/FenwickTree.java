package binaryoperations;

public class FenwickTree {

    private final int[] bit;

    // Fenwick Tree uses 1-based indexing
    public FenwickTree(int n) {
        bit = new int[n + 1];
    }

    // Add 'value' to the element at 'index'
    public void update(int index, int value) {

        while (index < bit.length) {
            bit[index] += value;

            // Move to the next Fenwick block that contains 'index'
            index += index & -index; //forward
        }
    }

    // Returns sum of elements from index 1 to 'index'
    public int query(int index) {

        int sum = 0;

        while (index > 0) {
            sum += bit[index];

            // Move to the previous Fenwick block
            index -= index & -index; //backward
        }

        return sum;
    }
}
