package graph.unionfindDSU;

public class ProcessRestrictedFriendRequests {

    //https://leetcode.com/problems/process-restricted-friend-requests/

    /*
    Approach:

    1. Initialize DSU with all users in separate groups.
    2. Process each friend request one by one.
    3. If both users are already in the same group, accept the request.
    4. Otherwise, check every restriction:
       - If merging the two groups would place a restricted pair in the same group,
         reject the request.
    5. If no restriction is violated, union the two groups and accept the request.

    Time : O(N + Q * R)
    Space: O(N)

    where:
    N = number of users
    Q = number of requests
    R = number of restrictions
    */
    public boolean[] friendRequests(int n, int[][] restrictions, int[][] requests)
    {
        UnionFind uf = new UnionFind(n); //time : O(n)

        boolean[] answer = new boolean[requests.length];

        for(int i=0; i<requests.length; i++){ //time : O(requests length)

            int u = requests[i][0];
            int v = requests[i][1];

            int rootU = uf.find(u);
            int rootV = uf.find(v);

            if(rootU == rootV){
                //already friends, just add to answer and continue with next request
                answer[i] = true;
                continue;
            }

            boolean merge = true;

            //check all restrictions before merging
            for(int[] restriction : restrictions){ //time : O(restrictions length)

                int a = restriction[0];
                int b = restriction[1];

                int rootA = uf.find(a);
                int rootB = uf.find(b);

                //check if merging u and v will also merge a and b?
                if ((rootA == rootU && rootB == rootV) ||
                        (rootA == rootV && rootB == rootU)) {
                    merge = false;
                    break;
                }
            }

            if(merge){
                uf.union(u, v);
                answer[i] = true;
            }
        }

        return answer;
    }
}
