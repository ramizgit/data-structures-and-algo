package dp;

public class TriangleMinPathSum {
    //https://leetcode.com/problems/triangle/description/

    public static void main(String[] args)
    {
        //triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
        System.out.println(minPathSum(new int[][]{ {2}, {3,4}, {6,5,7}, {4,1,8,3} })); //11
        System.out.println(minPathSum(new int[][]{ {-10}})); //-10
    }

    private static int minPathSum(int[][] triangle)
    {
        int m = triangle.length;
        int n = triangle[m-1].length;

        int[] dp = new int[n];
        for(int i=0; i<n; i++){
            dp[i] = triangle[m-1][i];
        }

        for(int i=m-2; i>=0; i--){
            int[] curr = triangle[i];
            int[] prev = triangle[i+1];
            for(int j=0; j<curr.length; j++){
                dp[j] = curr[j] + Math.min(dp[j], dp[j+1]);
            }
        }

        return dp[0];
    }
}
