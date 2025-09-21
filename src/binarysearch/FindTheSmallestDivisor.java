package binarysearch;

public class FindTheSmallestDivisor {
    //https://leetcode.com/problems/find-the-smallest-divisor-given-a-threshold/description/

    public static void main(String[] args)
    {
        System.out.println(smallestDivisor(new int[]{1,2,5,9}, 6)); //5
        System.out.println(smallestDivisor(new int[]{44,22,33,11,1}, 5)); //44
    }

    private static int smallestDivisor(int[] nums, int k)
    {
        int max = nums[0];
        for(int num : nums){
            max = Math.max(max, num);
        }

        int left = 1;
        int right = max;
        int answer = Integer.MAX_VALUE;

        while(left <= right){
            int mid = left + (right - left)/2;

            if(checkThreshold(nums, k, mid)){
                answer = Math.min(answer, mid); //found possible answer
                right = mid - 1; //go left if we find even smaller divisor, as we need minimum to meet the criteria
            }else{
                left = mid + 1;
            }
        }

        return answer;
    }

    private static boolean checkThreshold(int[] nums, int k, int div)
    {
        int sum = 0;
        for(int num : nums){
            sum += Math.ceilDiv(num, div);
        }

        return sum <= k;
    }
}
