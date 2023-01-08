package matrix;

public class LargestSquareMatrix {
    public static void main(String[] args)
    {
        int[][] m1 = new int[][]{ {1, 0, 1, 1, 1}, {1, 0, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 0, 0, 1, 0}};
        System.out.println(getLargestSquareMatrix(m1));//3

        int[][] m2 = new int[][]{ {1, 0, 1, 0, 0}, {1, 0, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 0, 0, 1, 0}};
        System.out.println(getLargestSquareMatrix(m2));//2

        int[][] m3 = new int[][]{ {0, 1}, {1, 0} };
        System.out.println(getLargestSquareMatrix(m3));//1
    }

    public static int getLargestSquareMatrix(int[][] matrix)
    {
        int m = matrix.length;
        int n = matrix[0].length;

        int[][] dp = new int[m][n];
        int laretSquare = 0;

        //populate first column of dp
        for(int i=0; i<m; i++)
        {
            dp[i][0] = matrix[i][0];

            if(dp[i][0] > laretSquare){
                laretSquare = dp[i][0];
            }
        }

        //populate first row of dp
        for(int j=0; j<n; j++)
        {
            dp[0][j] = matrix[0][j];

            if(dp[0][j] > laretSquare){
                laretSquare = dp[0][j];
            }
        }

        //populate rest of the dp matrix using previously calculated values
        for(int i=1; i<m; i++)
        {
            for(int j=1; j<n ;j++)
            {
                if(matrix[i][j] == 0){
                    dp[i][j] = 0;
                }else if(matrix[i][j] == 1){
                    dp[i][j] = Math.min(Math.min(dp[i][j-1], dp[i-1][j]), dp[i-1][j-1]) + 1;
                    if(dp[i][j] > laretSquare){
                        laretSquare = dp[i][j];
                    }
                }
            }
        }
        return laretSquare;
    }
}
