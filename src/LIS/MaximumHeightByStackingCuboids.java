package longestIncreasingSubseqVariants;

import java.util.*;


/*
“Sorting each cuboid removes rotation complexity by fixing a canonical orientation. Sorting all cuboids ensures a valid order for LIS-style DP,
where we build stacks from smaller to larger cuboids.”
 */

public class MaximumHeightByStackingCuboids {

    //todo : practice

    public int maxHeight(int[][] cuboids) {
        // Step 1: sort each cuboid for normalization
        /*
        If a cuboid can be rotated to fit somewhere, then its sorted version will also fit in that same position.
        Always use the smallest sides as base, largest as height. If stacking is possible in any rotation, it will also be possible in this normalized form.
         */
        for (int[] c : cuboids) {
            Arrays.sort(c);
        }

        // Step 2: sort all cuboids
        //this ensures when we process a box, all possible boxes that can go above it are already processed, primarily for DP concept
        Arrays.sort(cuboids, (a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0]; //width
            if (a[1] != b[1]) return a[1] - b[1]; //length
            return a[2] - b[2]; //height
        });

        int n = cuboids.length;

        // Step 3: LIS
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = cuboids[i][2]; // initialize height, base case
        }

        int max = 0;

        for (int i = 0; i < n; i++) {
            int[] curr = cuboids[i];
            for (int j = 0; j < i; j++) {
                int[] prev = cuboids[j];

                if (prev[0] <= curr[0] && //width
                        prev[1] <= curr[1] && //length
                        prev[2] <= curr[2]) { //height

                    dp[i] = Math.max(dp[i], dp[j] + curr[2]);
                }
            }

            max = Math.max(max, dp[i]);
        }

        return max;
    }
}
