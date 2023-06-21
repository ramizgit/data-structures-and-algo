package graph;

import java.util.*;

public class TopologicalSorting {

    public static void main(String[] args)
    {

        Map<Integer, List<Integer>> graph = new HashMap<>();

        graph.put(1, Arrays.asList(2,3));
        graph.put(2, Arrays.asList(3));

        System.out.println(topoligicalSort(1, graph)); //1,2,3

        Map<Integer, List<Integer>> graph2 = new HashMap<>();

        graph2.put(1, Arrays.asList(2,3));
        graph2.put(3, Arrays.asList(2));

        System.out.println(topoligicalSort(1, graph2)); //1,3,2
    }

    private static List<Integer> topoligicalSort(int start, Map<Integer, List<Integer>> graph)
    {
        List<Integer> answer = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();

        dfs(start, graph, answer, visited);

        Collections.reverse(answer);

        return answer;
    }

    private static void dfs(int current, Map<Integer, List<Integer>> graph, List<Integer> answer, Set<Integer> visited)
    {
        visited.add(current);

        List<Integer> neighbours = graph.getOrDefault(current, new ArrayList<>());

        for(int neighbour : neighbours){
            if(!visited.contains(neighbour)){
                dfs(neighbour, graph, answer, visited);
            }
        }

        answer.add(current);
    }
}
