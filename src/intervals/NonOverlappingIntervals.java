package intervals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NonOverlappingIntervals {
    //https://leetcode.com/problems/non-overlapping-intervals/description/

    public static void main(String[] args)
    {
        System.out.println(eraseOverlapIntervals(new int[][]{{1,2},{2,3},{3,4},{1,3}})); //1
        System.out.println(eraseOverlapIntervals(new int[][]{{1,2},{1,2},{1,2}})); //2
        System.out.println(eraseOverlapIntervals(new int[][]{{1,2},{2,3}})); //0
    }

    private static int eraseOverlapIntervals(int[][] intervals)
    {
        List<int[]> input = new ArrayList<>();
        for(int[] interval : intervals){
            input.add(interval);
        }
        Collections.sort(input, (a,b) -> a[0] - b[0]);

        int result = 0;
        int prevEnd = input.get(0)[1];

        for(int i=1; i<input.size(); i++){
            int[] current = input.get(i);

            if(current[0] < prevEnd){
                //overlap
                result++;

                prevEnd = Math.min(prevEnd, current[1]);
            }else {
                //no overlap, move on
                prevEnd = current[1];
            }
        }

        return result;
    }
}
