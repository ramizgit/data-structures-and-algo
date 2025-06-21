package slidingwindow;

public class BinarySubarraysWithSum {
    //https://leetcode.com/problems/binary-subarrays-with-sum/description/

    public static void main(String[] args)
    {
        System.out.println(numSubarraysWithSum(new int[]{1,0,1,0,1}, 2)); //4
        System.out.println(numSubarraysWithSum(new int[]{0,0,0,0,0}, 0)); //15

    }

    private static int numSubarraysWithSum(int[] nums, int goal)
    {
        return slidingWindow(nums, goal) - slidingWindow(nums, goal-1);
    }

    private static int slidingWindow(int[] nums, int target)
    {
        int left = 0;
        int right = 0;
        int sum = 0;
        int count = 0;

        while (right < nums.length){
            sum += nums[right];

            while (left <= right && sum > target){
                sum -= nums[left];
                left++;
            }

            if(sum <= target){
                count += (right - left + 1);
            }

            right++;
        }

        return count;
    }
}
