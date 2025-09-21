package binarysearch;

public class SplitArrayLargestSum {
    public static void main(String[] args)
    {

        //nums = [7,2,5,10,8], k = 2
        System.out.println(splitArray(new int[]{7,2,5,10,8}, 2));

    }

    private static int splitArray(int[] nums, int k)
    {
        int left = 0;
        int right = 0;
        int answer = Integer.MAX_VALUE;

        for(int i=0; i<nums.length; i++){
            left = Math.max(left, nums[i]);
            right += nums[i];
        }

        while(left <= right){
            int mid = left + (right - left)/2;

            if(canSplit(nums, k, mid)){
                answer = Math.min(answer, mid);
                right = mid - 1;
            }else{
               left = mid + 1;
            }
        }
        return answer;
    }

    //nums = [7,2,5,10,8], k = 2
    private static boolean canSplit(int[] nums, int k, int maxSum)
    {
        int sum = 0;
        int count = 1;

        for(int num : nums){
            sum += num;

            if(sum > maxSum){
                count++;
                //reset
                sum = num;
            }
        }

        return count <= k;
    }
}
