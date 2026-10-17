package LIS;

import static LIS.LongestBitonicSubsequence.longestBitonicSubsequence;

public class MinimumRemovalsMountain {

    //https://leetcode.com/problems/minimum-number-of-removals-to-make-mountain-array/description/

    private static int minimumMountainRemovals(int[] nums)
    {
        return nums.length - longestBitonicSubsequence(nums);
    }
}
