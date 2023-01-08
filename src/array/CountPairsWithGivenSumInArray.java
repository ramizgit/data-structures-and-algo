package array;

import java.util.HashMap;
import java.util.Map;

public class CountPairsWithGivenSumInArray {
    public static void main(String[] args)
    {
        System.out.println(countPairs(new int[]{1, 5, 7, -1}, 6));
        System.out.println(countPairs(new int[]{1, 5, 7, -1, 5}, 6));
        System.out.println(countPairs(new int[]{1, 1, 1, 1}, 2));
        System.out.println(countPairs(new int[]{10, 12, 10, 15, -1, 7, 6, 5, 4, 2, 1, 1, 1}, 11));
    }

    public static int countPairs(int[] arr, int sum)
    {
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for(int i=0; i<arr.length; i++)
        {
            if(map.containsKey(sum - arr[i]))
            {
                count += map.get(sum - arr[i]);
            }

            if(map.containsKey(arr[i]))
            {
                map.put(arr[i], map.get(arr[i])+1);
            }
            else
            {
                map.put(arr[i], 1);
            }
            //System.out.println("map : "+map);
        }
        return count;
    }
}
