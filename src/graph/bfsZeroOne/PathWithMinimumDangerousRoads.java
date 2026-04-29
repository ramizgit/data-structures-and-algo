package graph.bfsZeroOne;

import java.util.*;

/*
GOOGLE
There are N cities labeled from 1 to N
There are M bidirectional roads
Each road connects two cities and has an associated risk value w

You start at city 1 and want to reach city N.

You are allowed to choose a risk threshold X.

Any road with w ≤ X is considered safe
Any road with w > X is considered dangerous

However, due to safety limitations, you can travel through at most K dangerous roads.

Find the minimum possible value of X such that it is possible to travel from city 1 to city N,
while using at most K dangerous roads.

If no such path exists, return -1.

TEST CASE:-
N = 4, M = 4, K = 1
Edges:
1 - 2 (w=3)
2 - 4 (w=5)
1 - 3 (w=10)
3 - 4 (w=1)

X = 3
Safe: (1-2), (3-4)
Dangerous: (2-4), (1-3)
1 → 2 → 4
dangerous edges = 1 (allowed)
Hence answer is 3
 */

/*
APPROACH:-
binary search on X → convert edges to 0/1 → run 0-1 BFS minimizing 1’s

For a fixed threshold X:
If w ≤ X → edge cost = 0 (safe)
If w > X → edge cost = 1 (dangerous)
Now problem becomes:
“What is the minimum number of dangerous edges needed to go from 1 → N?”
👉 That is a pure 0–1 BFS problem
 */

//GOOOOOGLE
public class PathWithMinimumDangerousRoads {

    public int minimumRiskThreshold(int n, int[][] edges, int k)
    {
        //initialize graph
        Map<Integer, List<Edge>> graph = new HashMap<>();
        for(int i=1; i<=n; i++){
            graph.put(i, new ArrayList<>());
        }

        int maxWeight = 0; //to be used by binary search high limit

        //populate edges as per the input
        for(int[] edge : edges){
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            //since roads are bidirectional, add both u->v and v->u
            graph.get(u).add(new Edge(v,w));
            graph.get(v).add(new Edge(u,w));

            maxWeight = Math.max(maxWeight, w);
        }

        /*
        Step 1: Binary search on X
        low = 0
        high = max edge weight

        For each mid (X)
        Run 0–1 BFS:
        Use deque
        If edge cost = 0 → push front
        If edge cost = 1 → push back
         */

        int low = 0;
        int high = maxWeight;
        int X = -1;

        while(low <= high){
            int mid = low + (high - low)/2;

            if(canTravel(graph, n, k, mid)){
                X = mid; //possible answer
                high = mid - 1; //try lower
            }else{
                low = mid + 1; //try higher
            }
        }

        return X;
    }

    public boolean canTravel(Map<Integer, List<Edge>> graph, int n, int k, int threshold)
    {
        //0-1 BFS from 1 to N
        Deque<State> deque = new ArrayDeque<>();
        deque.offerFirst(new State(1, 0));

        int[] cost = new int[n+1];
        Arrays.fill(cost, Integer.MAX_VALUE);
        cost[1] = 0; //starting point cost is 0

        while(!deque.isEmpty()){
            State curr = deque.pollFirst();

            //ignore stale/outdated states
            if (curr.cost > cost[curr.node]) {
                continue;
            }

            //early exit
            if(curr.node == n){
                return curr.cost <= k;
            }

            //explore neighbours
            for(Edge neighbour : graph.get(curr.node)){
                int newCost = curr.cost + (neighbour.w > threshold ? 1 : 0);
                if (newCost < cost[neighbour.v]) {
                    //relaxation
                    cost[neighbour.v] = newCost;
                    if (neighbour.w <= threshold) {
                        deque.offerFirst(new State(neighbour.v,  newCost)); //safe road, cost=0
                    } else {
                        deque.offerLast(new State(neighbour.v, newCost)); //dangerous road, cost=1
                    }
                }
            }
        }

        return cost[n] <= k;
    }

    class Edge{
        int v;
        int w;

        public Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    class State{
        int node;
        int cost;

        public State(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
    }
}
