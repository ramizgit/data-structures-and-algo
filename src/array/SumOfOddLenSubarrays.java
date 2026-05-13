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
            /*
            e.g. arr = [1,4,2,5,3]
            i = 2; //element 2

            how many choices do we have for the STARTING index of a subarray that includes i?
            possible start = 0,1,2
            hence start = i+1, because indices go from 0 to i.
             */
            int start = i + 1;


            /*
            how many choices do we have for the ENDING index of a subarray that includes i?
            possible ends 2,3,4
            hence end = n-i
             */
            int end = n - i;

            //every start can pair with every end, hence multiply
            int totalSubarrayCount = start * end;

            int oddSubarrayCount = (totalSubarrayCount + 1) / 2;

            sum += arr[i] * oddSubarrayCount;
        }

        return sum;
    }
}
