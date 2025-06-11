package dp;

public class UniquePathsII {
    //https://leetcode.com/problems/unique-paths-ii/
    public static void main(String[] args)
    {
        System.out.println(uniquePaths(new int[][]{{0,0,0}, {0,1,0}, {0,0,0}})); //2
        System.out.println(uniquePaths(new int[][]{{0,1}, {0,0}})); //1
    }

    private static int uniquePaths(int[][] obstacleGrid)
    {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];

        //populate first column
        for(int i=0; i<m; i++){
            if(obstacleGrid[i][0] == 1){
                dp[i][0] = 0;
            }else {
                dp[i][0] = 1;
            }

        }

        //populate first row
        for(int j=0; j<n; j++){
            if(obstacleGrid[0][j] == 1){
                dp[0][j] = 0;
            }else {
                dp[0][j] = 1;
            }
        }

        //run dp
        for(int i=1; i<m; i++){
            for(int j=1; j<n; j++){
                if(obstacleGrid[i][j] == 1){
                    dp[i][j] = 0;
                }else {
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
        }

        return dp[m-1][n-1];
    }
}

