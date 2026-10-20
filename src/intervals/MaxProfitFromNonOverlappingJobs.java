package intervals;

import java.util.*;

/*
Pattern: Intervals + Binary Search + Dynamic Programming

You are given n jobs. Each job is represented by three integers:

jobs[i] = [startTime, endTime, profit]

You can choose any subset of jobs, but the selected jobs must not overlap. A job ending at time t and another starting at time t are compatible.

Return the maximum total profit you can earn.
Example 1
jobs = new int[][] {
    {1, 3, 50},
    {2, 5, 20},
    {3, 10, 100},
    {6, 9, 70}
};

Output:
120

Explanation: Choose jobs [1, 3, 50] and [3, 10, 100], earning 150.

Constraints
- 1 <= jobs.length <= 100000
- 0 <= startTime < endTime <= 10^9
- 1 <= profit <= 10^6
 */

public class MaxProfitFromNonOverlappingJobs {

    //todo : practice

    public long maxProfit(int[][] jobs) {

        // Input validation
        if (jobs == null || jobs.length == 0) {
            return 0;
        }

        int n = jobs.length;

        // Sort jobs by end time in ascending order
        /*
        why sort by end time?
        We sort by end time so that when processing a job, all earlier jobs that could be compatible with it are already processed,
        allowing us to use binary search and reuse their optimal DP results.
         */
        Arrays.sort(jobs, (a, b) -> Integer.compare(a[1], b[1]));

        long[] dp = new long[n + 1]; //dp[i] = maximum profit using the first i jobs. dp[0] = 0, meaning no jobs selected.

        for (int i = 1; i <= n; i++) {

            int[] currJob = jobs[i - 1];

            int startTime = currJob[0];
            int profit = currJob[2];

            // Find the last job ending <= current job's start time
            int lastCompatible = findLastCompatibleJob(jobs, i - 2, startTime); //why i-1? Because i - 1 is the current job's index. Including it would allow the binary search to consider the current job itself as its own predecessor.

            dp[i] = Math.max(dp[i - 1], // Option 1: Skip the current job
                    profit + dp[lastCompatible + 1] // Option 2: Take the current job
            );
        }

        return dp[n];
    }

    /*
     * Finds the last job in jobs[0..high] whose end time <= startTime.
     *
     * Returns -1 if no compatible job exists.
     *
     * Time: O(log n)
     * Space: O(1)
     */
    private int findLastCompatibleJob(
            int[][] jobs, int high, int startTime) {

        int low = 0;
        int answer = -1;

        while (low <= high) {

            int mid = low + (high - low) / 2;

            if (jobs[mid][1] <= startTime) {
                answer = mid; //possible answer
                low = mid + 1; // Look for a later compatible job
            } else {
                high = mid - 1; // Job ends too late
            }
        }

        return answer;
    }
}
