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

        map.putIfAbsent(x, new HashMap<>());
        map.get(x).put(y, map.get(x).getOrDefault(y, 0) + 1);
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

    // NEW METHOD - JUST RETURNS TRUE OR FALSE TO DETECT SQUARE
    public boolean completesSquare(int[] point) {
        int x = point[0];
        int y = point[1];

        if (!map.containsKey(x)) return false;

        Map<Integer, Integer> yMap = map.get(x);

        for (int y2 : yMap.keySet()) {
            if (y == y2) continue;

            int d = Math.abs(y - y2);

            // check right square
            if (getCount(x + d, y) > 0 &&
                    getCount(x + d, y2) > 0 &&
                    getCount(x, y2) > 0) {
                return true;
            }

            // check left square
            if (getCount(x - d, y) > 0 &&
                    getCount(x - d, y2) > 0 &&
                    getCount(x, y2) > 0) {
                return true;
            }
        }

        return false;
    }

    private int getCount(int x, int y) {
        if (!map.containsKey(x)) return 0;
        return map.get(x).getOrDefault(y, 0);
    }
}
