package graph;

import java.util.*;

public class CourseScheduleDFS {
    public static void main(String[] args)
    {
        int[][] input = { {1, 0} };
        System.out.println(canFinish(2, input)); //true

        int[][] input2 = { {1, 0}, {0, 1} };
        System.out.println(canFinish(2, input2)); //false

        int[][] input3 = { {1, 0}, {2,1}, {1,2} };
        System.out.println(canFinish(3, input3)); //false

        int[][] input4 = { {1, 0}, {2,1} };
        System.out.println(canFinish(5, input4)); //true
    }

    private static boolean canFinish(int numCourses, int[][] prerequisites)
    {
        Map<Integer, List<Integer>> graph = buildGraph(prerequisites);

        for(int i=0; i<numCourses; i++){
            boolean[] ret = new boolean[1];
            ret[0] = true;
            dfs(i, graph, new HashSet<>(), ret);
            if(!ret[0]){
                return false;
            }
        }
        return true;
    }

    private static void dfs(int num, Map<Integer, List<Integer>> graph, Set<Integer> visited, boolean[] ret)
    {
        visited.add(num);
        List<Integer> neighbours = graph.getOrDefault(num, new ArrayList<>());
        for(int neighbour : neighbours){
            if(visited.contains(neighbour)){
                ret[0] = false;
            }else {
                visited.add(neighbour);
                dfs(neighbour, graph, visited, ret);
            }
        }
    }

   private static Map<Integer, List<Integer>> buildGraph(int[][] prerequisites)
    {
        Map<Integer, List<Integer>> graph = new HashMap<>();

        for(int[] prerequisite : prerequisites){
            graph.computeIfAbsent(prerequisite[0], key -> new ArrayList<>()).add(prerequisite[1]);
        }

        return graph;
    }
}
