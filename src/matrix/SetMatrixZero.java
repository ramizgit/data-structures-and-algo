package matrix;

public class SetMatrixZero {
    //https://leetcode.com/problems/set-matrix-zeroes/description/

    public static void main(String[] args)
    {
        int[][] matrix = { {1,1,1}, {1,0,1}, {1,1,1} };
        setZeroes(matrix);

    }

    private static void setZeroes(int[][] matrix)
    {
        int m = matrix.length;
        int n = matrix[0].length;

        int[] row = new int[m];
        int[] col = new int[n];

        //iterate and populate arrays
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(matrix[i][j] == 0){
                    row[i] = -1;
                    col[j] = -1;
                }
            }
        }

        //change matrix rows
        for(int i=0; i<row.length; i++){
            if(row[i] == -1){
                for(int j=0; j<n; j++){
                    matrix[i][j] = 0;
                }
            }
        }

        //change matrix cols
        for(int i=0; i<col.length; i++){
            if(col[i] == -1){
                for(int j=0; j<m; j++){
                    matrix[j][i] = 0;
                }
            }
        }

        System.out.println(matrix);
    }
}
