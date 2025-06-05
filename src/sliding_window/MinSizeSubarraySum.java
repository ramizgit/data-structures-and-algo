package slidingwindow;

public class MinSizeSubarraySum {

    //https://leetcode.com/problems/minimum-size-subarray-sum/description/

    /*
    Given an array of positive integers nums and a positive integer target, return the minimal length of a subarray whose sum is greater than or equal to target.
    If there is no such subarray, return 0 instead.
     */

    public static void main(String[] args)
    {
        System.out.println(minSubArrayLen(7, new int[]{2,3,1,2,4,3})); //2
        System.out.println(minSubArrayLen(4, new int[]{1,4,4})); //1
        System.out.println(minSubArrayLen(11, new int[]{1,1,1,1,1,1,1,1})); //0
    }

    private static int minSubArrayLen(int target, int[] nums)
    {
        int left = 0;
        int right = 0;
        int sum = 0;
        int result = Integer.MAX_VALUE;

        while (right < nums.length){
            sum += nums[right];

            while (sum > target){
                sum -= nums[left];
                left++;
            }

            if(sum == target){
                result = Math.min(result, right - left + 1);
            }

            right++;
        }

        return result == Integer.MAX_VALUE ? 0 : result;
    }
}

