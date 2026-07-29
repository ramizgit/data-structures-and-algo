package array;

public class MaximumSubarray {

    //https://leetcode.com/problems/maximum-subarray/

    //KADANE'S ALGORITHM
    private static int maxSubArray(int[] nums)
    {
        int sum = 0;
        int max = Integer.MIN_VALUE;

        for (int num : nums) {

            sum += num;

            max = Math.max(max, sum);

            if(sum < 0){
                sum = 0; //reset if negative
            }
        }

        return max;
    }

    private static int maxSubArrayWithIdx(int[] nums)
    {
        int sum = 0;
        int max = Integer.MIN_VALUE;
        int start = 0;
        int end = 0;

        /*
        why tmpstart?
        When the running sum becomes negative:
        Throw away the current subarray.
        The next element becomes the candidate start.
        If the running sum later becomes the best ever:
        Promote tmpstart to the real start.
         */
        int tmpstart = 0;

        for(int i=0; i< nums.length; i++){

            sum += nums[i];

            if(sum > max){
                max = sum;
                start = tmpstart;
                end = i;
            }

            //reset if negative
            if(sum < 0){
                sum = 0;
                tmpstart = i+1;
            }
        }

        System.out.println("start = "+start+" end = "+end);

        return max;
    }
}
