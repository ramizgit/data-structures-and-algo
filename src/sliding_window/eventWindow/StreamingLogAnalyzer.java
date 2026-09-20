package sliding_window.eventWindow;

import java.util.*;

/*
Problem: Active Users in a Sliding Time Window

You are given a stream of log entries sorted in non-decreasing order of timestamp.

Each log entry is represented as:

logs[i] = {timestamp, userId}

where:

timestamp is an integer representing when the request occurred.
userId identifies the user who made the request.

You are also given an integer W, representing the size of the sliding time window.

For each log entry at timestamp t, a user is considered active if they have made at least one request in the inclusive time range:

[t - W + 1, t]

Return an array where answer[i] is the number of distinct active users after processing logs[i].

Example 1
logs = [
    [1, "A"],
    [2, "B"],
    [3, "A"],
    [7, "C"]
]

W = 5

Output:

[1, 2, 2, 2]

Explanation:

t = 1 → window [ -3, 1] → {A}       → 1
t = 2 → window [ -2, 2] → {A,B}     → 2
t = 3 → window [ -1, 3] → {A,B}     → 2
t = 7 → window [  3, 7] → {A,C}     → 2

Notice that A's request at t=1 expired, but A remains active because of the request at t=3.

Constraints
1 <= logs.length <= 10^7
1 <= W <= 10^9

logs[i][0] <= logs[i+1][0]

1 <= logs[i][0] <= 10^9

userId consists of lowercase English letters and digits.
Method Signature
public int[] countActiveUsers(int[][] logs, int W)
 */

public class StreamingLogAnalyzer {

    //hint : sliding window + frequency map

    //Time: O(N) amortized
    //Space: O(N) worst case
    //N = number of log entries.
    public int[] countActiveUsers(String[][] logs, int W)
    {
        //input validation
        if(logs == null || logs.length ==0){
            return new int[0];
        }

        int n = logs.length;
        int[] result = new int[n];

        Deque<LogEntry> activeRequestQueue = new ArrayDeque<>();
        Map<String, Integer> userFreq = new HashMap<>();

        for(int i=0; i<logs.length; i++){
            String[] log = logs[i];
            int timestamp = Integer.parseInt(log[0]);
            String user = log[1];

            //first expire out of window events
            while(!activeRequestQueue.isEmpty() && activeRequestQueue.peekFirst().timestamp <= (timestamp - W)){
                //remove stale entries from front
                LogEntry staleEntry = activeRequestQueue.pollFirst();

                //reduce freq in map
                userFreq.put(staleEntry.user, userFreq.get(staleEntry.user) - 1);

                //remove from map if freq is 0
                if(userFreq.get(staleEntry.user) == 0){
                    userFreq.remove(staleEntry.user);
                }
            }

            //add current event at the end
            activeRequestQueue.offerLast(new LogEntry(timestamp, user));
            userFreq.put(user, userFreq.getOrDefault(user, 0) + 1);

            result[i] = userFreq.size();
        }

        return result;
    }

    //todo : incomplete code : integrate below code if top k is asked
    private TreeMap<Integer, Set<String>> freqToUsers;

    public List<String> getTopKUsers(int k) {

        List<String> result = new ArrayList<>();

        // Highest frequency first
        for (Map.Entry<Integer, Set<String>> entry : freqToUsers.descendingMap().entrySet()) {

            for (String user : entry.getValue()) {

                result.add(user);

                if (result.size() == k) {
                    return result;
                }
            }
        }

        return result;
    }

    static class LogEntry{
        int timestamp;
        String user;

        public LogEntry(int timestamp, String user) {
            this.timestamp = timestamp;
            this.user = user;
        }
    }
}
