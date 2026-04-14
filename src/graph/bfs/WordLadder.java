package graph.bfs;

import java.util.*;

public class WordLadder {

    public int ladderLength(String beginWord, String endWord, List<String> wordList)
    {
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

        //bfs
        Queue<WordSeq> queue = new ArrayDeque<>();
        queue.offer(new WordSeq(beginWord, 1));

        Set<String> visited = new HashSet<>();
        visited.add(beginWord);

        while(!queue.isEmpty()){
            WordSeq curr = queue.poll();

            //breaking condition
            if(curr.word.equals(endWord)){
                return curr.seq;
            }

            //explore neighbours
            for(int i=0; i<curr.word.length(); i++){
                String pattern = curr.word.substring(0, i) + "*" + curr.word.substring(i+1);
                List<String> neighbours = patternMap.getOrDefault(pattern, Collections.emptyList());
                for(String neighbour : neighbours){
                    if(!visited.contains(neighbour)){
                        visited.add(neighbour);
                        queue.offer(new WordSeq(neighbour, curr.seq+1));
                    }
                }
            }
        }

        return 0;
    }
}

class WordSeq{
    String word;
    int seq;

    public WordSeq(String word, int seq) {
        this.word = word;
        this.seq = seq;
    }
}
