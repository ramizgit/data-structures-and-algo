package matrix;

public class MatrixDiagonalSum {
    //https://leetcode.com/problems/matrix-diagonal-sum/description/

    public static void main(String[] args)
    {
        int[][] mat = { {1,2,3}, {4,5,6}, {7,8,9} };
        System.out.println(diagonalSum(mat));
    }

    private static int diagonalSum(int[][] mat)
    {
        int m = mat.length;
        int n = mat[0].length;

        int sum = 0;

        for(int i=0; i<m; i++){
            //primary diagonal
            sum += mat[i][i];

            //secondary diagonal
            sum += mat[i][n - 1 - i];
        }

        //handle duplicate if odd lengh
        if(m % 2 != 0){
            sum -= mat[m/2][n/2];
        }

        return sum;
    }
}
