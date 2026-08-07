package dp;

public class MaxProductSubarray {

    //https://leetcode.com/problems/maximum-product-subarray/description/

    public int maxProduct(int[] nums)
    {
        int minEndingHere = nums[0]; //minimum product ending at previous index
        int maxEndingHere = nums[0]; //maximum product ending at previous index
        int result = nums[0];

        for(int i=1; i<nums.length; i++){

            int currMax = max(nums[i], nums[i] * maxEndingHere, nums[i] * minEndingHere);
            int currMin = min(nums[i], nums[i] * maxEndingHere, nums[i] * minEndingHere);

            result = Math.max(result, currMax);

            maxEndingHere = currMax;
            minEndingHere = currMin;
        }

        return result;
    }

    private int max(int a, int b, int c)
    {
        return Math.max(Math.max(a, b), c);
    }

    private int min(int a, int b, int c)
    {
        return Math.min(Math.min(a, b), c);
    }
}
