package graph.topologicalsorting;

import java.util.*;

/*
Task Scheduler with Dependencies

You are given a task scheduling system. Each task has a unique taskId and a duration.

The system supports the following operations:

ADD_TASK(taskId, duration)
ADD_DEPENDENCY(taskA, taskB)
NEXT()
COMPLETE(taskId)
Rules
ADD_TASK(taskId, duration) adds a new task to the system.
ADD_DEPENDENCY(taskA, taskB) means taskA must be completed before taskB can start.
A task is eligible to run if:
It has not been started or completed, and
All of its dependencies have been completed.
NEXT() starts and returns the eligible task with:
The smallest duration.
If multiple tasks have the same duration, the smallest taskId.
Once NEXT() returns a task, that task is considered running and will not be returned again by NEXT().
COMPLETE(taskId) marks a running task as completed. This may make other tasks eligible to run.
If no task is currently eligible, NEXT() returns -1.
Dependencies may be transitive. For example, if A → B and B → C, then C cannot start until both A and B have been completed.
Assume dependencies do not form a cycle.
Example
ADD_TASK("A", 5)
ADD_TASK("B", 2)
ADD_TASK("C", 3)
ADD_TASK("D", 4)

ADD_DEPENDENCY("A", "C")
ADD_DEPENDENCY("B", "C")
ADD_DEPENDENCY("C", "D")

NEXT()       → "B"
COMPLETE("B")

NEXT()       → "A"
COMPLETE("A")

NEXT()       → "C"
COMPLETE("C")

NEXT()       → "D"
COMPLETE("D")

NEXT()       → -1

Goal: Design the data structures and implement all four operations efficiently.
 */

public class TaskSchedulerWithDependencyDynamicTopo {

    //todo : practice

    private Map<String, Task> tasks;
    private Map<String, List<String>> graph;
    private Map<String, Integer> indegree;

    // Eligible tasks: shortest duration first, then smallest taskId
    private PriorityQueue<Task> eligibleTasks;

    public TaskSchedulerWithDependencyDynamicTopo() {
        tasks = new HashMap<>();
        graph = new HashMap<>();
        indegree = new HashMap<>();

        eligibleTasks = new PriorityQueue<>(
                (a, b) -> {
                    if (a.duration != b.duration) {
                        return Integer.compare(a.duration, b.duration);
                    }
                    return a.id.compareTo(b.id);
                }
        );
    }

    public void addTask(String taskId, int duration) {
        Task task = new Task(taskId, duration);

        tasks.put(taskId, task);
        graph.putIfAbsent(taskId, new ArrayList<>());
        indegree.putIfAbsent(taskId, 0);

        // No dependencies currently, so task is eligible
        if (indegree.get(taskId) == 0) {
            eligibleTasks.offer(task);
        }
    }

    // taskA must complete before taskB
    public void addDependency(String taskA, String taskB) {
        graph.get(taskA).add(taskB);
        indegree.put(taskB, indegree.get(taskB) + 1);

        // If B was already eligible, remove it.
        // Its dependency is no longer satisfied.
        eligibleTasks.remove(tasks.get(taskB));
    }

    // Starts the best eligible task
    public String next() {
        if (eligibleTasks.isEmpty()) {
            return "-1";
        }

        Task task = eligibleTasks.poll();
        task.running = true;

        return task.id;
    }

    public void complete(String taskId) {
        Task task = tasks.get(taskId);

        if (task == null || !task.running) {
            return;
        }

        task.running = false;
        task.completed = true;

        // Completing this task may unlock dependent tasks
        for (String dependent : graph.get(taskId)) {
            indegree.put(dependent, indegree.get(dependent) - 1);

            if (indegree.get(dependent) == 0) {
                eligibleTasks.offer(tasks.get(dependent));
            }
        }
    }

    static class Task {
        String id;
        int duration;
        boolean running;
        boolean completed;

        Task(String id, int duration) {
            this.id = id;
            this.duration = duration;
        }
    }
}
