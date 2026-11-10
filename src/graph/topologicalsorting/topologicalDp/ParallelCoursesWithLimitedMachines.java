package graph.topologicalsorting.topologicalDp;

import java.util.*;

public class ParallelCoursesWithLimitedMachines {

    //todo : practice

    public int minimumCompletionTime(int[] processingTime, int[][] dependencies, int machines) {

        int n = processingTime.length;

        // Build graph.
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        int[] indegree = new int[n];

        for (int[] dependency : dependencies) {
            int from = dependency[0];
            int to = dependency[1];

            graph.get(from).add(to);
            indegree[to]++;
        }

        // Jobs whose dependencies are completed.
        Queue<Integer> topoQueue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                topoQueue.offer(i);
            }
        }

        // Pool of available machines.
        Queue<Integer> availableMachines = new LinkedList<>();

        for (int i = 0; i < machines; i++) {
            availableMachines.offer(i);
        }

        // Pool of busy machines, ordered by finish time.
        PriorityQueue<Machine> busyMachines = new PriorityQueue<>(Comparator.comparingInt(m -> m.finishTime));

        int currentTime = 0;
        int completedJobs = 0;

        while (completedJobs < n) {

            // Assign eligible jobs to available machines.
            while (!topoQueue.isEmpty() && !availableMachines.isEmpty()) {
                int job = topoQueue.poll();
                int machineId = availableMachines.poll();
                int finishTime = currentTime + processingTime[job];
                busyMachines.offer(new Machine(machineId, finishTime, job));
            }

            // No running jobs and no eligible jobs means a cycle exists.
            if (busyMachines.isEmpty()) {
                return -1;
            }

            // Advance time to the next machine completion.
            currentTime = busyMachines.peek().finishTime;

            //free machines whose jobs have completed
            while (!busyMachines.isEmpty() && busyMachines.peek().finishTime == currentTime) {

                Machine machine = busyMachines.poll();

                completedJobs++;

                // Return machine to the available pool.
                availableMachines.offer(machine.machineId);

                // Unlock dependent jobs.
                for (int neighbour : graph.get(machine.runningJob)) {
                    indegree[neighbour]--;

                    if (indegree[neighbour] == 0) {
                        topoQueue.offer(neighbour);
                    }
                }
            }
        }

        return currentTime;
    }

    static class Machine {
        int machineId;
        int finishTime;
        int runningJob;

        Machine(int machineId, int finishTime, int runningJob) {
            this.machineId = machineId;
            this.finishTime = finishTime;
            this.runningJob = runningJob;
        }
    }
}