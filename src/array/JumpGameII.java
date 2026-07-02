package array;

public class JumpGameII {

    //https://leetcode.com/problems/jump-game-ii/description/

    public int jump(int[] nums)
    {
        int jumps = 0;
        int boundary = 0;
        int farthest = 0;

        for (int i = 0; i < nums.length - 1; i++) { //why till n-2? we iterate over indices that we might jump from, not indices that we might jump to

            farthest = Math.max(farthest, i + nums[i]); //find the maximum (farthest) while scanning the current range

            // End of current jump range.
            if (i == boundary) {

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
