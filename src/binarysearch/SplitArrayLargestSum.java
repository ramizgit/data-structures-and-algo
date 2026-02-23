package binarysearch;

public class SplitArrayLargestSum {
    public static void main(String[] args)
    {
        //nums = [7,2,5,10,8], k = 2
        System.out.println(splitArray(new int[]{7,2,5,10,8}, 2));
    }

    private static int splitArray(int[] nums, int k)
    {
        int max = 0;
        int total = 0;

        for(int n : nums){
            max = Math.max(max, n);
            total += n;
        }

        //binary search
        int low = max;
        int high = total;

        int answer = 0;

        while(low <= high){
            int mid = low + (high - low) / 2;

            if(canSplit(nums, k, mid)){
                answer = mid; //possible answer
                high = mid - 1; //try lower to minimize max sum
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
