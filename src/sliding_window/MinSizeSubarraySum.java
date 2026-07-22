package slidingwindow;

public class MinSizeSubarraySum {

    //https://leetcode.com/problems/minimum-size-subarray-sum/description/

    /*
    Given an array of positive integers nums and a positive integer target, return the minimal length of a subarray whose sum is greater than or equal to target.
    If there is no such subarray, return 0 instead.
     */

    public int minSubArrayLen(int target, int[] nums)
    {
        int windowStart = 0;
        int windowSum = 0;
        int minLength = Integer.MAX_VALUE;

        for (int windowEnd = 0; windowEnd < nums.length; windowEnd++) {

            windowSum += nums[windowEnd];

            while (windowSum >= target){

                minLength = Math.min(minLength, windowEnd - windowStart + 1); //track min length

                //shrink the window
                windowSum -= nums[windowStart];
                windowStart++;
            }
        }

        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }
}
