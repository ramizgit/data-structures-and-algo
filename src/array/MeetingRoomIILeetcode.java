package linesweep;

import java.util.Map;
import java.util.TreeMap;

public class MeetingRoomIILeetcode {
    //Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
    //find the minimum number of conference rooms required.)
    public static void main(String[] args)
    {
        System.out.println(minMeetingRooms(new int[][]{{5,8},{9,15}})); //1
        System.out.println(minMeetingRooms(new int[][]{{0,30},{5,10},{15,20}})); //2
        System.out.println(minMeetingRooms(new int[][]{{1,10},{5,15},{15,20}})); //2
        System.out.println(minMeetingRooms(new int[][]{{1,10},{15,20}, {12,14}})); //1
        System.out.println(minMeetingRooms(new int[][]{{1,10},{15,20}, {12,16}})); //2
    }

    private static int minMeetingRooms(int[][] intervals) {

        TreeMap<Integer, Integer> map = new TreeMap<>();
        int count = 1;

        for (int[] interval : intervals) {
            //check floor
            Map.Entry<Integer, Integer> floor = map.floorEntry(interval[0]);
            if (floor != null && interval[0] < floor.getValue()) {
                count++;
            }

            //check ceiling
            Map.Entry<Integer, Integer> ceiling = map.ceilingEntry(interval[0]);
            if (ceiling != null && ceiling.getKey() < interval[1]) {
                count++;
            }

            //else put in the map
            map.put(interval[0], interval[1]);
        }
        return count;
    }
}
