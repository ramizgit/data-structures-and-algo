package array;

import java.util.Map;
import java.util.TreeMap;

public class IntervalListIntersectionsLeetcode {
    public static void main(String[] args)
    {
        /*
        Input: firstList = [[0,2],[5,10],[13,23],[24,25]],
              secondList = [[1,5],[8,12],[15,24],[25,26]]
                   Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
         */
        
        int[][] firstList = {{0,2},{5,10},{13,23},{24,25}};
        int[][] secondList = {{1,5},{8,12},{15,24},{25,26}};

        intervalIntersection(firstList, secondList);
    }

    private static int[][] intervalIntersection(int[][] firstList, int[][] secondList)
    {
        TreeMap<Integer, Integer> firstTreemap = new TreeMap<>();
        TreeMap<Integer, Integer> output = new TreeMap<>();

        //populate first tree map
        for(int[] first : firstList){
            firstTreemap.put(first[0], first[1]);
        }

        //iterate second list and find intersection
        for(int[] second : secondList){
            int start = second[0];
            int end = second[1];
            
            Map.Entry<Integer, Integer> floorEntry = firstTreemap.floorEntry(start);
            if(floorEntry != null && start <= floorEntry.getValue()){
                //overlap found
                output.put(start, Math.min(end, floorEntry.getValue()));
            }

            Map.Entry<Integer, Integer> ceilingEntry = firstTreemap.ceilingEntry(start);
            if(ceilingEntry != null && ceilingEntry.getKey() <= end){
                //overlap found
                output.put(ceilingEntry.getKey(), Math.min(end, ceilingEntry.getValue()));
            }
        }
        
        System.out.println(output);

        return null;
    }
}
