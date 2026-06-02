package neetcode150.arraysAndHashing;

import java.util.HashSet;
import java.util.Set;

public class LongestConsecutiveSequence {

    private static int longestConsecutive(int[] nums)
    {
        //input validation
        if(nums == null || nums.length == 0){
            return 0;
        }

        //add numbers to set for O(1) lookup
        Set<Integer> set = new HashSet<>();
        for(int num : nums){
            set.add(num);
        }

        int max = 0;

        for(int num : nums){
            if(!set.contains(num-1)){
                //starting element found, track max length
                int curr = num;
                int len = 0;
                while(set.contains(curr)){
                    len++;
                    curr++;
                }

                max = Math.max(max, len);
            }
        }

        return max;
    }
}
