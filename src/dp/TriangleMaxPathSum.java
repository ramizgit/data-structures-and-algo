package dp;

import java.util.*;

public class TriangleMaxPathSum {

    //just calculates max sum collected
    public int maxPathSum(int[][] triangle)
    {
        int n = triangle.length;

        int[] dp = triangle[n-1].clone(); //O(n) space

        for(int i=n-2; i>=0; i--){
            int[] curr = triangle[i];

            for(int j=0; j<curr.length; j++){ //O(n^2) time complexity
                dp[j] = curr[j] + Math.max(dp[j], dp[j+1]);
            }
        }

        return dp[0];
    }

    //also prints the path taken to collect max sum
    public int maxPathSumWithPath(int[][] triangle)
    {
        int n = triangle.length;

        int[] dp = triangle[n-1].clone();

        // to store choices
        int[][] choice = new int[n][n];

        for(int i=n-2; i>=0; i--){
            int[] curr = triangle[i];

            for(int j=0; j<curr.length; j++){
                if (dp[j] >= dp[j + 1]) {
                    dp[j] = curr[j] + dp[j];
                    choice[i][j] = 0; // down
                } else {
                    dp[j] = curr[j] + dp[j + 1];
                    choice[i][j] = 1; // down-right
                }
            }
        }

        // print max sum
        System.out.println("Max Sum: " + dp[0]);

        // reconstruct path
        List<Integer> path = new ArrayList<>();
        int j = 0;

        for (int i = 0; i < n; i++) {
            path.add(triangle[i][j]);

            if (i < n - 1) {
                j = j + choice[i][j];
            }
        }

        System.out.println("Path: " + path);

        return dp[0];
    }
}
