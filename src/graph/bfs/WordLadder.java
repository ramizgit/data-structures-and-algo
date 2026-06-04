package graph.bfs;

import java.util.*;

public class WordLadder {

    //https://leetcode.com/problems/word-ladder/description/

    public int ladderLength(String beginWord, String endWord, List<String> wordList)
    {
        //edge case
        if(!wordList.contains(endWord)){
            return 0;
        }

        //build pattern map
        Map<String, List<String>> patternMap = new HashMap<>();
        for(String word : wordList){
            for(int i=0; i<word.length(); i++){
                String pattern = word.substring(0, i) + "*" + word.substring(i+1);
                patternMap.computeIfAbsent(pattern, key -> new ArrayList<>()).add(word);
            }
        }

        //bfs logic
        Queue<State> bfsQueue = new ArrayDeque<>();
        bfsQueue.offer(new State(beginWord, 1)); //starting word

        Set<String> visited = new HashSet<>(); //to avoid going in cycle
        visited.add(beginWord);

        while(!bfsQueue.isEmpty()){

            State curr = bfsQueue.poll();

            //exit condition
            if(curr.word.equals(endWord)){
                return curr.steps;
            }

            //explore neighbours
            for(int i=0; i<curr.word.length(); i++){

                String pattern = curr.word.substring(0, i) + "*" + curr.word.substring(i+1);

                List<String> neighbours = patternMap.getOrDefault(pattern, Collections.emptyList());

                for(String neighbour : neighbours){
                    if(!visited.contains(neighbour)){
                        bfsQueue.offer(new State(neighbour, curr.steps +1));
                        visited.add(neighbour);
                    }
                }
            }
        }

        return 0;
    }

    class State {
        String word;
        int steps;

        public State(String word, int steps) {
            this.word = word;
            this.steps = steps;
        }
    }
}


