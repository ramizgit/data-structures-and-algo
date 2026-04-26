package heap;

public class KthSmallestElementSortedMatrix {

    //https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/description/

    //binary search approach
    //Time complexity : O(nlog(max-min))
    //Space complexity : O(1)
    public int kthSmallest(int[][] matrix, int k)
    {
        int n = matrix.length;

        int low = matrix[0][0];
        int high = matrix[n-1][n-1];
        int answer = 0;

        while(low <= high){
            int mid = low + (high - low)/2;

            if(hasKElementsLessOrEqualToTarget(matrix, mid, k, n)){
                answer = mid; //possible answer
                high = mid - 1;
            }else{
                low = mid + 1;
            }
        }

        return answer;
    }

    //this is O(n) time complexity
    public boolean hasKElementsLessOrEqualToTarget(int[][] matrix, int target, int k, int n)
    {
        int row = 0;
        int col = n-1;
        int count = 0;

        while(row < n && col >= 0){
            if(matrix[row][col] <= target){
                count += (col + 1);
                row++; //go down
            }else{
                col--; //go left
            }
        }

        return count >= k;
    }
}
