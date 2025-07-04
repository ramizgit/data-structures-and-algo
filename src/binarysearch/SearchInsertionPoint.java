package binarysearch;

public class SearchInsertionPoint {

    public static void main(String[] args)
    {
        System.out.println(searchInsert(new int[]{1,3,5,6}, 2));
    }

    private static int searchInsert(int[] nums, int target)
    {
        int left = 0;
        int right = nums.length-1;

        while (left <= right){
            int mid = (left + right) / 2;

            if(nums[mid] == target){
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }else {

                right = mid - 1;
            }
        }

        return left; //SUBTLE BUT VERY IMPORTANT POINT (*****)
    }
}

