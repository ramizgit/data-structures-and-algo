package greedy;

public class JumpGame {

    //https://leetcode.com/problems/jump-game/description/

    /*
    Backward greedy: Start with the goal (last index). Find the leftmost position that can reach the goal, then make that position the new goal.
     */

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
