package heap;

import java.util.*;

/*
You are given a list of task types.

Each task type has:

a unique uppercase letter (A-Z)
a remaining number of executions
an execution duration

Between two executions of the same task, there must be at least cooldown units of time after the previous execution finishes.

You may execute at most one task at a time.

You may execute tasks in any order.

Return the minimum total time required to finish all tasks.

If no task is currently available, the CPU remains idle.

Example 1
tasks =

Task  Count Duration
A      2      3
B      2      2
C      1      1

cooldown = 2

One optimal schedule

0-3   A
3-5   B
5-6   C
6-9   A
9-11  B

Answer

11
Example 2
tasks =

Task  Count Duration
A      2      5

cooldown = 3

Schedule

0-5    A

5-8    Idle

8-13   A

Answer

13
Constraints
1 <= number of distinct task types <= 10^5

1 <= execution count <= 10^5

1 <= duration <= 10^6

0 <= cooldown <= 10^6
 */

public class TaskScheduleriii {

    public long minimumExecutionTime(
            char[] taskTypes,
            int[] counts,
            int[] durations,
            int cooldown)
    {

        //max heap for remaining ones
        PriorityQueue<Task> availableTasks = new PriorityQueue<>( (a, b) -> Integer.compare(b.count, a.count) );

        for(int i=0; i<taskTypes.length; i++){
            availableTasks.offer(new Task(taskTypes[i], counts[i], durations[i]));
        }

        //min heap for cooldown waiting
        PriorityQueue<Task> cooldownTasks = new PriorityQueue<>( (a, b) -> Integer.compare(a.nextAvailable, b.nextAvailable) );

        int time = 0;

        while(!availableTasks.isEmpty() || !cooldownTasks.isEmpty()){

            //move tasks from cooldown to available queue
            while(!cooldownTasks.isEmpty() && cooldownTasks.peek().nextAvailable <= time){
                availableTasks.offer(cooldownTasks.poll());
            }

            if (availableTasks.isEmpty()) {
                time = cooldownTasks.peek().nextAvailable;
                continue;
            }

            Task task = availableTasks.poll();

            time += task.duration;

            task.count--; //reduce count

            if(task.count > 0){
                //add to cool down queue
                task.nextAvailable = time + cooldown;
                cooldownTasks.offer(task);
            }
        }

        return time;
    }

    static class Task{
        char type;
        int count;
        int duration;
        int nextAvailable;


        public Task(char type, int count, int duration) {
            this.type = type;
            this.count = count;
            this.duration = duration;
        }
    }
}
