package array;

import java.util.*;

public class SortArrByIncrFreq {
    //https://leetcode.com/problems/sort-array-by-increasing-frequency/description/

    public static void main(String[] args)
    {
        frequencySort(new int[]{1,1,2,2,2,3});
        frequencySort(new int[]{2,3,1,3,2});
    }

    private static int[] frequencySort(int[] nums)
    {
        Map<Integer, Integer> freq = new HashMap<>();

        for(int num : nums){
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        // convert int[] to Integer[]
        Integer[] arr = new Integer[nums.length];
        for (int i = 0; i < nums.length; i++) {
            arr[i] = nums[i];
        }

        Arrays.sort(arr, (a, b) -> {
         int f1 = freq.get(a);
         int f2 = freq.get(b);

         if(f1 != f2){
             return f1 - f2;
         }else{
             return b - a;
         }
        });

        // convert back to int[]
        for (int i = 0; i < nums.length; i++) {
            nums[i] = arr[i];
        }

        return nums;
    }
}
