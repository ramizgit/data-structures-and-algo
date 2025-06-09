package binarysearch;

public class SingleElementInASortedArray {

    //https://leetcode.com/problems/single-element-in-a-sorted-array/description/

    public static void main(String[] args)
    {
        System.out.println(singleNonDuplicate(new int[]{1,1,2,3,3,4,4,8,8}));//2
        System.out.println(singleNonDuplicate(new int[]{3,3,7,7,10,11,11}));//10
    }

    private static int singleNonDuplicate(int[] nums)
    {
        int low = 0;
        int high = nums.length-1;

        while (low <= high){
            int mid = low + (high - low)/2;

            if((mid-1 < 0 || nums[mid-1] != nums[mid]) && (mid+1 >= nums.length || nums[mid] != nums[mid+1])){
                return nums[mid];
            }else {
                int leftArrSize = nums[mid] == nums[mid-1] ? mid - 1 : mid;

                if(leftArrSize % 2 != 0){
                    high = mid - 1;
                }else {
                    low = mid + 1;
                }
            }
        }

        return -1;
    }
}
