package google;

import java.util.*;

public class FindServersThatHandledMostNumberOfRequests {

    //https://leetcode.com/problems/find-servers-that-handled-most-number-of-requests/

    public List<Integer> busiestServers(int k, int[] arrival, int[] load)
    {
        //available servers
        TreeSet<Integer> freeServers = new TreeSet<>();
        for(int i=0; i<k; i++){
            freeServers.add(i);
        }

        //busy servers - minheap {serverId, endTime}
        PriorityQueue<int[]> busyServers = new PriorityQueue<>( (a, b) -> a[1] - b[1] );
        int[] requests = new int[k]; //keep track of num of requests by each server
        int maxRequest = 0;

        for(int i=0; i<arrival.length; i++){
            int arrivalTime = arrival[i];
            int runTime = load[i];

            // free completed servers
            while (!busyServers.isEmpty() && busyServers.peek()[1] <= arrivalTime) {
                int freedServer = busyServers.poll()[0];
                freeServers.add(freedServer);
            }

            // no free server -> drop request
            if (freeServers.isEmpty()) {
                continue;
            }

            // find next available server starting from i % k
            Integer availableServer = freeServers.ceiling(i % k);

            // circular wrap
            if (availableServer == null) {
                availableServer = freeServers.first();
            }

            // mark server busy -> remove from available server and add to busy minheap
            freeServers.remove(availableServer);
            busyServers.add(new int[]{availableServer, arrivalTime + runTime});

            requests[availableServer]++; //increment count of request handled by the server
            maxRequest = Math.max(maxRequest, requests[availableServer]); //track max request being handled
        }

        //iterate requests and collect result
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            if (requests[i] == maxRequest) {
                result.add(i);
            }
        }

        return result;
    }
}
