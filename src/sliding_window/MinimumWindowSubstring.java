package slidingWindow;

import java.util.*;

public class MinimumWindowSubstring {

    //https://leetcode.com/problems/minimum-window-substring/description/

    public String minWindow(String s, String t)
    {
        //edge case
        if (s == null || t == null || s.length() < t.length()) {
            return "";
        }

        //compute frequency of t
        int[] freq = new int[128]; //all ASCII chars including lower and upper case letters
        for(char ch : t.toCharArray()){
            freq[ch]++;
        }

        int left = 0;
        int right = 0;
        int remaining = t.length(); //remaining = number of chars from t still missing in the current window
        int minLength = Integer.MAX_VALUE;
        int bestStart = 0;

        while(right < s.length()){

            //expand window
            char rch = s.charAt(right);

            if(freq[rch] > 0){
                remaining--;
            }

            freq[rch]--;

            while(remaining == 0){

                //track min length
                int windowSize = right - left + 1;
                if(windowSize < minLength){
                    minLength = windowSize;
                    bestStart = left;
                }

                //shrink window
                char lch = s.charAt(left);
                left++;
                if(freq[lch] >= 0){
                    remaining++;
                }
                freq[lch]++;
            }

            right++;
        }

        return minLength == Integer.MAX_VALUE ? "" : s.substring(bestStart, bestStart + minLength);
    }

    public String minWindowUsingMap(String s, String t)
    {
        //edge cases
        if (s == null || t == null || s.length() < t.length()) {
            return "";
        }

        //compute frequency of t
        Map<Character, Integer> freq = new HashMap<>();
        for(char ch : t.toCharArray()){
            freq.put(ch, freq.getOrDefault(ch, 0) + 1);
        }

        int left = 0;
        int right = 0;
        int minLength = Integer.MAX_VALUE;
        int remaining = t.length();
        int bestStart = 0;

        while(right < s.length()){

            //expand window
            char ch = s.charAt(right);

            if(freq.containsKey(ch)){
                if(freq.get(ch) > 0){
                    remaining--;
                }
                freq.put(ch, freq.get(ch) - 1);
            }

            while(remaining == 0){

                //collect result
                if((right - left + 1) < minLength){
                    minLength = right - left + 1;
                    bestStart = left;
                }

                //shrink window
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

        return minLength == Integer.MAX_VALUE ? "" : s.substring(bestStart, bestStart + minLength);
    }
}
