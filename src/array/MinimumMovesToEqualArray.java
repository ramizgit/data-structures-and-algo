package arrays;

import java.util.*;

public class MinimumMovesToEqualArray {

    //https://leetcode.com/problems/minimum-moves-to-equal-array-elements-ii/description/

    //Time : O(nlogn)
    public int minMoves2(int[] nums)
    {
        Arrays.sort(nums); //O(nlogn)

        int middle = nums.length / 2;
        int median = nums[middle];

        int moves = 0;

        for(int num : nums){
            moves += Math.abs(median - num);
        }

        return moves;
    }
}
