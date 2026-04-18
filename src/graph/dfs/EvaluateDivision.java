package graph.dfs;

import java.util.*;

public class EvaluateDivision {

    //https://leetcode.com/problems/evaluate-division/description/

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries)
    {
        //initialize graph
        Map<String, List<Edge>> graph = new HashMap<>();

        //populate graph
        for(int i=0; i<equations.size(); i++){
            String u = equations.get(i).get(0);
            String v = equations.get(i).get(1);
            double val = values[i];

            graph.computeIfAbsent(u, key -> new ArrayList<>()).add(new Edge(v, val));
            graph.computeIfAbsent(v, key -> new ArrayList<>()).add(new Edge(u, 1.0 / val));
        }

        double[] result = new double[queries.size()];
        int idx = 0;

        for(List<String> query : queries){
            String src = query.get(0);
            String dest = query.get(1);

            //if missing variable handling
            if(!graph.containsKey(src) || !graph.containsKey(dest)){
                result[idx++] = -1.0;
                continue;
            }

            //edge case if src == dest
            if (src.equals(dest)) {
                result[idx++] = 1.0;
                continue;
            }

            Set<String> visited = new HashSet<>();
            result[idx++] = evaluateDfs(src, dest, graph, 1.0, visited);
        }

        return result;
    }

    public double evaluateDfs(String src, String dest, Map<String, List<Edge>> graph, double product, Set<String> visited)
    {
        if(src.equals(dest)){
            return product;
        }

        visited.add(src);

        List<Edge> neighbours = graph.get(src);
        for(Edge neighbour : neighbours){
            if(!visited.contains(neighbour.v)){
                double result = evaluateDfs(neighbour.v, dest, graph, product * neighbour.value, visited);
                if (result != -1.0) {
                    return result; // early return, stop dfs once answer is found (only one path needed)
                }
            }
        }

        return -1.0;
    }

    class Edge{
        String v;
        double value;

        public Edge(String v, double value) {
            this.v = v;
            this.value = value;
        }
    }
}
