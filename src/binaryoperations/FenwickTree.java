package binaryoperations;

/*
Trick : BIT[i] stores the sum of the lowbit(i) elements ending at index i, where lowbit(i) = i & -i.
that is -
BIT[1] → 1 element (01)
BIT[2] → 2 elements (10)
BIT[3] → 1 element (11)
BIT[4] → 4 elements (100)
BIT[5] → 1 element (101)
BIT[6] → 2 elements (110)
BIT[7] → 1 element (111)
BIT[8] → 8 elements (1000)
 */

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

        //get sum of all values from index 1 through index.

        int sum = 0;

        while (index > 0) {
            sum += bit[index];

            // Move to the previous Fenwick block
            index -= index & -index; //backward
        }

        return sum;
    }

    // Returns sum of elements from index left to right, inclusive
    //rangeQuery(left, right) = query(right) - query(left - 1);
    public int rangeQuery(int left, int right){
        return query(right) - query(left - 1);
    }

}
