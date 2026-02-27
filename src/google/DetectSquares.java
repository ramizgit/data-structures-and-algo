package google;

import java.util.*;

//https://leetcode.com/problems/detect-squares/description/
public class DetectSquares {

    Map<Integer, Map<Integer, Integer>> map;

    public DetectSquares() {
        this.map = new HashMap<>();
    }

    public void add(int[] point) {
        int x = point[0];
        int y = point[1];

        Map<Integer, Integer> yMap = this.map.getOrDefault(x, new HashMap<>());
        yMap.put(y, yMap.getOrDefault(y, 0) + 1);
        this.map.put(x, yMap);
    }

    public int count(int[] point) {
        int x = point[0];
        int y = point[1];

        if(!this.map.containsKey(x)){
            return 0;
        }

        Map<Integer, Integer> yMap = this.map.get(x);
        int count = 0;

        for(int y2 : yMap.keySet()){
            if(y == y2){
                continue;
            }

            int d = Math.abs(y - y2);

            //check right, multiply due to dupes
            count += getCount(x+d, y) * getCount(x+d, y2) * getCount(x, y2);

            //check left, multiply due to dupes
            count += getCount(x-d, y) * getCount(x-d, y2) * getCount(x, y2);
        }

        return count;
    }

    private int getCount(int x, int y) {
        if (!map.containsKey(x)) return 0;
        return map.get(x).getOrDefault(y, 0);
    }
}
