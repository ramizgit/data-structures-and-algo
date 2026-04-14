package graph.bfs;

import java.util.*;

public class OpenTheLock {

    //https://leetcode.com/problems/open-the-lock/description/

    public int openLock(String[] deadends, String target)
    {
        String start = "0000";

        Set<String> deadSet = new HashSet<>(List.of(deadends));
        if(deadSet.contains(start)){
            return -1;
        }

        //bfs logic
        Queue<LockSeq> queue = new ArrayDeque<>();
        queue.offer(new LockSeq(start, 0));

        Set<String> visited = new HashSet<>();
        visited.add(start);

        while(!queue.isEmpty()){
            LockSeq curr = queue.poll();

            //exit condition
            if(curr.lock.equals(target)){
                return curr.turns;
            }

            //explore nieghbours
            List<String> neighbours = getNeighbours(curr.lock);
            for(String neighbour : neighbours){
                if(!visited.contains(neighbour) && !deadSet.contains(neighbour)){
                    visited.add(neighbour);
                    queue.offer(new LockSeq(neighbour, curr.turns+1));
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
}

class LockSeq{
    String lock;
    int turns;

    public LockSeq(String lock, int turns) {
        this.lock = lock;
        this.turns = turns;
    }
}
