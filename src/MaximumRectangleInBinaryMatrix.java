public class MaximumRectangleInBinaryMatrix {

    public static void main(String[] args)
    {
        int[][] matrix = { {1, 1, 1, 0},
                            {1, 1, 1, 0},
                            {1, 0, 1, 0} };

        System.out.println("Max rectangle is : "+getMaxRectangle(matrix));

    }

    public static int getMaxRectangle(int[][] matrix)
    {
        int m = matrix.length;
        int n = matrix[0].length;
        int[] tmparr = new int[n];
        int maxRectangle = 0;

        //populate first row of the matrix into tmp array
        for(int i=0; i<m; i++)
        {
            for(int j=0; j<n; j++)
            {
                if(matrix[i][j] == 0){
                    tmparr[j] = 0;
                }else{
                    tmparr[j] += matrix[i][j];
                }
            }
            int area = LargestRectangleInHistogram.getLargestRectangle(tmparr);
            maxRectangle = Math.max(maxRectangle, area);
        }

        return maxRectangle;
    }
}
