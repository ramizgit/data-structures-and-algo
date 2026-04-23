package longestIncreasingSubseqVariants;

import java.util.*;

public class MaximumHeightByStackingCuboids {

    //todo : practice

    public int maxHeight(int[][] cuboids) {
        // Step 1: sort each cuboid
        for (int[] c : cuboids) {
            Arrays.sort(c);
        }

        // Step 2: sort all cuboids
        Arrays.sort(cuboids, (a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0]; //width
            if (a[1] != b[1]) return a[1] - b[1]; //length
            return a[2] - b[2]; //height
        });

        int n = cuboids.length;

        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = cuboids[i][2]; // initialize height
        }

        int max = 0;

        // Step 3: LIS
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (cuboids[j][0] <= cuboids[i][0] && //width
                        cuboids[j][1] <= cuboids[i][1] && //length
                        cuboids[j][2] <= cuboids[i][2]) { //height

                    dp[i] = Math.max(dp[i], dp[j] + cuboids[i][2]);
                }
            }

            max = Math.max(max, dp[i]);
        }

        return max;
    }
}
