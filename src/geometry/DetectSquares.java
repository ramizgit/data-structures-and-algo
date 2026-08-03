package geometry;

import java.util.HashMap;
import java.util.Map;

public class DetectSquares {

    //https://leetcode.com/problems/detect-squares/description/

    /*
    Duplicate points are allowed and should be treated as different points. hence Map of map being used to keep track of freq
     */
    Map<Integer, Map<Integer, Integer>> map; //{x -> {y -> count}}

    public DetectSquares() {
        this.map = new HashMap<>();
    }

    public void add(int[] point) {
        int x = point[0];
        int y = point[1];

        Map<Integer, Integer> yMap = map.computeIfAbsent(x, k -> new HashMap<>());
        yMap.put(y, yMap.getOrDefault(y, 0) + 1);
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
                continue; //skip the query point itself; a square side must have positive length
            }

            int d = Math.abs(y - y2);

            //check right, multiply due to dupes
            //multiply the frequencies of the three stored corners. The query point (x, y) is fixed and is not counted.
            //note : The query point is considered fixed—it is not taken from the map and therefore doesn't contribute a multiplicity. Only the three stored corners do.
            count += getCount(x+d, y) //first stored corner
                    * getCount(x+d, y2) //second stored corner
                    * getCount(x, y2); //third stored corner

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

            if(y == y2){
                continue; //skip the query point itself; a square side must have positive length
            }

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

        if (!map.containsKey(x)) {
            return 0;
        }

        return map.get(x).getOrDefault(y, 0);
    }
}
