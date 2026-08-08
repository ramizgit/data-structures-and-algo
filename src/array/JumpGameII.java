package array;

public class JumpGameII {

    //https://leetcode.com/problems/jump-game-ii/description/

    public int jump(int[] nums)
    {
        int jumps = 0;
        int boundary = 0; //first jump has to originate from index 0, hence this is starting boundary
        int farthest = 0;

        for (int i = 0; i < nums.length - 1; i++) { //why till n-2? we iterate over indices that we might jump from, not indices that we might jump to

            /*
            Hint:-
            Within the current reachable range, find the position that gives the farthest next reach.
            When the current boundary is exhausted, take one jump and move the boundary to that farthest position.
             */

            farthest = Math.max(farthest, i + nums[i]); //find the farthest position reachable from the current jump range

            if (i == boundary) {
                //current jump is exhausted, commit to the next jump

                //important : we would need this check if the problem does not guarantee that you can reach nums[n - 1].
                /*if (farthest == boundary) {
                    return -1; // cannot move forward
                }*/

                jumps++; //take one jump
                boundary = farthest; //new range ends at farthest
            }
        }

        return jumps;
    }
}
