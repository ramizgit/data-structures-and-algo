package binarysearch;

public class FirstAndLastPosInSortedArray {

    //https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/description/

    public static void main(String[] args)
    {

        int[] range = searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8); //[3,4]
        System.out.println(range[0]+ " : "+range[1]);

        int[] range2 = searchRange(new int[]{5, 7, 7, 8, 8, 10}, 6); //[-1,-1]
        System.out.println(range2[0]+ " : "+range2[1]);
    }

    private static int[] searchRange(int[] nums, int target)
    {
        int left = binarySearch(nums, target, true);
        int right = binarySearch(nums, target, false);

        return new int[]{left, right};
    }

    private static int binarySearch(int[] nums, int target, boolean leftBias)
    {
        int low = 0;
        int high = nums.length-1;
        int idx = -1;

        while (low <= high){
            int mid = low + (high - low)/2;

            if(nums[mid] == target){
                idx = mid;

                if(leftBias){
                    high = mid - 1;
                }else {
                    low = mid + 1;
                }

            } else if (nums[mid] < target) {
                low = mid + 1;
            }else {
                high = mid - 1;
            }
        }

        return idx;
    }
}
