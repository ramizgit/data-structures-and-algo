package graph.unionfindDSU;

import java.util.*;

public class SentenceSimilarityII {

    //https://github.com/doocs/leetcode/blob/main/solution/0700-0799/0737.Sentence%20Similarity%20II/README_EN.md

    /*
    Approach:
    1. Assign a unique integer ID to every unique word in similarPairs.
    2. Build a Disjoint Set Union (Union-Find) over these IDs.
    3. Union the IDs of every similar word pair, forming connected components.
    4. Compare the two sentences word by word:
       - If the words are identical, continue.
       - If either word is unseen, they cannot be similar.
       - Otherwise, check whether both words belong to the same DSU component.
    5. If every corresponding pair is similar, return true; otherwise, return false.
    */

    //IMPORTANT : the similarity relation is transitive.
    // Let:
    // p = number of similar pairs
    // s = sentence length
    // w = number of unique words
    //
    // Time  : O((p + s) * α(w)) ≈ O(p + s)
    // Space : O(w)
    public boolean areSentencesSimilarTwo(String[] sentence1, String[] sentence2, String[][] similarPairs)
    {
        //input validation
        if(sentence1 == null || sentence2 == null || sentence1.length != sentence2.length){
            return false;
        }

        //first make one pass over similar words and give every word an ID, as DSU works with integers.
        Map<String, Integer> wordId = new HashMap<>();
        int id = 0;

        for(String[] pair : similarPairs){ //time : O(similarPairs length) = O(p)

            String word1 = pair[0];
            if(!wordId.containsKey(word1)){
                wordId.put(word1, id++);
            }

            String word2 = pair[1];
            if(!wordId.containsKey(word2)){
                wordId.put(word2, id++);
            }
        }

        //now we know all unique words
        //initialize DSU for all unique words, time: O(unique words) = O(w)
        UnionFind uf = new UnionFind(wordId.size());

        for(String[] pair : similarPairs){ //time : O(p)
            uf.union(wordId.get(pair[0]), wordId.get(pair[1])); //union similar words - O(a(n)) ~ O(1)
        }

        //iterate over sentence
        for(int i=0; i<sentence1.length; i++){ //time : O(sentence length) = O(s)

            String word1 = sentence1[i];
            String word2 = sentence2[i];

            if(word1.equals(word2)){
                continue;
            }

            //handle unseen words
            if(!wordId.containsKey(word1) || !wordId.containsKey(word2)){
                return false;
            }

            if(uf.find(wordId.get(word1)) != uf.find(wordId.get(word2))){ //time : O(a(n)) ~ O(1)
                return false; //word1 and word2 don't belong to same component of similar words
            }
        }

        return true;
    }
}
