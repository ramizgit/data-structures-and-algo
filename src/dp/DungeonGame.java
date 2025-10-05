package dp;

public class DungeonGame {
    //https://leetcode.com/problems/dungeon-game/description/
    public static void main(String[] args)
    {
        System.out.println(minHealth(new int[][]{ {-2,-3,3},{-5,-10,1},{10,30,-5} })); //7
    }

    private static int minHealth(int[][] dungeon)
    {
        int m = dungeon.length;
        int n = dungeon[0].length;

        int[][] dp = new int[m][n];

        //bottom up approach
        dp[m-1][n-1] = Math.max(1, 1 - dungeon[m-1][n-1]);

        //populate last col
        for(int i = m-2; i>=0; i--){
            dp[i][n-1] = Math.max(1, dp[i+1][n-1] - dungeon[i][n-1]);
        }

        //populate last row
        for(int i = n-2; i>=0; i--){
            dp[m-1][i] = Math.max(1, dp[m-1][i+1] - dungeon[m-1][i]);
        }

        //populate rest of dp
        for(int i=m-2; i>=0; i--){
            for(int j=n-2; j>=0; j--){
                int minHealthOnRight = Math.max(1, dp[i][j+1] - dungeon[i][j]);
                int minHealthOnDown = Math.max(1, dp[i+1][j] - dungeon[i][j]);
                dp[i][j] = Math.min(minHealthOnRight, minHealthOnDown);
            }
        }

        return dp[0][0];
    }
}
