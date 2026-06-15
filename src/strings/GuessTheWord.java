package consistenthashing.strings;

import java.util.*;

public class GuessTheWord {

    //https://leetcode.com/problems/guess-the-word/description/

    public void findSecretWord(String[] words, Master master, int allowedGuesses )
    {
        List<String> candidates = new ArrayList<>(Arrays.asList(words));

        while(allowedGuesses > 0 && !candidates.isEmpty()){

            //pick a word from candidates
            String pick = candidates.get(0);

            //call Master.guess()
            int match = Master.guess(pick);

            //secret found?
            if(match == 6){
                System.out.println("You guessed the secret word correctly.");
                return;
            }

            //filter words with this match count, rest all can be discarded
            List<String> filtered = new ArrayList<>();
            for(String candidate : candidates){
                if(matchCount(pick, candidate) == match){
                    filtered.add(candidate);
                }
            }

            candidates = filtered;
            allowedGuesses--;
        }
    }

    public int matchCount(String w1, String w2)
    {
        int count = 0;

        for(int i=0; i<w1.length(); i++){
            if(w1.charAt(i) == w2.charAt(i)){
                count++;
            }
        }
        return count;
    }

    class Master{

        //dummy method
        public static int guess(String word) {
            return -1;
        }
    }
}
