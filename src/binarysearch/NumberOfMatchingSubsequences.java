package binarysearch;

public class NumberOfMatchingSubsequences {

    //https://leetcode.com/problems/number-of-matching-subsequences/description/

    //todo : implement

    /*
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

    /*
    buildIndexMap(t)

    map<Character, List<Integer>> indexMap

    for each index i in t
        indexMap[t[i]].add(i)


    isSubsequence(word)

        previousMatchedIndex = -1

        for each character c in word

            if c not present in indexMap
                return false

            positions = indexMap.get(c)

            nextIndex = first position in positions
                        that is > previousMatchedIndex
                        (using binary search)

            if nextIndex does not exist
                return false

            previousMatchedIndex = nextIndex

        return true


            firstGreaterThan(list, target)

        low = 0
        high = list.size() - 1
        answer = -1

        while low <= high

            mid = low + (high - low) / 2

            if list[mid] > target
                answer = list[mid]
                high = mid - 1      // look for a smaller valid index
            else
                low = mid + 1

        return answer
     */

    public int numMatchingSubseq(String s, String[] words)
    {

        return 0;
    }
}
