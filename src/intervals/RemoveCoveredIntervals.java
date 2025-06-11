package intervals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RemoveCoveredIntervals {

    //https://leetcode.com/problems/remove-covered-intervals/description/
    public static void main(String[] args)
    {
        System.out.println(removeCoveredIntervals(new int[][]{{1,4}, {3,6}, {2,8}})); //2
        System.out.println(removeCoveredIntervals(new int[][]{{1,4}, {2,3}})); //1
        System.out.println(removeCoveredIntervals(new int[][]{{1,4}, {4,5}})); //2
        System.out.println(removeCoveredIntervals(new int[][]{{1,5}, {1,10}, {2,7}})); //1
    }

    private static int removeCoveredIntervals(int[][] intervals)
    {
        List<int[]> input = new ArrayList<>();
        for(int[] interval : intervals){
            input.add(interval);
        }
        Collections.sort(input, (a,b) -> a[0] - b[0]);

        int result = input.size();

        for(int i=1; i<input.size(); i++){
            int[] prev = input.get(i-1);
            int[] curr = input.get(i);

            //check if prev covers curr
            if(curr[0] >= prev[0] && curr[1] <= prev[1]){
                result--;
            }

            //check if curr covers prev
            if(prev[0] == curr[0] && prev[1] <= curr[1]){
                result--;
            }
        }

        return result;
    }
}
