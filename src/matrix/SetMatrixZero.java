package matrix;

public class SetMatrixZeroes {

    //https://leetcode.com/problems/set-matrix-zeroes/
    /*
    Given an m x n integer matrix matrix, if an element is 0, set its entire row and column to 0's.
    A straightforward solution using O(mn) space is probably a bad idea.
    A simple improvement uses O(m + n) space, but still not the best solution.
    Could you devise a constant space solution?
     */
    
    public void setZeroes(int[][] matrix)
    {
        int m = matrix.length;
        int n = matrix[0].length;
        
        boolean firstRowZero = false;
        boolean firstColZero = false;
        
        //check if first row has any zero
        for(int j=0; j<n; j++){
            if(matrix[0][j] == 0){
                firstRowZero = true;
                break;
            }
        }

        //check if first col has any zero
        for(int i=0; i<m; i++){
            if(matrix[i][0] == 0){
                firstColZero = true;
                break;
            }
        }
        
        //iterate matrix and use frist row/col to store if any zero
        for(int i=1; i<m; i++){
            for(int j=1; j<n; j++){
                if(matrix[i][j] == 0){
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }
        
        //modify the matrix
        for(int i=1; i<m; i++){
            for(int j=1; j<n; j++){
                if(matrix[i][0] == 0 || matrix[0][j] == 0){
                    matrix[i][j] = 0;
                }
            }
        }
        
        //make first entire row zero if it had zero initially
        if(firstRowZero){
            for(int j=0; j<n; j++){
                matrix[0][j] = 0;
            }
        }

        //make first entire col zero if it had zero initially
        if(firstColZero){
            for(int i=0; i<m; i++){
                matrix[i][0] = 0;
            }
        }
    }
}
