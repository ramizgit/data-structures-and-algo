package graph;

import java.util.ArrayList;
import java.util.List;

public class AllPathFromSrcToTgtDFS {
    public static void main(String[] args)
    {
        int[][] graph = { {1,2},{3},{3},{} };
        System.out.println(allPathsSourceTarget(graph)); //Output: [[0,1,3],[0,2,3]]
        
        int[][] graph2 = {{4,3,1},{3,2,4},{3},{4},{}};
        System.out.println(allPathsSourceTarget(graph2)); //Output: [[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]
    }

    public static List<List<Integer>> allPathsSourceTarget(int[][] graph)
    {
        List<Integer> path = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();

        dfs(0, graph.length-1, graph, path, result);

        return result;
    }

    public static void dfs(int current, int target, int[][] graph, List<Integer> path, List<List<Integer>> result)
    {
        path.add(current);

        if(current == target){
            result.add(new ArrayList<>(path));
        }else {
            int[] neighbors = graph[current];
            for(int neighbor : neighbors){
                dfs(neighbor, target, graph, path, result);
            }
        }

        path.remove(path.size()-1); //backtracking
    }
}
