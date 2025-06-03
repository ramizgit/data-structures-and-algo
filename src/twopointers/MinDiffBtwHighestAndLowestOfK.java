package twopointer;

import java.util.Arrays;

public class MinDiffBtwHighestAndLowestOfK {

    public static void main(String[] args)
    {
        //https://leetcode.com/problems/minimum-difference-between-highest-and-lowest-of-k-scores/description/

        System.out.println(minimumDifference(new int[]{90}, 1)); //0
        System.out.println(minimumDifference(new int[]{9,4,1,7}, 2)); //2
        System.out.println(minimumDifference(new int[]{9,4,1,7}, 3)); //5 ---- 1,4,7,9
        System.out.println(minimumDifference(new int[]{9,4,3,7}, 3)); //4 ---- 3,4,7,9
    }

    private static int minimumDifference(int[] nums, int k)
    {
        Arrays.sort(nums);

        int left = 0;
        int right = k-1;
        int min = Integer.MAX_VALUE;

        while (right < nums.length){
            min = Math.min(min, nums[right] - nums[left]);
            left++;
            right++;
        }

        return min;
    }
}
