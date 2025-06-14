package dp;

public class MaxProductSubarray {
    //https://leetcode.com/problems/maximum-product-subarray/description/

    public static void main(String[] args)
    {
        System.out.println(maxProduct(new int[]{1,2,3})); //6
        System.out.println(maxProduct(new int[]{1,-2,3})); //3
        System.out.println(maxProduct(new int[]{-1,-2,3})); //6
        System.out.println(maxProduct(new int[]{2,3,-2,4})); //6
        System.out.println(maxProduct(new int[]{-2,0,-1})); //0
        System.out.println(maxProduct(new int[]{-2,0,1})); //1
    }

    private static int maxProduct(int[] nums)
    {
        int prevMin = nums[0];
        int prevMax = nums[0];
        int result = nums[0];

        for(int i=1; i<nums.length; i++){
            int currMax = max(nums[i], nums[i] * prevMax, nums[i] * prevMin);
            int currMin = min(nums[i], nums[i] * prevMax, nums[i] * prevMin);

            result = Math.max(result, currMax);

            prevMax = currMax;
            prevMin = currMin;
        }

        return result;
    }

    private static int max(int a, int b, int c)
    {
        return Math.max(Math.max(a, b), c);
    }

    private static int min(int a, int b, int c)
    {
        return Math.min(Math.min(a, b), c);
    }
}
