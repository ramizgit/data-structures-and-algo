package array;

public class RotateArray {
    //https://leetcode.com/problems/rotate-array/description/

    public static void main(String[] args)
    {
        rotate(new int[]{1,2,3,4,5,6,7}, 3);
    }

    private static void rotate(int[] nums, int k)
    {
        //step1 : rotate whole array
        reverse(nums, 0, nums.length-1);

        //step2 : rotate left array
        reverse(nums, 0, k-1);

        //step3 : rotate right array
        reverse(nums, k, nums.length-1);

        System.out.println(nums);
    }

    private static void reverse(int[] nums, int left, int right)
    {
        while (left <= right){
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;

            left++;
            right--;
        }
    }
}
