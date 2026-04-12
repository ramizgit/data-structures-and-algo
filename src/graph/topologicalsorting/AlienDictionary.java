package graph;

import java.util.*;

public class AlienDictionary {

    //https://leetcode.com/problems/alien-dictionary/description/

    public String foreignDictionary(String[] words)
    {
        //initialize graph
        Map<Character, List<Character>> graph = new HashMap<>();
        Map<Character, Integer> indegree = new HashMap<>();
        for(String word : words){
            for(char ch : word.toCharArray()){
                graph.putIfAbsent(ch, new ArrayList<>());
                indegree.putIfAbsent(ch, 0);
            }
        }

        //populate graph and indegree as per the input
        for(int i = 1; i < words.length; i++){
            String w1 = words[i - 1];
            String w2 = words[i];

            int len = Math.min(w1.length(), w2.length());
            boolean foundDiff = false;

            for(int j = 0; j < len; j++){
                char c1 = w1.charAt(j);
                char c2 = w2.charAt(j);

                if(c1 != c2){
                    // avoid duplicate edges
                    if(!graph.get(c1).contains(c2)){
                        graph.get(c1).add(c2);
                        indegree.put(c2, indegree.get(c2) + 1);
                    }
                    foundDiff = true;
                    break; // IMPORTANT
                }
            }

            // invalid case: prefix
            if(!foundDiff && w1.length() > w2.length()){
                return "";
            }
        }

        //topological sort
        Queue<Character> queue = new ArrayDeque<>();
        for(char ch : indegree.keySet()){
            if(indegree.get(ch) == 0){
                queue.offer(ch);
            }
        }

        StringBuilder order = new StringBuilder();

        while(!queue.isEmpty()){
            char curr = queue.poll();
            order.append(curr);

            for(char neighbour : graph.get(curr)){
                indegree.put(neighbour, indegree.get(neighbour) - 1);
                if(indegree.get(neighbour) == 0){
                    queue.offer(neighbour);
                }
            }
        }

        // cycle detection
        if(order.length() != graph.size()){
            return "";
        }

        return order.toString();
    }
}
