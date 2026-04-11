package matrix;

public class MaximumSumRectangle {

    /*
    Given a 2D matrix mat[][] of integers, find the maximum sum among all possible submatrices.
     */

    //Time complexity : O(m^2 * n)
    //Space complexity : O(n)
    public int maxSumRectangle(int[][] matrix)
    {
        int m = matrix.length;
        int n = matrix[0].length;
        int max = Integer.MIN_VALUE;

        for(int i=0; i<m; i++){ //O(m) time
            int[] colSum = new int[n]; //O(n) space
            for(int r=i; r<m; r++){ //O(m) time
                for(int j=0; j<n; j++){ //O(n) time
                    colSum[j] += matrix[r][j];
                }

                //run kadens algo
                max = Math.max(max, getMaxSubArraySum(colSum)); //O(n)
            }
        }

        return max;
    }

    // Kadane's algorithm (handles all-negative case)
    public int getMaxSubArraySum(int[] arr)
    {
        int curr = arr[0];
        int max = arr[0];

        for(int i = 1; i < arr.length; i++){
            curr = Math.max(arr[i], curr + arr[i]);
            max = Math.max(max, curr);
        }

        return max;
    }
}
