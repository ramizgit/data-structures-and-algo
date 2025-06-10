package intervals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MergeIntervals {
    //https://leetcode.com/problems/merge-intervals/description/

    public static void main(String[] args)
    {
        merge(new int[][]{{2,6},{1,3},{8,10},{15,18}});
        merge(new int[][]{{1,4},{4,5}});
    }

    private static int[][] merge(int[][] intervals)
    {
        List<int[]> input = new ArrayList<>();
        for(int[] interval : intervals){
            input.add(interval);
        }

        Collections.sort(input, (a,b)->a[0] - b[0]);

        List<int[]> result = new ArrayList<>();
        result.add(input.get(0));

        for(int i=1; i<input.size(); i++){
            int[] currentInterval = input.get(i);
            int[] lastInterval = result.get(result.size()-1);

            if(currentInterval[0] <= lastInterval[1]){
                //there is overlap
                currentInterval[0] = Math.min(currentInterval[0], lastInterval[0]);
                currentInterval[1] = Math.max(currentInterval[1], lastInterval[1]);

                result.remove(result.size()-1);
                result.add(currentInterval);
            }else {
                result.add(currentInterval);
            }
        }

        return result.toArray(new int[result.size()][2]);
    }
}

