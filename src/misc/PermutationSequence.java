package consistenthashing.misc;

import java.util.ArrayList;
import java.util.List;

public class PermutationSequence {

    //https://leetcode.com/problems/permutation-sequence/description/

    //Given n and k, return the kth permutation sequence.

    public String getPermutation(int n, int k) {

        List<Integer> digits = new ArrayList<>();

        int[] factorial = new int[n + 1]; //factorial[i] = i!
        factorial[0] = 1;

        for (int i = 1; i <= n; i++) {
            digits.add(i);
            factorial[i] = factorial[i - 1] * i;
        }

        k--; // convert to 0-based indexing

        StringBuilder answer = new StringBuilder();

        for (int remaining = n; remaining >= 1; remaining--) { // digits still available to place

            int blockSize = factorial[remaining - 1]; // how many permutations start with the same digit?
            int index = k / blockSize;

            answer.append(digits.get(index));

            k %= blockSize; // position within selected block
            digits.remove(index);
        }

        return answer.toString();
    }
}
