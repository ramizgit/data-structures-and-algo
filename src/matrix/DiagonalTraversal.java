package meta;

public class DiagonalTraversal {
    public static void main(String[] args)
    {
        int[][] mat = { {1,2,3}, {4,5,6}, {7,8,9} };

        int[] output = diagonalOrder(mat);

        System.out.println(output);
    }

    private static int[] diagonalOrder(int[][] mat)
    {
        //edge case
        if(mat ==null || mat.length == 0 || mat[0].length == 0){
            return new int[0];
        }

        int m = mat.length;
        int n = mat[0].length;
        int row = 0;
        int col = 0;
        int[] arr = new int[m*n];
        int i = 0;
        boolean up = true;

        while (row < m && col < n){
            if(up){
                //digonal going up
                while (row > 0 && col < n-1){
                    arr[i++] = mat[row][col];
                    row--;
                    col++;
                }
                arr[i++] = mat[row][col];
                if(col == n-1){
                    row++;
                }else{
                    col++;
                }
            }else{
                //diagonal going down
                while(col > 0 && row < m-1){
                    arr[i++] = mat[row][col];
                    row++;
                    col--;
                }
                arr[i++] = mat[row][col];
                if(row == m-1){
                    col++;
                }else{
                    row++;
                }
            }

            up = !up;
        }

        return arr;
    }
}
