package slidingwindow;

public class MinSizeSubarraySum {

    //https://leetcode.com/problems/minimum-size-subarray-sum/description/

    /*
    Given an array of positive integers nums and a positive integer target, return the minimal length of a subarray whose sum is greater than or equal to target.
    If there is no such subarray, return 0 instead.
     */

    public int minSubArrayLen(int target, int[] nums)
    {
        int left = 0;
        int right = 0;
        int windowSum = 0;
        int minLength = Integer.MAX_VALUE;

        while (right < nums.length){

            windowSum += nums[right];

            while (windowSum >= target){
                minLength = Math.min(minLength, right - left + 1);

                windowSum -= nums[left]; //shrink window
                left++;
            }

            right++;
        }

        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }
}
