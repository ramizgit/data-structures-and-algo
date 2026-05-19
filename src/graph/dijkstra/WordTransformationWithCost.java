package graph.dijkstra;

import java.util.*;

/*
Problem — Word Transformation With Costs
You are given:
a beginWord
an endWord
a dictionary wordList
and a cost array changeCost[26]

You may transform one word into another by:

changing exactly ONE character at a time
resulting word must exist in dictionary

However:

Changing character:

x -> y

costs:

abs(changeCost[x] - changeCost[y])

Return the minimum total cost to convert:

beginWord -> endWord

If impossible, return -1.
 */

/*
Trick : in simple word ladder, edge weight is constant (1), hence BFS works there. but here cost is different for
diff. word transformation, hence we need dijkstra.
 */

public class WordTransformationWithCost {

    public int minimumTransformationCost(String beginWord, String endWord, List<String> wordList, int[] changeCost)
    {
        //fail if end word does not exist
        if(!wordList.contains(endWord)){
            return -1;
        }

        //ensure beginWord also participates in pattern generation
        if(!wordList.contains(beginWord)){
            wordList.add(beginWord);
        }

        //initialize graph
        Map<String, List<String>> patternMap = new HashMap<>();
        for(String word : wordList){
            for(int i=0; i<word.length(); i++){
                String pattern = word.substring(0, i) + "*" + word.substring(i+1);
                patternMap.computeIfAbsent(pattern, key -> new ArrayList<>()).add(word);
            }
        }

        //dijkstar algo
        PriorityQueue<State> pq = new PriorityQueue<>( (a, b) -> a.cost - b.cost ); //minheap
        pq.offer(new State(beginWord, 0)); //starting word

        //dist array : dist[word] = minimum cost
        Map<String, Integer> dist = new HashMap<>();
        dist.put(beginWord, 0); //starting word

        while(!pq.isEmpty()){
            State curr = pq.poll();

            //stale entry check
            if(curr.cost > dist.getOrDefault(curr.word, Integer.MAX_VALUE)){
                continue;
            }

            //early exit
            if(curr.word.equals(endWord)){
                return curr.cost;
            }

            //explore neighbours
            for(int i=0; i<curr.word.length(); i++){
                String pattern = curr.word.substring(0, i) + "*" + curr.word.substring(i+1);
                List<String> neighbours = patternMap.getOrDefault(pattern, Collections.emptyList());
                for(String neighbour : neighbours){

                    if(neighbour.equals(curr.word)){
                        continue; //skip same word
                    }

                    //find changed character
                    int edgeCost = transformationCost(curr.word, neighbour, changeCost);

                    int newCost = curr.cost + edgeCost;

                    if(newCost < dist.getOrDefault(neighbour, Integer.MAX_VALUE)){
                        //relaxation
                        dist.put(neighbour, newCost);

                        //enqueue
                        pq.offer(new State(neighbour, newCost));
                    }
                }
            }
        }

        return -1;
    }

    //calculate cost of transforming one word into another
    private int transformationCost(String word1, String word2, int[] changeCost)
    {
        for(int i = 0; i < word1.length(); i++){

            char ch1 = word1.charAt(i);
            char ch2 = word2.charAt(i);

            if(ch1 != ch2){
                //valid neighbours differ by exactly one character
                return Math.abs(changeCost[ch1 - 'a'] - changeCost[ch2 - 'a']);
            }
        }

        return 0;
    }

    class State{
        String word;
        int cost;

        public State(String word, int cost) {
            this.word = word;
            this.cost = cost;
        }
    }

}
