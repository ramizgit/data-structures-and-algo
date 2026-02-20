package slidingWindow;

import java.util.*;

public class FindAllAnagram {
    /*Given two strings s and p, return an array of all the start indices of p's anagrams in s.
    You may return the answer in any order.
     */

    public static void main(String[] args)
    {
        //s = "cbaebabacd", p = "abc"
    }

    public List<Integer> findAnagrams(String s, String p)
    {
        List<Integer> result = new ArrayList<>();
        if (s.length() < p.length()) return result;

        Map<Character, Integer> freq = new HashMap<>();
        for(char ch : p.toCharArray()){
            freq.put(ch, freq.getOrDefault(ch, 0) + 1);
        }

        int left = 0;
        int right = 0;
        int remaining = p.length();

        while(right < s.length()){
            char ch = s.charAt(right);

            if (freq.containsKey(ch)) {
                if (freq.get(ch) > 0) {
                    remaining--;
                }
                freq.put(ch, freq.get(ch) - 1);
            }

            if(right - left + 1 == p.length()){
                if(remaining == 0){
                    result.add(left);
                }

                char lch = s.charAt(left);

                if (freq.containsKey(lch)) {
                    if (freq.get(lch) >= 0) {
                        remaining++;
                    }
                    freq.put(lch, freq.get(lch) + 1);
                }

                left++;
            }

            right++;
        }

        return result;
    }
}
