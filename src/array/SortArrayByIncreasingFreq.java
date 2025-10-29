package array;

import java.util.*;

public class SortArrayByIncreasingFreq {
    public static void main(String[] args) {
        frequencySort(new int[]{1, 1, 2, 2, 2, 3}); //[3,1,1,2,2,2]
        frequencySort(new int[]{2, 3, 1, 3, 2}); //[2,3,1,3,2]
        frequencySort(new int[]{-1, 1, -6, 4, 5, -6, 1, 4, 1}); //[5,-1,4,4,-6,-6,1,1,1]
    }

    private static void frequencySort(int[] nums) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        List<Integer> list = new ArrayList<>();

        for (int num : nums) {
            list.add(num);
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        Collections.sort(list, (a, b) -> {
            int aFreq = freqMap.get(a);
            int bFreq = freqMap.get(b);
            if (aFreq != bFreq) {
                return aFreq - bFreq;
            } else {
                return b - a;
            }
        });

        System.out.println(list);
    }
}
