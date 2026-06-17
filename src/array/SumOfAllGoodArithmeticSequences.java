package consistenthashing.array;

public class SumOfAllGoodArithmeticSequences {

    public long sumOfGoodArithmeticSubarrays(int[] nums)
    {
        int n = nums.length;

        int left = 0;
        int right = 0;

        long sum = 0;

        for(int num : nums){
            sum += num;
        }

        while(right < n){

            while(right < n-1 && nums[right+1] - nums[right] == 1){
                right++;
            }

            //find contribution from left to right
            //sum += getAllSubArrSumInTheRange(nums, left, right);
            if(left < right){
                sum += getContributionForRun(nums, left, right);
            }

            left = right;
            while(right < n-1 && nums[right+1] - nums[right] == -1){
                right++;
            }

            //find contribution from left to right
            //sum += getAllSubArrSumInTheRange(nums, left, right);
            if(left < right){
                sum += getContributionForRun(nums, left, right);
            }

            left = right;
            right++;
        }

        return sum;
    }

    // contribution of all good subarrays of length >= 2 inside this monotonic ±1 run
    private long getContributionForRun(int[] nums, int left, int right)
    {
        long sum = 0;

        for(int i=left; i<=right; i++){
            int leftChoice = i - left + 1; //left boundary starts from "left", not 0, hence "-left"
            int rightChoice = right - i + 1; //right boundary is "right", not nums.length

            long total = (long) leftChoice * rightChoice - 1;

            sum += total * nums[i];
        }

        return sum;
    }
}
