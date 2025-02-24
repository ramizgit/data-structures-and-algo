package graph;

import java.util.*;

public class CourseScheduleII {
    public static void main(String[] args)
    {
        int[][] input = { {1, 0} };
        System.out.println(findOrder(2, input)); //[0, 1]

        int[][] input2 = { {1, 0}, {0, 1} };
        System.out.println(findOrder(2, input2)); //[]

        int[][] input3 = { {1, 0}, {2,1}, {1,2} };
        System.out.println(findOrder(3, input3)); //[]

        int[][] input4 = { {2, 0}, {1,2} };
        System.out.println(findOrder(5, input4)); //[0, 2, 1, 3, 4]

        int[][] input5 = { {3, 0}, {3,4} };
        System.out.println(findOrder(5, input5)); //[0, 1, 2, 4, 3]

        int[][] input6 = { {0, 1} };
        System.out.println(findOrder(2, input6)); //[1, 0]
    }

    private static List<Integer> findOrder(int numCourses, int[][] prerequisites)
    {
        Map<Integer, List<Integer>> graph = buildGraph(prerequisites);
        List<Integer> courseOder = new ArrayList<>();
        boolean[] loop = new boolean[1];

        for(int i=0 ;i< numCourses; i++){
            dfs(graph, i, courseOder, new HashSet<>(), loop);

            if(loop[0]){
                return new ArrayList<>();
            }
        }

        return courseOder;
    }

    private static void dfs(Map<Integer, List<Integer>> graph, int current, List<Integer> courseOder, Set<Integer> visited, boolean[] loop)
    {
        visited.add(current);

        List<Integer> neighbours = graph.getOrDefault(current, new ArrayList<>());

        for(int neighbour : neighbours){
            if(!visited.contains(neighbour)){
                dfs(graph, neighbour, courseOder, visited, loop);
            }else {
                loop[0] = true;
                return;
            }
        }

        if(!courseOder.contains(current)){
            courseOder.add(current);
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
