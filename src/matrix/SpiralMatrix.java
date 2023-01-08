package matrix;

public class SpiralMatrix {
    public static void main(String[] args) //todo:practice
    {
        int[][] matrix = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
        printSpiralMatrix(matrix);
    }

    public static void printSpiralMatrix(int[][] matrix)
    {
        int row = matrix.length;
        int col = matrix[0].length;
        int r = 0;
        int c = 0;

        while(r < row && c < col)
        {
            for(int i=c; i<col; i++)
            {
                System.out.println(matrix[r][i]);
            }
            r++;

            for(int i=r; i<row; i++)
            {
                System.out.println(matrix[i][col-1]);
            }
            col--;

            if(r < row)
            {
                for(int i=col-1; i>=c; i--)
                {
                    System.out.println(matrix[row-1][i]);
                }
                row--;
            }

            if(c < col)
            {
                for(int i=row-1; i>=r; i--)
                {
                    System.out.println(matrix[i][c]);
                }
                c++;
            }

        }
    }
}
