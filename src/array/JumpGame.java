package array;

public class JumpGame {

    //https://leetcode.com/problems/jump-game/description/

    public boolean canJump(int[] nums)
    {
        int goal = nums.length-1;

        for(int i=nums.length-2; i>=0; i--){

            if(i + nums[i] >= goal){
                goal = i; //if we can reach the current goal from i, then i becomes the new goal.
            }
        }

        return goal == 0;
    }
}
