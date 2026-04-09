package matrix;

import java.util.*;

/*
Given a 2D matrix, count how many submatrices have sum = k
 */
public class SubmatrixSumK {

    public int numSubmatrixSumTarget(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;

        int count = 0;

        // Fix top row
        for (int r1 = 0; r1 < m; r1++) {
            int[] colSum = new int[n];

            // Expand bottom row
            for (int r2 = r1; r2 < m; r2++) {

                // Build compressed column sum
                for (int c = 0; c < n; c++) {
                    colSum[c] += matrix[r2][c];
                }

                // Count subarrays with sum = k
                count += countSubarrays(colSum, k);
            }
        }

        return count;
    }

    // Standard prefix sum + hashmap
    private int countSubarrays(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        int prefixSum = 0;
        int count = 0;

        for (int num : nums) {
            prefixSum += num;

            if (map.containsKey(prefixSum - k)) {
                count += map.get(prefixSum - k);
            }

            map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
        }

        return count;
    }

    // For quick testing
    public static void main(String[] args) {
        SubmatrixSumK obj = new SubmatrixSumK();

        int[][] matrix = {
                {1, 2, 1},
                {3, 1, -1},
                {2, 4, 1}
        };

        int k = 5;

        System.out.println(obj.numSubmatrixSumTarget(matrix, k)); // expected output: 4
    }
}
