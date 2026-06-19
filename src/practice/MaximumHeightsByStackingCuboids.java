package consistenthashing.practice;

import java.util.*;

public class MaximumHeightsByStackingCuboids {

    public int maxHeight(int[][] cuboids)
    {
        //sort cuboids in asc order dimension so that height becomes max
        for(int[] cuboid : cuboids){
            Arrays.sort(cuboid); //width <= length <= height
        }

        //rearragne to sort all cuboids lexicographically
        //This guarantees that when processing cuboid i, every possible predecessor has already been processed.
        Arrays.sort(cuboids, (a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0]; //width
            if (a[1] != b[1]) return a[1] - b[1]; //length
            return a[2] - b[2]; //height
        });

        //now run dp
        int n = cuboids.length;

        int[] dp = new int[n]; //dp[i] = max height by stacking till ith cuboid

        //base case, heigh of each cuboid
        for(int i=0; i<n; i++){
            dp[i] = cuboids[i][2];
        }

        int maxHeight = 0;

        //now LIS
        for(int i=0; i<n; i++){
            int[] curr = cuboids[i];
            for(int j=0; j<i; j++){
                int[] prev = cuboids[j];

                if(prev[0] <= curr[0] //width
                        && prev[1] <= curr[1] //length
                        && prev[2] <= curr[2] //height
                ){
                    dp[i] = Math.max(dp[i], curr[2] + dp[j]);
                }
            }

            maxHeight = Math.max(maxHeight, dp[i]);
        }

        return maxHeight;
    }
}
