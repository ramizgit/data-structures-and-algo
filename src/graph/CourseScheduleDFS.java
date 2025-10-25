package graph;

import java.util.*;

public class CourseSchedule {

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

        int[][] input5 = { {1,0}, {2,0}, {1,2}  };
        System.out.println(canFinish(5, input5)); //true
    }

    private static boolean canFinish(int numCourses, int[][] prerequisites)
    {
        Map<Integer, List<Integer>> graph = buildGraph(prerequisites);

        for(int i=0; i<numCourses; i++){
            Set<Integer> visited = new HashSet<>();
            if(cycleExists(i,graph, visited)){
                return false;
            }
        }

        return true;
    }

    //dfs
    private static boolean cycleExists(int start, Map<Integer, List<Integer>> graph, Set<Integer> visited)
    {
        if(visited.contains(start)){
            return true; //cycle found
        }

        visited.add(start);

        //explore neighbours
        List<Integer> neighbours = graph.getOrDefault(start, new ArrayList<>());

        for(int neighbour : neighbours){
            if(cycleExists(neighbour, graph, visited)){
                return true;
            }
        }

        visited.remove(start); //backtracking

        return false;
    }

    private static Map<Integer, List<Integer>> buildGraph(int[][] prerequisites)
    {
        Map<Integer, List<Integer>> graph = new HashMap<>();

        for(int[] prerequisite : prerequisites){
            graph.computeIfAbsent(prerequisite[0], k -> new ArrayList<>()).add(prerequisite[1]);
        }

        return graph;
    }

}
