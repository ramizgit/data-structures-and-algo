package array;

import java.util.HashMap;
import java.util.Map;

public class SubArraySumK {

    public static void main(String[] args)
    {
        System.out.println(subArrayCount(new int[]{1,1,1}, 2));//2
        System.out.println(subArrayCount(new int[]{1,-1,1,1,1,1}, 3));//4
    }

    private static int subArrayCount(int[] arr, int k)
    {
        int sum = 0;
        int result = 0;
        Map<Integer, Integer> prefixMap = new HashMap<>();
        prefixMap.put(0, 1);

        for(int i=0; i<arr.length; i++){
            sum += arr[0];
            int diff = sum - k;
            result += prefixMap.getOrDefault(diff, 0);
            prefixMap.put(sum, prefixMap.getOrDefault(sum, 0)+1);
        }

        return result;
    }
}
