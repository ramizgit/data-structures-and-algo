package graph;

import java.util.*;

public class AlienDictionary {

    //https://leetcode.com/problems/alien-dictionary/description/

    public String alienOrder(String[] words)
    {
        //initialize graph as adjacency list
        Map<Character, Set<Character>> graph = new HashMap<>(); //set for efficient lookup later

        for(String word : words){
            for(char ch : word.toCharArray()){
                graph.putIfAbsent(ch, new HashSet<>());
            }
        }

        //indegree of each 26 letters
        int[] indegree = new int[26];

        //populate graph and indegree as per the input
        for(int i = 0; i < words.length - 1; i++){

            String word1 = words[i];
            String word2 = words[i+1];

            int minLen = Math.min(word1.length(), word2.length());
            boolean diff = false;

            for(int j = 0; j < minLen; j++){

                char ch1 = word1.charAt(j);
                char ch2 = word2.charAt(j);

                if(ch1 == ch2){
                    continue;
                }

                diff = true;

                //avoid duplicate edges, prevents indegree inflation (important)
                if(!graph.get(ch1).contains(ch2)){
                    graph.get(ch1).add(ch2);
                    indegree[ch2 - 'a']++;
                }

                break; // IMPORTANT : first differing character uniquely determines the ordering
            }

            // invalid prefix case: longer word appears before its prefix, example: ["apple", "app"]
            if(!diff && word1.length() > word2.length()){
                return "";
            }
        }

        //Start with all 0-indegree nodes from the graph, topological sort - standard Kahn's algorithm
        Queue<Character> bfsQueue = new ArrayDeque<>();

        for(char ch : graph.keySet()){ //important : do not iterate whole indegree array, as it will add all letters a-z but not all supposed to be in the graph
            if(indegree[ch - 'a'] == 0){
                bfsQueue.offer(ch);
            }
        }

        StringBuilder order = new StringBuilder();

        while(!bfsQueue.isEmpty()){

            char curr = bfsQueue.poll();
            order.append(curr); //add to the answer

            //explore neighbours
            for(char neighbour : graph.get(curr)){

                indegree[neighbour - 'a']--;

                if(indegree[neighbour - 'a'] == 0){
                    bfsQueue.offer(neighbour);
                }
            }
        }

        // cycle detection
        if(order.length() != graph.size()){
            return ""; //cycle found
        }

        return order.toString();
    }
}
