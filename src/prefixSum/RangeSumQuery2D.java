package prefixSum;

public class RangeSumQuery2D {
}

class NumMatrix {

    private int[][] prefix;

    //Time : O(mn), Space : O(mn)
    public NumMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        //initialize prefix matrix
        this.prefix = new int[m+1][n+1];

        //populate prefix matrix
        for(int i=1; i<=m; i++){
            for(int j=1; j<=n; j++){
                this.prefix[i][j] = matrix[i-1][j-1] +
                                    this.prefix[i][j-1] + //add left corner
                                    this.prefix[i-1][j] - //add top corner
                                    this.prefix[i-1][j-1]; //remove overlapping diagonal
            }
        }

    }

    public int sumRegion(int row1, int col1, int row2, int col2) {

        row1++; col1++; row2++; col2++; //increment by 1 as prefix 2D is 1 based index

        return this.prefix[row2][col2] -
                this.prefix[row2][col1-1] - //substract left corner
                this.prefix[row1-1][col2] + //subtract top corner
                this.prefix[row1-1][col1-1];//add diagonal as its removed twice above
    }
}
