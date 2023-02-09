package strings;

import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstringLeetcode {

    public static void main(String[] args)
    {
        System.out.println(minWindow("ADOBECODEBANC","ABC"));
        System.out.println(minWindow("AAAAAADBCA","ABC"));
    }

    private static String minWindow(String s, String t)
    {
        //handle edge cases
        if(s.isEmpty() || t.isEmpty() || s.length() < t.length()){
            return "";
        }

        Map<Character, Integer> map = new HashMap<>();

        for(char ch : t.toCharArray()){
            map.put(ch, map.getOrDefault(ch, 0)+1);
        }

        int start = 0;
        int end = 0;
        int min = Integer.MAX_VALUE;
        int answer = 0;
        int count=0;

        while (end < s.length())
        {
            char ch = s.charAt(end);

            if(map.getOrDefault(ch, 0) > 0){
                count++;
            }
            map.put(ch, map.getOrDefault(ch, 0)-1);

            while (count == t.length())
            {
                if(min > (end - start + 1))
                {
                    min = end - start + 1;
                    answer = start;
                }

                map.put(s.charAt(start), map.getOrDefault(s.charAt(start), 0)+1);

                if(map.get(s.charAt(start)) > 0){
                    count--;
                }

                //reduce window size
                start++;
            }
            end++;
        }

        if(min == Integer.MAX_VALUE){
            return "";
        }

        return s.substring(answer, answer + min);
    }
}



