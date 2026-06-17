package arrays;

public class SumOfOddLenSubarrays {

    //https://leetcode.com/problems/sum-of-all-odd-length-subarrays/description/

    /*
    Given an array of positive integers arr, return the sum of all possible odd-length subarrays of arr.
     */

    public int sumOddLengthSubarrays(int[] arr)
    {
        /*
            Approach:
            Instead of generating all subarrays (O(n²)),
            compute "how many subarrays include arr[i]" and multiply contribution accordingly.

            For every arr[i]:
            - count how many subarrays include it
            - among them, determine how many are odd length
            - multiply contribution accordingly
         */

        int n = arr.length;
        int sum = 0;

        for(int i=0; i<n; i++){
            /*
            e.g. arr = [1,4,2,5,3]
            i = 2; //element 2

            how many choices do we have for the STARTING index of a subarray that includes i?
            possible start indices = 0,1,2
            hence start = i+1, because indices go from 0 to i.
             */
            int leftChoices = i + 1; //basically this is num of elements between index i and 0

            /*
            how many choices do we have for the ENDING index of a subarray that includes i?
            possible ends 2,3,4
            hence end = n-i
             */
            int rightChoices = n - i; //basically this is num of elements between end index and i

            //every leftChoice can pair with every rightChoice, hence multiply
            int totalSubarrayCount = leftChoices * rightChoices;

            int oddSubarrayCount = (totalSubarrayCount + 1) / 2; //odd-length subarrays get one extra, hence +1

            sum += arr[i] * oddSubarrayCount;
        }

        return sum;
    }
}
