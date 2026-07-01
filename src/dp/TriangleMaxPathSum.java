package dp;

import java.util.*;

public class TriangleMaxPathSum {

    //just calculates max sum collected
    //Time : O(n^2)
    //Space : O(n)
    public int maxPathSum(int[][] triangle)
    {
        //input validation
        if(triangle == null || triangle.length == 0){
            return -1;
        }

        int n = triangle.length;

        int[] dp = triangle[n-1].clone(); // DP starts as the last row, base case: max path sum from the last row is the value itself

        for(int i=n-2; i>=0; i--){ //compute best path sum for each row from bottom to top

            int[] curr = triangle[i];

            for(int j=0; j<curr.length; j++){
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

        // direction[i][j] stores which child gives the optimal path:
        // 0 -> down      (i+1, j)
        // 1 -> down-right (i+1, j+1)
        int[][] direction = new int[n][n];

        for(int i=n-2; i>=0; i--){

            int[] curr = triangle[i];

            for(int j=0; j<curr.length; j++){
                if (dp[j] >= dp[j + 1]) {
                    dp[j] = curr[j] + dp[j];
                    direction[i][j] = 0; // down
                } else {
                    dp[j] = curr[j] + dp[j + 1];
                    direction[i][j] = 1; // down-right
                }
            }
        }

        // print max sum
        System.out.println("Max Sum: " + dp[0]);

        // reconstruct path
        List<Integer> path = new ArrayList<>();
        int j = 0; //Start at (0,0)

        for (int i = 0; i < n-1; i++) {
            path.add(triangle[i][j]);
            j = j + direction[i][j];
        }

        // add the last row element
        path.add(triangle[n - 1][j]);

        System.out.println("Path: " + path);

        return dp[0];
    }
}
