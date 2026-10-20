package heap;

import java.util.*;

public class TaskScheduler {

    //https://leetcode.com/problems/task-scheduler/

    public int leastInterval(char[] tasks, int n)
    {
        //frequency of tasks
        Map<Character, Integer> frequency = new HashMap<>();
        for(char ch : tasks){
            frequency.put(ch, frequency.getOrDefault(ch, 0)+1);
        }

        //sort tasks by frequency using max heap
        PriorityQueue<Character> maxheap = new PriorityQueue<>( (a,b) -> frequency.get(b) - frequency.get(a) );
        maxheap.addAll(frequency.keySet());

        List<String> result = new ArrayList<>();
        int time = 0;

        while (!maxheap.isEmpty()){

            List<Character> remainingTasks = new ArrayList<>();

            for(int i=0; i<=n; i++){
                if(!maxheap.isEmpty()){
                    Character task = maxheap.poll();
                    frequency.put(task, frequency.get(task)-1);

                    if (frequency.get(task) > 0) {
                        remainingTasks.add(task); //only add a task to used if it has remaining executions
                    }

                    result.add(task.toString());
                    time++;
                }else {
                    if(remainingTasks.isEmpty()){
                        break; //early exit as all tasks completed; no need to add trailing idle slots
                    }
                    result.add("Idle");
                    time++;
                }
            }

            //add unfinished tasks back to heap
            maxheap.addAll(remainingTasks);
        }

        System.out.println(result);
        return time;
    }
}
