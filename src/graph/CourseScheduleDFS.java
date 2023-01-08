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

    public static boolean canFinish(int numCourses, int[][] prerequisites)
    {
        Map<Integer, List<Integer>> graph = buildGraph(prerequisites);

        //now do dfs and find if any cycle
        for(int i=0; i<numCourses; i++){
            if(!dfs(graph, i, new HashSet<>())){
                return false;
            }
        }
        return true;
    }

    public static boolean dfs(Map<Integer, List<Integer>> graph, int node, Set<Integer> visitedNodes)
    {
        boolean ret = true;

        List<Integer> adjNodes = graph.getOrDefault(node, new ArrayList<>());

        for(int n : adjNodes){
            if(visitedNodes.contains(n)){
                return false;
            }else {
                visitedNodes.add(node);
                ret = dfs(graph, n, visitedNodes);
            }
        }

        return ret;
    }

    public static Map<Integer, List<Integer>> buildGraph(int[][] prerequisites)
    {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int[] prerequisite : prerequisites){
            List<Integer> list = graph.getOrDefault(prerequisite[0], new ArrayList<>());
            list.add(prerequisite[1]);
            graph.put(prerequisite[0], list);
        }
        return graph;
    }
}
