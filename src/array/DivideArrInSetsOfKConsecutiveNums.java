package array;

import java.util.*;

public class DivideArrInSetsOfKConsecutiveNums {

    //https://leetcode.com/problems/divide-array-in-sets-of-k-consecutive-numbers/description/

    public static void main(String[] args)
    {

    }

    private static boolean partitionArray(int[] nums, int k)
    {
        if(nums == null || nums.length % k != 0){
            return false;
        }

        //build freq map
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for(int num : nums){ //0(nlogn)
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        while(!map.isEmpty()){
            int start = map.firstKey(); //greedy approach
            for(int i=0; i<k; i++){ //0(klogn)
                int curr = start + i;
                if(!map.containsKey(curr)){
                    return false;
                }
                map.put(curr, map.get(curr) - 1);
                if(map.get(curr) == 0){
                    map.remove(curr);
                }
            }
        }

        return true;
    }
}
