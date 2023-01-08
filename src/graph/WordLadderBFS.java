package graph;

import javafx.util.Pair;

import java.util.*;

public class WordLadderBFS {

    public static void main(String[] args)
    {
        System.out.println("Shortest sequene = "+ladderLength("hit", "cog", Arrays.asList("hot","dot","dog","lot","log","cog")));//5
        System.out.println("Shortest sequene = "+ladderLength("hit", "hot", Arrays.asList("hot","dot","dog","lot","log","cog")));//2
        System.out.println("Shortest sequene = "+ladderLength("hit", "lot", Arrays.asList("hot","dot","dog","lot","log","cog")));//3
        System.out.println("Shortest sequene = "+ladderLength("hit", "tot", Arrays.asList("hot","dot","dog","lot","log","cog")));//0
    }

    public static int ladderLength(String beginWord, String endWord, List<String> wordList)
    {
        if(!wordList.contains(endWord)){
            return 0;
        }

        //create pattern map from wordList
        Map<String, List<String>> patternmap = getPatternMap(wordList);

        //now run BFS
        return bfs(beginWord, endWord, patternmap);
    }

    public static int bfs(String beginWord, String endWord, Map<String, List<String>> patternmap)
    {
        Queue<Pair<String, Integer>> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.add(new Pair<>(beginWord, 1));
        visited.add(beginWord);

        while (!queue.isEmpty()){
            Pair<String, Integer> poll = queue.poll();
            String word = poll.getKey();

            if(word.equals(endWord)){
                return poll.getValue();
            }

            for(int i=0; i<word.length(); i++){
                String pattern = word.substring(0, i) + "*" + word.substring(i+1);
                List<String> words = patternmap.getOrDefault(pattern, new ArrayList<>());
                for(String w : words){
                    if(!visited.contains(w)){
                        visited.add(w);
                        queue.add(new Pair<>(w, poll.getValue()+1));
                    }

                }
            }
        }

        return 0;
    }

    /*public static void dfs(String beginWord, String endWord, Map<String, List<String>> adjList, List<String> path, List<List<String>> result, Set<String> visited)
    {
        visited.add(beginWord);
        path.add(beginWord);

        if(beginWord.equals(endWord)){
            result.add(new ArrayList<>(path));
        }else {
            List<String> words = adjList.get(beginWord);

            if(words == null){
                return;
            }

            for(String word : words){
                if(!visited.contains(word)){
                    dfs(word, endWord, adjList, path, result, visited);
                }

            }
        }
        path.remove(path.size()-1);
    }*/

    public static Map<String, List<String>> getPatternMap(List<String> wordList)
    {
        Map<String, List<String>> patternmap = new HashMap<>();

        for(String word : wordList){
            for(int i=0; i<word.length(); i++){
                String pattern = word.substring(0, i) + "*" + word.substring(i+1);
                List<String> list = patternmap.getOrDefault(pattern, new ArrayList<>());
                list.add(word);
                patternmap.put(pattern, list);
            }
        }
        return patternmap;
    }
}
