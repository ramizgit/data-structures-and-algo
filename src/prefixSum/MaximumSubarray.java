package prefixSum;

public class MaximumSubarray {

    //https://leetcode.com/problems/maximum-subarray/

    //hint : KADANE'S ALGORITHM
    public static void main(String[] args)
    {
        System.out.println(maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4})); //6
        System.out.println(maxSubArrayWithIdx(new int[]{-2,1,-3,4,-1,2,1,-5,4})); //6

        System.out.println(maxSubArray(new int[]{-2,-3,-4})); //-2
        System.out.println(maxSubArrayWithIdx(new int[]{-2,-3,-4})); //-2

        System.out.println(maxSubArray(new int[]{-2,-1,-4})); //-2
        System.out.println(maxSubArrayWithIdx(new int[]{-2,-1,-4})); //-2

    }

    private static int maxSubArray(int[] nums)
    {
        int sum = 0;
        int max = Integer.MIN_VALUE;

        for(int i=0; i< nums.length; i++){
            sum += nums[i];

            max = Math.max(max, sum);

            //reset if negative
            if(sum < 0){
                sum = 0;
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
