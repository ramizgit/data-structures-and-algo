package intervals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MeetingRoom {
    //https://leetcode.com/problems/meeting-rooms/description/
    public static void main(String[] args)
    {
        System.out.println(canAttendMeetings(new int[][]{{0, 30}, {5, 10}, {15, 20}})); //false
        System.out.println(canAttendMeetings(new int[][]{{5, 8}, {9, 15}})); //true
    }

    private static boolean canAttendMeetings(int[][] intervals)
    {
        List<int[]> input = new ArrayList<>();
        for(int[] interval : intervals){
            input.add(interval);
        }
        Collections.sort(input, (a, b) -> a[0] - b[0]);

        for(int i=1; i<input.size(); i++){
            int[] prevInterval = input.get(i-1);
            int[] currInterval = input.get(i);
            if(currInterval[0] < prevInterval[1]){
                //overlap
                return false;
            }
        }
        return true;
    }
}
