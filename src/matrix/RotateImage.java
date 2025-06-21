package matrix;

public class RotateImage {
    //https://leetcode.com/problems/rotate-image/description/

    public static void main(String[] args)
    {
        int[][] matrix = { {1,2,3},{4,5,6},{7,8,9} };
        rotate(matrix);

        int[][] matrix2 = { {5,1,9,11},{2,4,8,10},{13,3,6,7}, {15,14,12,16} };
        rotate(matrix2);
    }

    private static void rotate(int[][] matrix)
    {
        int m = matrix.length;
        int n = matrix[0].length;

        //transpose
        for(int i=0; i<m; i++){
            for(int j=i; j<n; j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        //reflection
        for(int i=0; i<m; i++){
            for(int j=0; j<n/2; j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n-j-1];
                matrix[i][n-j-1] = temp;
            }
        }

        System.out.println(matrix);

    }
}
