package slidingwindow;

public class MaximumAverageSubarray {
    //https://leetcode.com/problems/maximum-average-subarray-i/description/
    public static void main(String[] args)
    {
        System.out.println(findMaxAverage(new int[]{1,12,-5,-6,50,3}, 4));
    }

    private static double findMaxAverage(int[] nums, int k)
    {
        int sum = 0;
        for(int i=0; i<k; i++){
            sum += nums[i];
        }

        double maxAvg = (double) sum / k;

        for(int i=k; i<nums.length; i++){
            sum += nums[i] - nums[i-k];
            maxAvg = Math.max(maxAvg, (double)sum/k);
        }

        return maxAvg;
    }
}
