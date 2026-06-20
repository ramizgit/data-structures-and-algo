package graph.bfs;

import java.util.*;

public class OpenTheLock {

    //https://leetcode.com/problems/open-the-lock/description/

    public int openLock(String[] deadends, String target)
    {
        String start = "0000";

        Set<String> deadSet = new HashSet<>(List.of(deadends));

        //edge case
        if(deadSet.contains(start)){
            return -1;
        }

        //bfs logic
        Queue<State> bfsQueue = new ArrayDeque<>();
        bfsQueue.offer(new State(start, 0));

        Set<String> visited = new HashSet<>();
        visited.add(start);

        while(!bfsQueue.isEmpty()){

            State curr = bfsQueue.poll();

            //exit condition
            if(curr.lock.equals(target)){
                return curr.turns;
            }

            //explore neighbours
            for(String neighbour : getNeighbours(curr.lock)){
                if(!visited.contains(neighbour) && !deadSet.contains(neighbour)){
                    visited.add(neighbour);
                    bfsQueue.offer(new State(neighbour, curr.turns+1));
                }
            }
        }

        return -1;
    }

    public List<String> getNeighbours(String lock)
    {
        List<String> neighbours = new ArrayList<>();

        char[] arr = lock.toCharArray();

        for(int i=0; i<arr.length; i++){
            char original = arr[i];

            //increment
            arr[i] = (char) ((original - '0' + 1) % 10 + '0');
            neighbours.add(new String(arr));

            //decrement
            arr[i] = (char) ((original - '0' - 1 + 10) % 10 + '0');
            neighbours.add(new String(arr));

            // restore
            arr[i] = original;
        }

        return neighbours;
    }

    class State {
        String lock;
        int turns;

        public State(String lock, int turns) {
            this.lock = lock;
            this.turns = turns;
        }
    }
}


