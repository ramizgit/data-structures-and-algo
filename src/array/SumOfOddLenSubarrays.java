package arrays;

public class SumOfOddLenSubarrays {
    //https://leetcode.com/problems/sum-of-all-odd-length-subarrays/description/

    /*
    Given an array of positive integers arr, return the sum of all possible odd-length subarrays of arr.
     */

    public int sumOddLengthSubarrays(int[] arr)
    {
        int n = arr.length;
        int sum = 0;

        for(int i=0; i<n; i++){
            int start = i + 1;
            int end = n - i;

            int totalSubarrayCount = start * end;

            int oddSubarrayCount = (totalSubarrayCount + 1) / 2;

            sum += arr[i] * oddSubarrayCount;
        }

        return sum;
    }
}
