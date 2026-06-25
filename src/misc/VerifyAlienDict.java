package meta;

import java.util.List;

public class VerifyAlienDict {

    //https://leetcode.com/problems/verifying-an-alien-dictionary/description/

    private static boolean isAlienSorted(List<String> words, String order)
    {
        //edge case
        if(words == null || words.isEmpty() || words.size() == 1){
            return true;
        }

        //alphabet order rank
        int[] rank = new int[26];

        for(int i=0; i<order.length(); i++){
            rank[order.charAt(i) - 'a'] = i;
        }

        for(int i=0; i<words.size()-1; i++){

            String word1 = words.get(i);
            String word2 = words.get(i+1);

            int minLen = Math.min(word1.length(), word2.length());
            boolean diff = false;

            //compare the two words
            for(int j=0; j<minLen; j++){

                char ch1 = word1.charAt(j);
                char ch2 = word2.charAt(j);

                if(ch1 == ch2){
                    continue;
                }

                diff = true;

                if (rank[ch1 - 'a'] > rank[ch2 - 'a']) {
                    return false;
                }

                // words are in correct order, move to next pair
                break;
            }

            // prefix case
            if (!diff && word1.length() > word2.length()) {
                return false;
            }
        }

        return true;
    }
}

