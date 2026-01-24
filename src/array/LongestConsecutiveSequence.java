package neetcode150.arraysAndHashing;

import java.util.HashSet;
import java.util.Set;

public class LongestConsecutiveSequence {

    public static void main(String[] args)
    {
        System.out.println(longestConsecutive(new int[]{2,20,4,10,3,3,4,5})); //4

    }

    private static int longestConsecutive(int[] nums)
    {
        Set<Integer> set = new HashSet<>();
        for(int num : nums){
            set.add(num);
        }

        int max = 0;

        for(int num : nums){
            if(!set.contains(num-1)){
                int currNum = num;
                int currMax = 1;
                while(set.contains(currNum+1)){
                    currNum++;
                    currMax++;
                }

                max = Math.max(max, currMax);
            }
        }

        return max;
    }
}
