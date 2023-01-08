package graph;

import java.util.*;

public class EvaluateDivisionLeetcode {
    public static void main(String[] args)
    {
        /*
        Input: equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]
         */

        List<List<String>> equations = Arrays.asList(Arrays.asList("a", "b"), Arrays.asList("b", "c"));

        double[] values = new double[2];
        values[0] = 2.0;
        values[1] = 3.0;

        List<List<String>> queries = Arrays.asList(Arrays.asList("a", "c"), Arrays.asList("b", "a"), Arrays.asList("a", "e"),
                Arrays.asList("a", "a"), Arrays.asList("x", "x"));

        double[] output = calcEquation(equations, values, queries);
        for(double d : output){
            System.out.print(d + " ");
        }

        System.out.println();
        /*
        Input: equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
Output: [3.75000,0.40000,5.00000,0.20000]
         */

        List<List<String>> equations2 = Arrays.asList(Arrays.asList("a", "b"), Arrays.asList("b", "c"), Arrays.asList("bc", "cd"));

        double[] values2 = new double[3];
        values2[0] = 1.5;
        values2[1] = 2.5;
        values2[2] = 5.0;

        List<List<String>> queries2 = Arrays.asList(Arrays.asList("a", "c"), Arrays.asList("c", "b"), Arrays.asList("bc", "cd"),
                Arrays.asList("cd", "bc"));

        double[] output2 = calcEquation(equations2, values2, queries2);
        for(double d : output2){
            System.out.print(d + " ");
        }
    }

    public static double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries)
    {
        double[] output = new double[queries.size()];

        //build graph for input equations in a map
        Map<String, List<GNode>> graph = buildGraph(equations, values);

        //dfs
        for(int i=0; i<queries.size(); i++){
            List<String> query = queries.get(i);
            String source = query.get(0);
            String destination = query.get(1);

            if(!graph.containsKey(source) || !graph.containsKey(destination)){
                output[i] = -1.00000;
            }else {
                //dfs
                output[i] = dfs(graph, source, destination, new HashSet<String>(), 1);
            }
        }

        return output;
    }

    public static double dfs(Map<String, List<GNode>> graph, String source, String destination, Set<String> visited, double ans)
    {
        if(source.equals(destination)){
            return ans;
        }

        visited.add(source);

        List<GNode> nodes = graph.get(source);
        for(GNode node : nodes){
            if(!visited.contains(node.vertex)){
                return dfs(graph, node.vertex, destination, visited, ans * node.weight);
            }
        }

        return -1.0;
    }

    public static Map<String, List<GNode>> buildGraph(List<List<String>> equations, double[] values)
    {
        Map<String, List<GNode>> graph = new HashMap();
        for(int i=0; i<equations.size(); i++){
            List<String> equation = equations.get(i);
            String source = equation.get(0);
            String destination = equation.get(1);
            double value = values[i];

            List<GNode> nodes = graph.getOrDefault(source, new ArrayList<>());
            nodes.add(new GNode(destination, value));
            graph.put(source, nodes);

            //store reverse equation as well
            List<GNode> revNodes = graph.getOrDefault(destination, new ArrayList<>());
            revNodes.add(new GNode(source, (1/value)));
            graph.put(destination, revNodes);
        }

        return graph;
    }
}

class GNode{
    String vertex;
    double weight;

    public GNode(String vertex, double weight) {
        this.vertex = vertex;
        this.weight = weight;
    }
}
