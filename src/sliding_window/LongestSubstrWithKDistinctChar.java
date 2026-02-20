package slidingWindow;

import java.util.*;

public class LongestSubstrWithKDistinctChar {

    public int lengthOfLongestSubstring(String s, int k)
    {
        if(s == null || s.isEmpty()){
            return 0;
        }

        Map<Character, Integer> freq = new HashMap<>();
        int left = 0;
        int right = 0;
        int maxLen = -1;

        while(right < s.length()){
            char ch = s.charAt(right);
            freq.put(ch, freq.getOrDefault(ch, 0) + 1);

            while(freq.size() > k){
                char lch = s.charAt(left);
                freq.put(lch, freq.get(lch) - 1);

                if(freq.get(lch) == 0){
                    freq.remove(lch);
                }

                left++;
            }

            if (freq.size() == k) {
                maxLen = Math.max(maxLen, right - left + 1);
            }

            right++;
        }
        return maxLen;
    }
}
