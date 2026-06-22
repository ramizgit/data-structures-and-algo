package consistenthashing.graph.mst;

import java.util.*;

public class FindCriticalPseudoCriticalEdgesInMST {

    //https://leetcode.com/problems/find-critical-and-pseudo-critical-edges-in-minimum-spanning-tree/description/

    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges)
    {
        /*
        APPROACH (Kruskal + Force / Skip Edge)

        1. Compute the cost of the original MST using Kruskal.

        2. For every edge:
           a) Skip the edge and run Kruskal.
              - If the MST cost increases (or MST cannot be formed),
                the edge is Critical because it is required in every MST.

           b) Otherwise, force the edge into the MST first
              (union its endpoints, add its cost), then continue
              normal Kruskal.
              - If the final MST cost remains unchanged,
                the edge is Pseudo-Critical because it can belong
                to at least one optimal MST.

        3. Return the lists of Critical and Pseudo-Critical edges.
        */

        List<Edge> edgeList = new ArrayList<>();
        for(int i=0; i<edges.length; i++){
            int u = edges[i][0];
            int v = edges[i][1];
            int w = edges[i][2];

            edgeList.add(new Edge(u, v, w, i));
        }

        //sort asc order of edge weight
        edgeList.sort( (a, b) -> Integer.compare(a.weight, b.weight) );

        int normalCost = kruskal(n, edgeList, -1, -1);

        List<Integer> critical = new ArrayList<>();
        List<Integer> pseudo = new ArrayList<>();

        for(int i=0; i<edgeList.size(); i++){
            //skip edge i to check critical
            int skipCost = kruskal(n, edgeList, i, -1);
            if( skipCost == -1 || skipCost > normalCost){
                critical.add(edgeList.get(i).index);
            }else{ //force edge i to check psedudo
                int forceCost = kruskal(n, edgeList, -1, i);
                if(forceCost == normalCost){
                    pseudo.add(edgeList.get(i).index);
                }
            }
        }

        List<List<Integer>> result = new ArrayList<>();
        result.add(critical);
        result.add(pseudo);

        return result;
    }

    private int kruskal(int n, List<Edge> edgeList, int skipEdgeIdx, int forceEdgeIdx)
    {
        graph.unionfindDSU.UnionFind uf = new graph.unionfindDSU.UnionFind(n);

        int totalCost = 0;
        int edgeCount = 0;

        //force logic
        if(forceEdgeIdx != -1){
            Edge forceEdge = edgeList.get(forceEdgeIdx);

            if (uf.union(forceEdge.u, forceEdge.v)) {
                totalCost += forceEdge.weight;
                edgeCount++;
            }
        }

        for(int i=0; i<edgeList.size(); i++){

            if (i == skipEdgeIdx || i == forceEdgeIdx) {
                continue;
            }

            Edge edge = edgeList.get(i);

            if(uf.union(edge.u, edge.v)){
                //add to MST
                totalCost += edge.weight;
                edgeCount++;

                if(edgeCount == n-1){
                    return totalCost;
                }
            }
        }

        return -1;
    }

    static class Edge{
        int u;
        int v;
        int weight;
        int index;

        public Edge(int u, int v, int weight, int index) {
            this.u = u;
            this.v = v;
            this.weight = weight;
            this.index = index;
        }
    }
}
