package google;

public class MoveZeros {

    //https://leetcode.com/problems/move-zeroes/

    public void moveZeroes(int[] nums) {

        int left = 0;
        int right = 0;

        while(right < nums.length){

            if(nums[right] != 0){
                if(left != right){
                    //swap
                    int tmp = nums[right];
                    nums[right] = nums[left];
                    nums[left] = tmp;
                }
                left++;
            }

            right++;
        }
    }
}
