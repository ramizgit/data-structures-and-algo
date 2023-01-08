package heap;

import java.util.*;

public class CpuTaskSchedulerLeetcode {
    public static void main(String[] args)
    {
        char[] arr = {'A', 'A', 'A', 'B', 'B', 'B'};

        System.out.println(leastInterval(arr, 2));
        System.out.println(leastInterval(arr, 0));
        
        char[] arr2 = {'A','A','A','A','A','A','B','C','D','E','F','G'};
        System.out.println(leastInterval(arr2, 2));
        //A -> B -> C -> A -> D -> E -> A -> F -> G -> A -> idle -> idle -> A -> idle -> idle -> A
    }

    public static int leastInterval(char[] tasks, int n)
    {
        Map<Character, Integer> map = new HashMap<>();
        List<String> result = new ArrayList<>();

        for(char ch : tasks){
            map.put(ch, map.getOrDefault(ch, 0)+1);
        }

        Queue<Character> maxheap = new PriorityQueue<>( (a,b) -> map.get(b) - map.get(a) );
        maxheap.addAll(map.keySet());

        while (!maxheap.isEmpty()){

            Queue<Character> queue = new LinkedList<>();

            for(int i=0; i<=n; i++){
                if(!maxheap.isEmpty()){
                    Character task = maxheap.poll();
                    queue.add(task);
                    map.put(task, map.get(task)-1);
                    result.add(task.toString());

                }else {
                    result.add("Idle");
                }
            }

            for(char ch : queue){
                if(map.get(ch) > 0){
                    maxheap.add(ch);
                }
            }

        }

        System.out.println(result);
        return result.size();
    }
}
