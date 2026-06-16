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

        int digitsAvailable = n;

        while (digitsAvailable >= 1) { // digits still available to place

            int blockSize = factorial[digitsAvailable - 1]; // how many permutations start with the same digit?

            int index = k / blockSize; //Chooses which digit's block contains the desired permutation.

            answer.append(digits.get(index)); //collect answer

            k %= blockSize; //Moves to the position within that chosen block.

            digits.remove(index); //Removes the chosen digit so it can't be reused.
            digitsAvailable--; //one less digit available
        }

        return answer.toString();
    }
}
