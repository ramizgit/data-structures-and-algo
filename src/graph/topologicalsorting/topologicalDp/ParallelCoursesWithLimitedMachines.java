package graph.topologicalsorting.topologicalDp;

import java.util.*;

public class ParallelCoursesWithLimitedMachines {

    //todo : practice

    public int minimumCompletionTime(int[] processingTime, int[][] dependencies, int machines)
    {
        int n = processingTime.length;

        // Build graph and calculate indegree.
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

        // Jobs eligible to start, ordered by shortest processing time.
        PriorityQueue<Integer> availableJobs = new PriorityQueue<>((a, b) ->
                {
                    if (processingTime[a] != processingTime[b]) {
                        return Integer.compare(processingTime[a], processingTime[b]);
                    }

                    return Integer.compare(a, b);
                }
                );

        // Currently running jobs, ordered by earliest finish time.
        PriorityQueue<RunningJob> runningJobs = new PriorityQueue<>((a, b) -> Integer.compare(a.finishTime, b.finishTime));

        // Initially, all jobs with no dependencies are eligible.
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                availableJobs.offer(i);
            }
        }

        int currentTime = 0;
        int completedJobs = 0;

        while (completedJobs < n) {

            // Start jobs while machines are available.
            while (!availableJobs.isEmpty() && runningJobs.size() < machines) {

                int job = availableJobs.poll();

                int finishTime = currentTime + processingTime[job];

                runningJobs.offer(
                        new RunningJob(job, finishTime));
            }

            // No running or available jobs means a cycle exists.
            if (runningJobs.isEmpty()) {
                return -1;
            }

            // Advance time to the next job completion.
            currentTime = runningJobs.peek().finishTime;

            // Process ALL jobs finishing at this same time.
            while (!runningJobs.isEmpty()
                    && runningJobs.peek().finishTime == currentTime) {

                RunningJob completed = runningJobs.poll();
                completedJobs++;

                // Make dependent jobs eligible when all dependencies finish.
                for (int neighbour : graph.get(completed.job)) {
                    indegree[neighbour]--;

                    if (indegree[neighbour] == 0) {
                        availableJobs.offer(neighbour);
                    }
                }
            }
        }

        return currentTime;
    }

    static class RunningJob {
        int job;
        int finishTime;

        RunningJob(int job, int finishTime) {
            this.job = job;
            this.finishTime = finishTime;
        }
    }
}
