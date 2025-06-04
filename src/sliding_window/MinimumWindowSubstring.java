package slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {

    //https://leetcode.com/problems/minimum-window-substring/description/

    public static void main(String[] args)
    {
        System.out.println(minWindow("ADOBECODEBANC", "ABC")); //BANC
        System.out.println(minWindow("AAAAAADBCA","ABC")); //BCA
    }

    private static String minWindow(String s, String t)
    {
        //handle edge cases
        if(s.isEmpty() || t.isEmpty() || s.length() < t.length()){
            return "";
        }
        
        //get freqency of target string
        Map<Character, Integer> tmap = new HashMap<>();
        for(char ch : t.toCharArray()){
            tmap.put(ch, tmap.getOrDefault(ch, 0) + 1);
        }

        int left = 0;
        int right = 0;
        int matched = 0;
        int minLen = Integer.MAX_VALUE;
        int start = 0;
        int end = 0;

        while (right < s.length()){
            char rightCh = s.charAt(right);

            //reduce tmap freq is matching char found
            if(tmap.containsKey(rightCh)){
                tmap.put(rightCh, tmap.getOrDefault(rightCh, 0) - 1);

                if(tmap.get(rightCh) == 0){
                    //one char fully matched, increase count
                    matched++;
                }
            }

            //move left ptr if fully matched
            while (tmap.keySet().size() == matched){
                //capture min window len
                if(minLen > (right - left + 1)){
                    minLen = right - left + 1;
                    start = left;
                    end = right + 1;
                }

                char leftCh = s.charAt(left++);
                if(tmap.containsKey(leftCh)){
                    if(tmap.get(leftCh) == 0){
                        matched--;
                    }
                    tmap.put(leftCh, tmap.getOrDefault(leftCh, 0) + 1);
                }
            }
            right++;
        }

        return s.substring(start, end);
    }
}
