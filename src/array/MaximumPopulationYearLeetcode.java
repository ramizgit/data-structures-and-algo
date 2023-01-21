package linesweep;

import java.util.Map;
import java.util.TreeMap;

public class MaximumPopulationYearLeetcode {
    public static void main(String[] args)
    {
        maximumPopulation(new int[][]{{1993,1999},{2000,2010}});
        maximumPopulation(new int[][]{{1950,1961},{1960,1971},{1970,1981}});
    }

    private static int maximumPopulation(int[][] logs)
    {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int maxPopulation = 0;

        for(int[] log : logs){
            for(int i=log[0]; i<log[1]; i++){
                map.put(i, 1 + map.getOrDefault(i, 0));
                maxPopulation = Math.max(maxPopulation, map.get(i));
            }
        }

        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            if(entry.getValue() == maxPopulation){
                System.out.println("max population : "+maxPopulation);
                System.out.println("min year : "+entry.getKey());
                return entry.getKey();
            }
        }

        return -1;
    }
}
