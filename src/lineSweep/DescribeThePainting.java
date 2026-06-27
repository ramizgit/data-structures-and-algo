package consistenthashing.lineSweep;

import java.util.*;

public class DescribeThePainting {

    //https://leetcode.com/problems/describe-the-painting/description/

    public List<List<Long>> splitPainting(int[][] segments)
    {
        /*
        Approach:-
        its offline, treemap is ruled out
        now its diff. array vs event list with sorting
        coordinate range is "1 <= starti < endi <= 105" which is small, hence go with diff. array approach
         */

        //input validation
        if(segments == null || segments.length == 0){
            return Collections.emptyList();
        }

        //difference array
        long[] diff = new long[100001];

        //populate diff array as per input segments
        for(int[] segment : segments){
            int start = segment[0];
            int end = segment[1];
            long color = segment[2];

            diff[start] += color;
            diff[end] -= color;
        }

        //prefix sum and collect result
        List<List<Long>> result = new ArrayList<>();

        long currSum = diff[0];
        long prevSum = diff[0];
        int prevIdx = 0;

        for(int i=1; i<diff.length; i++){

            currSum += diff[i];

            if(currSum != prevSum){

                if (prevSum != 0) {
                    List<Long> list = new ArrayList<>();
                    list.add((long) prevIdx);
                    list.add((long) i);
                    list.add(prevSum);
                    result.add(list);

                    prevIdx = i;
                }

            }

            prevSum = currSum;
        }

        return result;
    }
}
