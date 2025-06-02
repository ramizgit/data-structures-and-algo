package google;

import java.util.ArrayList;
import java.util.List;

public class GuessTheWord {
    //todo:practice

    private static void main(String[] args)
    {

    }

    private void findSecretWord(String[] words, Master master)
    {
        String[] candidates = copy(words);

        while (candidates.length != 0){

            //pick any one word
            String random = candidates[0];

            //call master.match
            int match = master.guess(random);

            if(match == 6){
                System.out.println("yay!!! secret found");
                return;
            }

            List<String> tempCandidateList = new ArrayList<>();

            //reduce candidate list
            for(int i=1; i<candidates.length; i++){
                if(master.findMatches(random, candidates[i]) == match){
                    tempCandidateList.add(candidates[i]);
                }
            }

            //reset candidates
            candidates = new String[tempCandidateList.size()];
            for(int i=0; i<tempCandidateList.size(); i++){
                candidates[i] = tempCandidateList.get(i);
            }
        }

        System.out.println("secret not found");
    }

    private static String[] copy(String[] original)
    {
        String[] copy = new String[original.length];
        for(int i=0; i<original.length; i++){
            copy[i] = original[i];
        }

        return copy;
    }

}

class Master{
    public int guess(String word) {
        return -1;
    }

    public int findMatches(String w1, String w2){
        int count = 0;
        for(int i=0; i<6; i++){
            if(w1.charAt(i) == w2.charAt(i)){
                count++;
            }
        }
        return count;
    }
}
