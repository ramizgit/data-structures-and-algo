package binarysearch;

import java.util.*;

public class NumberOfMatchingSubsequences {

    //https://leetcode.com/problems/number-of-matching-subsequences/description/

    /*
    Approach:
    1. Preprocess the target string 't' by storing all indices of each character.
       Example:
       t = "abcabc"

       a -> [0, 3]
       b -> [1, 4]
       c -> [2, 5]

    2. For each query word:
       - Maintain previousMatchedIndex = -1.
       - For every character in the word:
           - Get the list of indices for that character.
           - Use binary search to find the first index > previousMatchedIndex.
           - If no such index exists, the word is NOT a subsequence.
           - Otherwise, update previousMatchedIndex.
       - If all characters are matched, the word is a subsequence.

    Time:
    Preprocessing : O(t.length())
    Each Query    : O(word.length() * log(t.length()))

    Space:
    O(t.length())
    */

    public int numMatchingSubseq(String s, String[] words)
    {
        //preprocess s and store char -> indices map
        Map<Character, List<Integer>> charToIdxMap = new HashMap<>();

        for(int i=0; i<s.length(); i++){
            char ch = s.charAt(i);
            charToIdxMap.computeIfAbsent(ch, key -> new ArrayList<>()).add(i);
        }

        int result = 0;

        for(String word : words){
            if(isSubSeq(word, charToIdxMap)){
                result++;
            }
        }

        return result;
    }

    private boolean isSubSeq(String word, Map<Character, List<Integer>> charToIdxMap)
    {
        int prevIdx = -1;

        for(int i=0; i<word.length(); i++){

            char ch = word.charAt(i);

            if(!charToIdxMap.containsKey(ch)){
                return false;
            }

            List<Integer> idxList = charToIdxMap.get(ch);

            int index = binarySearch(idxList, prevIdx);

            if(index == -1){
                return false; //not a subseq, try next word
            }

            prevIdx = index;
        }

        return true;
    }

    private int binarySearch(List<Integer> idxList, int prevIdx)
    {
        //binary search to find the first index > prevIdx
        int left = 0;
        int right = idxList.size()-1;
        int answer = -1;

        while(left <= right){
            int mid = left + (right - left)/2;

            if(idxList.get(mid) > prevIdx){
                answer = idxList.get(mid); //possible answer
                right = mid - 1; //try lower
            }else{
                left = mid + 1; //try higher
            }
        }

        return answer;
    }
}
