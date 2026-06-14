package consistenthashing.sliding_window;

import java.util.HashSet;
import java.util.Set;

public class LongestDuplicateSubstring {

    //https://leetcode.com/problems/longest-duplicate-substring/description/

    public String longestDupSubstring(String s)
    {
        //input validation
        if(s == null || s.isEmpty()){
            return "";
        }

        int n = s.length();

        //binary search
        int low = 0;
        int high = n-1;
        int maxLength = 0;
        int bestStart = 0;

        while(low <= high){
            int mid = low + (high - low)/2;

            //check all substring of length mid for dupe
            int start = hasDuplicateSubstring(s, mid);
            if(start != -1){
                maxLength = mid; //possible answer
                bestStart = start;
                low = mid + 1; //try higher
            }else {
                high = mid -1;
            }
        }

        return maxLength == 0 ? "" : s.substring(bestStart, bestStart + maxLength);
    }

    /*
    This method does two things:-
        1. Tell us whether a duplicate exists.
        2. Tell us which duplicate substring we found.
     */
    private int hasDuplicateSubstring(String s, int len)
    {
        if(len == 0){
            return -1;
        }

        long base = 31;

        // base^(len-1)
        long highestPower = 1;
        for(int i=1; i<len; i++){
            highestPower *= base;
        }

        long hash = 0;

        // hash of first window
        for(int i=0; i<len; i++){
            hash = hash * base + s.charAt(i);
        }

        Set<Long> seen = new HashSet<>();
        seen.add(hash);

        for(int start=1; start<=s.length()-len; start++){

            char outgoing = s.charAt(start - 1);
            char incoming = s.charAt(start + len - 1);

            // rolling hash update
            hash = hash - outgoing * highestPower;
            hash = hash * base;
            hash = hash + incoming;

            if(seen.contains(hash)){
                return start; // duplicate found
            }

            seen.add(hash);
        }

        return -1;
    }
}
