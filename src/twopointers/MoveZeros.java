package twopointer;

public class MoveZeros {
    //https://leetcode.com/problems/move-zeroes/description/

    public static void main(String[] args)
    {
        moveZeroes(new int[]{0,0,0,3,12});
    }

    private static void moveZeroes(int[] nums)
    {
        int left = 0;
        int right = 0;

        while (right < nums.length){
            if(nums[right] != 0){
                //swap with left
                int temp = nums[right];
                nums[right] = nums[left];
                nums[left] = temp;

                left++;
            }

            right++;
        }

        System.out.println(nums);
    }
}
