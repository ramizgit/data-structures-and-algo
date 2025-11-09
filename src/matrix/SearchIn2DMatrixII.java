package matrix;

public class SearchIn2DMatrixII {
    //https://leetcode.com/problems/search-a-2d-matrix-ii/description/

    public static void main(String[] args)
    {

    }

    private static boolean searchMatrix(int[][] matrix, int target)
    {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return false;
        }

        int m = matrix.length;
        int n = matrix[0].length;

        int r = 0;
        int c = n-1;

        while(r < m && c >= 0){
            if(matrix[r][c] == target){
                return true;
            }else if(target < matrix[r][c]){
                c--;
            }else{
                r++;
            }
        }

        return false;
    }
}
