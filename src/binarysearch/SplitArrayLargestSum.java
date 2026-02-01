package binarysearch;

public class SplitArrayLargestSum {
    public static void main(String[] args)
    {
        //nums = [7,2,5,10,8], k = 2
        System.out.println(splitArray(new int[]{7,2,5,10,8}, 2));
    }

    private static int splitArray(int[] nums, int k)
    {
        int low = 0;
        int high = 0;
        int answer = Integer.MAX_VALUE;

        for(int n : nums){
            low = Math.max(low, n);
            high += n;
        }

        while(low <= high){
            int mid = low + (high - low)/2;

            if(canSplit(nums, k, mid)){
                answer = mid; //possible answer
                high = mid - 1;
            }else{
               low = mid + 1;
            }
        }
        return answer;
    }

    //nums = [7,2,5,10,8], k = 2
    private static boolean canSplit(int[] nums, int k, int maxSum)
    {
        int sum = 0;
        int subarray = 1;

        for(int n : nums){
            sum += n;

            if(sum > maxSum){
                subarray++;
                //reset
                sum = n;
            }
        }

        return subarray <= k;
    }
}
