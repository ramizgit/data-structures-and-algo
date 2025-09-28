package slidingwindow;

public class MaximumAverageSubarrayI {
    public static void main(String[] args)
    {
        System.out.println(maximumAverageSubarray(new int[]{1,12,-5,-6,50,3}, 4));
        System.out.println(maximumAverageSubarray(new int[]{3}, 1));
    }

    private static double maximumAverageSubarray(int[] nums, int k)
    {
        int sum = 0;
        int left = 0;
        int right = 0;
        double max = Integer.MIN_VALUE;

        while(right < nums.length) {
            sum += nums[right];

            if(right - left >= k-1){
                max = Math.max(max, (double)sum/k);
                sum -= nums[left++];
            }
            right++;
        }
        return max;
    }
}
