package slidingWindow;

import java.util.*;

public class MinimumWindowSubstring {

    public String minWindow(String s, String t)
    {
        if (s == null || t == null || s.length() < t.length()) {
            return "";
        }

        Map<Character, Integer> freq = new HashMap<>();
        for(char ch : t.toCharArray()){
            freq.put(ch, freq.getOrDefault(ch, 0) + 1);
        }

        int left = 0;
        int right = 0;
        int result = Integer.MAX_VALUE;
        int remaining = t.length();
        int bestStart = 0;
        int bestEnd = 0;

        while(right < s.length()){
            char ch = s.charAt(right);

            if(freq.containsKey(ch)){
                if(freq.get(ch) > 0){
                    remaining--;
                }
                freq.put(ch, freq.get(ch) - 1);
            }

            while(remaining == 0){
                if(result > (right - left + 1)){
                    result = right - left + 1;
                    bestStart = left;
                    bestEnd = right;
                }

                char lch = s.charAt(left);
                if(freq.containsKey(lch)){
                    if(freq.get(lch) >= 0){
                        remaining++;
                    }
                    freq.put(lch, freq.get(lch) + 1);
                }

                left++;
            }

            right++;
        }

        return result == Integer.MAX_VALUE ? "" : s.substring(bestStart, bestEnd + 1);
    }
}
