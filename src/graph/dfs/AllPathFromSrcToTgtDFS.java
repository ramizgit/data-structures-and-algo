package graph.dfs;

import java.util.ArrayList;
import java.util.List;

public class AllPathFromSrcToTgtDFS {

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
            //explore neighbours
            for(int neighbor : graph[current]){
                dfs(neighbor, target, graph, path, result);
            }
        }

        path.removeLast(); //backtracking
    }
}
