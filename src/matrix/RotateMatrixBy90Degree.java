package matrix;

public class RotateMatrixBy90Degree {
    public static void main(String[] args)
    {
        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        rotate(matrix);

        int[][] matrix2 = {{5,1,9,11},{2,4,8,10},{13,3,6,7},{15,14,12,16}};
        rotate(matrix2);
    }

    private static void rotate(int[][] matrix)
    {
        System.out.println("print input matrix");
        print(matrix);

        int m = matrix.length;
        int n = matrix[0].length;

        //do matrix transpose
        for(int i=0; i<m; i++)
        {
            for(int j=i+1; j<n; j++)
            {
                //swap cells
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        System.out.println("print after transpose");
        print(matrix);

        //swap columns to finally rotate by 90 degree
        for(int i=0; i<m; i++)
        {
            for(int j=0; j<n/2; j++)
            {
                //swap cells
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n-j-1];
                matrix[i][n-j-1] = temp;
            }
        }

        System.out.println("print final");
        print(matrix);
    }

    private static void print(int[][] matrix)
    {
        int m = matrix.length;
        int n = matrix[0].length;

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("======================");

    }
}
