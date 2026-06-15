package consistenthashing.twopointers;

import java.util.ArrayList;
import java.util.List;

public class PositionsOfLargeGroups {

    //https://leetcode.com/problems/positions-of-large-groups/description/

    public static List<List<Integer>> largeGroupPositions(String s)
    {
        int left = 0;
        int right = 1;
        List<List<Integer>> result = new ArrayList<>();

        while(right < s.length()){
            if(s.charAt(left) != s.charAt(right)){
                if(right - left >= 3){
                    result.add(List.of(left, right-1));
                }
                left = right;
            }

            right++;
        }

        // handle last group
        if (right - left >= 3) {
            result.add(List.of(left, right - 1));
        }

        return result;
    }
}
