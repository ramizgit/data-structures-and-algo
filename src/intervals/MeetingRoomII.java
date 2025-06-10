package intervals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MeetingRoomII {
    //https://leetcode.com/problems/meeting-rooms-ii/description/
    public static void main(String[] args)
    {
        System.out.println(minDaysToSchedule(new int[][]{{0, 40}, {5, 10}, {15, 20}})); //2
        System.out.println(minDaysToSchedule(new int[][]{{5, 8}, {9, 15}})); //1
    }

    private static int minDaysToSchedule(int[][] intervals)
    {
        List<int[]> input = new ArrayList<>();
        for(int[] interval : intervals){
            input.add(interval);
        }
        Collections.sort(input, (a, b) -> a[0] - b[0]);

        int result = 1;

        for(int i=1; i<input.size(); i++){
            int[] prevInterval = input.get(i-1);
            int[] currInterval = input.get(i);
            if(currInterval[0] < prevInterval[1]){
                //overlap
                result++;
            }
        }
        return result;
    }
}
