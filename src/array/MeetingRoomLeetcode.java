package linesweep;

import java.util.Map;
import java.util.TreeMap;

public class MeetingRoomLeetcode {
    public static void main(String[] args)
    {
        System.out.println(canAttendMeetings(new int[][]{{5,8},{9,15}})); //true
        System.out.println(canAttendMeetings(new int[][]{{0,30},{5,10},{15,20}})); //false
        System.out.println(canAttendMeetings(new int[][]{{1,10},{5,15},{15,20}})); //false
        System.out.println(canAttendMeetings(new int[][]{{1,10},{15,20}, {12,16}})); //false
    }

    private static boolean canAttendMeetings(int[][] intervals) {

        TreeMap<Integer, Integer> map = new TreeMap<>();

        for (int[] interval : intervals) {
            //check floor
            Map.Entry<Integer, Integer> floor = map.floorEntry(interval[0]);
            if (floor != null && interval[0] < floor.getValue()) {
                return false;
            }

            //check ceiling
            Map.Entry<Integer, Integer> ceiling = map.ceilingEntry(interval[0]);
            if (ceiling != null && ceiling.getKey() < interval[1]) {
                return false;
            }

            //else put in the map
            map.put(interval[0], interval[1]);
        }
        return true;
    }
}
