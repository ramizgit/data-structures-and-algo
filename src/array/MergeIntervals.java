package array;

import java.util.Map;
import java.util.TreeMap;

public class MergeIntervals {
    public static void main(String[] args)
    {
        merge(new int[][]{ {1,3},{2,6},{8,10},{15,18} }); //{1=6, 8=10, 15=18}
        merge(new int[][]{ {1,3},{2,6},{5,10},{15,18} }); //{1=10, 15=18}
        merge(new int[][]{ {1,3},{2,6},{5,10},{9,18} }); //{1=18}
        merge(new int[][]{ {1,4}, {4,5}}); //{1=5}
    }

    private static int[][] merge(int[][] intervals)
    {
        TreeMap<Integer, Integer> map = new TreeMap<>();

        for(int[] interval : intervals){
            int newkey;
            int newvalue;

            Map.Entry<Integer, Integer> floorEntry = map.floorEntry(interval[0]);
            if(floorEntry != null && interval[0] <= floorEntry.getValue()){
                newkey = floorEntry.getKey();
                newvalue=Math.max(interval[1], floorEntry.getValue());
                map.put(newkey, newvalue);
            }else {
                map.put(interval[0], interval[1]);
            }
        }
        System.out.println(map);
        return null;
    }
}
