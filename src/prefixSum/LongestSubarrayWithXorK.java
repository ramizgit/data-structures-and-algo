package binaryoperations;

import java.util.HashMap;
import java.util.Map;

public class LongestSubarrayWithXorK {

    public static void main(String[] args)
    {
        System.out.println(longestSubarray(new int[]{4, 2, 2, 6, 4}, 6)); //5
        System.out.println(longestSubarray(new int[]{4, 2, 2, 6, 4}, 4)); //3
    }

    private static int longestSubarray(int[] arr, int k)
    {
        int xor = 0;
        int maxSubarrayLen = Integer.MIN_VALUE;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);

        for(int i=0; i<arr.length; i++){
            xor ^= arr[i];

            if(map.containsKey(xor ^ k)){
                maxSubarrayLen = Math.max(maxSubarrayLen, i - map.get(xor ^ k));
            }

            if(!map.containsKey(xor)){
                map.put(xor, i);
            }
        }

        return maxSubarrayLen;
    }
}
