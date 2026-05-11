package dp;

public class LongestLineOfConsecutiveOneInMatrix {

    // https://leetcode.com/problems/longest-line-of-consecutive-one-in-matrix/description/

    public int longestLine(int[][] mat)
    {
        int m = mat.length;
        int n = mat[0].length;

        // 0 -> horizontal
        // 1 -> vertical
        // 2 -> diagonal
        // 3 -> anti-diagonal
        int[][][] dp = new int[m+1][n+1][4];

        int maxLength = 0;

        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                if(mat[i-1][j-1] == 1)
                {
                    // horizontal
                    dp[i][j][0] = 1 + dp[i][j - 1][0];

                    // vertical
                    dp[i][j][1] = 1 + dp[i - 1][j][1];

                    // diagonal
                    dp[i][j][2] = 1 + dp[i - 1][j - 1][2];

                    // anti-diagonal
                    dp[i][j][3] = 1 + dp[i - 1][j + 1][3];

                    maxLength = Math.max(maxLength,
                            Math.max(
                                    Math.max(dp[i][j][0], dp[i][j][1]),
                                    Math.max(dp[i][j][2], dp[i][j][3])
                            )
                    );
                }
            }
        }

        return maxLength;
    }
}
