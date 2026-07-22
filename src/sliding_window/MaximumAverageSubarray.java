package sliding_window;

public class MaximumAverageSubarray {

    //https://leetcode.com/problems/maximum-average-subarray-i/description/

    private static double findMaxAverage(int[] nums, int k)
    {
        //build first window
        int sum = 0;
        for(int i=0; i<k; i++){
            sum += nums[i];
        }

        int maxSum = sum;

        int left = 0;
        int right = k;

        //slide remaining windows
        while (right < nums.length){

            sum += nums[right];
            sum -= nums[left];

            maxSum = Math.max(maxSum, sum);

            left++;
            right++;
        }

        return (double) maxSum / k;
    }
}
