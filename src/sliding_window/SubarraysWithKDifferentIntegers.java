package slidingWindow;

import java.util.*;

public class SubarraysWithKDifferentIntegers {

    public int subarraysWithKDistinct(int[] nums, int k) {
        if(nums == null || nums.length == 0){
            return 0;
        }

        return atMost(nums, k) - atMost(nums, k - 1);
    }

    public int atMost(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        int left = 0;
        int right = 0;
        int count = 0;

        while(right < nums.length){
            int num = nums[right];
            freq.put(num, freq.getOrDefault(num, 0) + 1);

            while(freq.size() > k){
                int lnum = nums[left];
                freq.put(lnum, freq.get(lnum) - 1);

                if(freq.get(lnum) == 0){
                    freq.remove(lnum);
                }

                left++;
            }

            // ALL subarrays ending at right are valid
            count += right - left + 1;

            right++;
        }
        return count;
    }
}

