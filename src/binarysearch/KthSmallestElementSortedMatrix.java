package heap;

import java.util.*;

public class KthSmallestElementSortedMatrix {

    //https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/description/

    //BINARY SEARCH APPROACH
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

    //HEAP BASED APPROACH
    //Time complexity : O(nlogn + klogn)
    //Space complexity : O(n)
    public int kthSmallestViaHeap(int[][] matrix, int k)
    {
        int n = matrix.length;

        PriorityQueue<Elements> minheap = new PriorityQueue<>( (a, b) -> a.val - b.val ); //O(n) space

        //O(nlogn) total for this for loop
        for(int i=0; i<n; i++){
            minheap.offer(new Elements(i, 0, matrix[i][0])); //each insert is O(logn)
        }

        int result = 0;

        //O(klogn) for this while loop
        while(k>0){
            Elements curr = minheap.poll(); //each poll is O(logn)
            result = curr.val;
            if(curr.col + 1 < n){
                minheap.offer(new Elements(curr.row, curr.col + 1, matrix[curr.row][curr.col + 1])); //each insert is O(logn)
            }
            k--;
        }

        return result;
    }

    class Elements{
        int row;
        int col;
        int val;

        public Elements(int row, int col, int val) {
            this.row = row;
            this.col = col;
            this.val = val;
        }
    }
}
