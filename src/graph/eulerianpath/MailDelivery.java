package graph.eulerianpath;

import java.util.*;

/*
Your task is to deliver mail to the inhabitants of a city. For this reason, you want to find a route whose starting and ending point are the post office, and that goes through every street exactly once.
Input
The first input line has two integers n and m: the number of crossings and streets. The crossings are numbered 1,\,2,\ldots,\,n, and the post office is located at crossing 1.
After that, there are m lines describing the streets. Each line has two integers a and b: there is a street between crossings a and b. All streets are two-way streets.
Every street is between two different crossings, and there is at most one street between two crossings.
Output
Print all the crossings on the route in the order you will visit them. You can print any valid solution.
If there are no solutions, print "IMPOSSIBLE".
Constraints
2\leq n\leq 10^5
1\leq m\leq 2 \cdot 10^5
1\leq a,\,b\leq n
Example
Input:
6 8
1 2
1 3
2 3
2 4
2 6
3 5
3 6
4 5

Output:
1 2 6 3 2 4 5 3 1
 */

public class MailDelivery {

    //https://cses.fi/problemset/task/1691

    public List<Integer> findEulerCircuit(int n, int[][] routes)
    {
        // 1. Build graph
        // 2. Check Eulerian circuit condition (all degrees even)
        // 3. DFS from node 1
        // 4. Reverse path
        // 5. Verify all edges were used (path.size() == m + 1)
        // 6. Return path

        //build graph as adjacency list
        Map<Integer, List<Edge>> graph = new HashMap<>();

        for(int i=1; i<=n; i++){
            graph.put(i, new ArrayList<>());
        }

        int id = 0;

        //populate edges
        for(int[] route : routes){
            int u = route[0];
            int v = route[1];

            //bi-directional edges
            graph.get(u).add(new Edge(v, id));
            graph.get(v).add(new Edge(u, id));

            id++;
        }

        //euler circuit check - confirm all degrees are even
        for (int i = 1; i <= n; i++) {
            if (graph.get(i).size() % 2 != 0) {
                return Collections.emptyList(); // IMPOSSIBLE
            }
        }

        boolean[] edgeUsed = new boolean[routes.length];
        List<Integer> path = new ArrayList<>();

        dfs(1, graph, edgeUsed, path);

        Collections.reverse(path);

        //check if every edge has been visited or not
        if (path.size() != routes.length + 1) {
            return Collections.emptyList(); // IMPOSSIBLE
        }

        return path;
    }

    //post oder dfs - Hierholzer algo
    private void dfs(int node, Map<Integer, List<Edge>> graph, boolean[] edgeUsed, List<Integer> path)
    {
        //explore neighbour
        for(Edge neighbour : graph.get(node)){
            if(!edgeUsed[neighbour.id]){
                edgeUsed[neighbour.id] = true;
                dfs(neighbour.node, graph, edgeUsed, path);
            }
        }

        path.add(node);
    }

    static class Edge{
        int node;
        int id;

        public Edge(int node, int id) {
            this.node = node;
            this.id = id;
        }
    }
}
