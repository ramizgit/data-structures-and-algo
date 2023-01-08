package array;

import java.util.HashMap;
import java.util.Map;

public class KDiffPairInArray {
    public static void main(String[] args)
    {
        System.out.println(findPairs(new int[]{3,1,4,1,5}, 2)); //2 ----> (1, 3) and (3, 5)
        System.out.println(findPairs(new int[]{1,2,3,4,5}, 1)); //4 -----> (1, 2), (2, 3), (3, 4) and (4, 5).
        System.out.println(findPairs(new int[]{1,3,1,5,4}, 0)); //1 -----> (1, 1)
        System.out.println(findPairs(new int[]{1,3,2,5,4}, 0)); //0
    }

    public static int findPairs(int[] nums, int k)
    {
        Map<Integer, Integer> map = new HashMap<>();
        int count=0;

        for(int n : nums){
            map.put(n, map.getOrDefault(n, 0)+1);
        }

        for(int n : map.keySet()){
            if( (k > 0 && map.containsKey(n+k)) || (k == 0 && map.get(n) > 1) ){
                count++;
            }
        }

        return count;
    }
}
