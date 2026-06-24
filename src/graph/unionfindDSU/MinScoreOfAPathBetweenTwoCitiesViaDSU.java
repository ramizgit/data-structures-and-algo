package consistenthashing.graph.unionfindDSU;

public class MinScoreOfAPathBetweenTwoCitiesViaDSU {

    //https://leetcode.com/problems/minimum-score-of-a-path-between-two-cities/description/

    //IMPORTANT : Not a dijkstra/bin search problem since revisiting cities/roads is allowed, its a simpel DFS/DSU problem to find min edge

    /*
    Key Observation:
    - Score of a path = minimum edge weight on that path.
    - We need the minimum possible score between city 1 and city n.
    - *** "Since revisiting cities/roads is allowed, we can always detour through any edge
      in the connected component containing city 1 before reaching city n."
    - Therefore, the answer is simply the minimum edge weight in the connected component
      containing city 1.

    Why not Dijkstra / Binary Search?
    - This is not a shortest-path or bottleneck-path problem.
    - Dijkstra is unnecessary because we are not optimizing over individual paths;
      any edge in the connected component can be included in a valid path.
    - Binary Search is unnecessary because the answer can be obtained directly by
      traversing the connected component and tracking the minimum edge weight.

    Approach:-
    Union all roads.
    Find the component containing city 1.
    Scan all roads.
    If one endpoint belongs to that component, consider its weight.
    */

    //Time : O(E+V)
    public int minScore(int n, int[][] roads)
    {
        //initialize union find - O(V)
        graph.unionfindDSU.UnionFind uf = new graph.unionfindDSU.UnionFind(n+1); //cities numbered from 1 to n

        //connect all edges
        for(int[] road : roads){ //O(E)
            int a = road[0];
            int b = road[1];

            uf.union(a, b); //Amortized O(a(V)) ~ O(1)
        }

        int sourceRoot = uf.find(1); //O(α(V)) ~ O(1)

        //get min
        int minDistance = Integer.MAX_VALUE;

        for(int[] road : roads){
            int a = road[0];
            int distance = road[2];

            //Since roads are bidirectional, if a is in the component, then b is also in the same component after all unions, so checking only a is sufficient.
            if(sourceRoot == uf.find(a)){ //O(α(V)) ~ O(1)
                minDistance = Math.min(minDistance, distance);
            }
        }

        return minDistance;
    }
}
