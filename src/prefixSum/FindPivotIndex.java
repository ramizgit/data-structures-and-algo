package prefixSum;

public class FindPivotIndex {

    //https://leetcode.com/problems/find-pivot-index/description/

    public static void main(String[] args)
    {
        System.out.println(pivotIndex(new int[]{1,7,3,6,5,6})); //3
        System.out.println(pivotIndex(new int[]{1,2,3})); //-1
    }

    private static int pivotIndex(int[] nums)
    {
        int totalSum = 0;

        for(int num : nums){
            totalSum += num;
        }

        int leftSum = 0;
        int rightSum = 0;

        for(int i=0; i<nums.length; i++){
            rightSum = totalSum - nums[i] - leftSum;

            if( leftSum == rightSum ){
                return i;
            }

            leftSum += nums[i];
        }

        return -1;
    }
}

