package slidingWindow;

import java.util.*;

public class LongestRepeatingCharacterReplacement {

    public int characterReplacement(String s, int k)
    {
        if(s == null || s.isEmpty()){
            return 0;
        }

        int left = 0;
        int right = 0;
        int maxLen = 0;
        int maxFreq = 0;
        Map<Character, Integer> freq = new HashMap<>();

        while(right < s.length()){
            char ch = s.charAt(right);
            freq.put(ch, freq.getOrDefault(ch, 0) + 1);
            maxFreq = Math.max(maxFreq, freq.get(ch));

            while( ((right - left + 1) - maxFreq) > k){
                //shrink
                char lch = s.charAt(left);
                freq.put(lch, freq.get(lch) - 1);
                left++;
            }

            maxLen = Math.max(maxLen, right - left + 1);
            right++;
        }

        return maxLen;
    }
}
