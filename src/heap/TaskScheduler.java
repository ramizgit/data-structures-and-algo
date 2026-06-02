package heap;

import java.util.*;

public class TaskScheduler {

    //https://leetcode.com/problems/task-scheduler/

    public int leastInterval(char[] tasks, int n)
    {
        //frequency of tasks
        Map<Character, Integer> map = new HashMap<>();
        for(char ch : tasks){
            map.put(ch, map.getOrDefault(ch, 0)+1);
        }

        //sort tasks by frequency using max heap
        PriorityQueue<Character> maxheap = new PriorityQueue<>( (a,b) -> map.get(b) - map.get(a) );
        maxheap.addAll(map.keySet());

        List<String> result = new ArrayList<>();
        int time = 0;

        while (!maxheap.isEmpty()){
            List<Character> used = new ArrayList<>();

            for(int i=0; i<=n; i++){
                if(!maxheap.isEmpty()){
                    Character task = maxheap.poll();
                    used.add(task);
                    map.put(task, map.get(task)-1);
                    result.add(task.toString());
                    time++;
                }else {
                    if(used.isEmpty()){
                        break; //early exit as all tasks completed; no need to add trailing idle slots
                    }
                    result.add("Idle");
                    time++;
                }
            }

            //add unfinished tasks back to heap
            for(char ch : used){
                if(map.get(ch) > 0){
                    maxheap.add(ch);
                }
            }

        }

        System.out.println(result);
        return time;
    }
}
