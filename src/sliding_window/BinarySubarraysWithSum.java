package slidingWindow;

public class BinarySubarraysWithSum {

    //https://leetcode.com/problems/binary-subarrays-with-sum/description/
    /*
    Given a binary array nums and an integer goal, return the number of non-empty subarrays with a sum goal.
    A subarray is a contiguous part of the array.
     */
    
    public int numSubarraysWithSum(int[] nums, int goal)
    {
        return numSubarraysWithSumAtMostK(nums, goal) - numSubarraysWithSumAtMostK(nums, goal-1);
    }

    private int numSubarraysWithSumAtMostK(int[] nums, int k)
    {
        if (k < 0) {
            return 0;
        }

        int left = 0;
        int right = 0;
        int sum = 0;
        int count = 0;

        while(right < nums.length){
            sum += nums[right];

            //shrink if needed
            while(sum > k){
                sum -= nums[left];
                left++;
            }

            count += right - left + 1; //num of subarrays with at most k sum

            right++;
        }

        return count;
    }
}
