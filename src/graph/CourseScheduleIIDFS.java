package graph;

import java.util.*;

public class CourseScheduleIIDFS {
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

    public static List<Integer> findOrder(int numCourses, int[][] prerequisites)
    {
        Map<Integer, List<Integer>> graph = buildGraph(prerequisites);
        List<Integer> answer = new ArrayList<>();

        //now do dfs and find if any cycle
        for(int i=0; i<numCourses; i++){
            if(!dfs(graph, i, new HashSet<>(), answer)){
                return new ArrayList<>();
            }
        }
        return answer;
    }

    public static boolean dfs(Map<Integer, List<Integer>> graph, int node, Set<Integer> visitedNodes, List<Integer> answer)
    {
        visitedNodes.add(node);
        boolean ret = true;

        List<Integer> adjNodes = graph.getOrDefault(node, new ArrayList<>());

        for(int n : adjNodes){
            if(visitedNodes.contains(n)){
                return false;
            }else {
                ret = dfs(graph, n, visitedNodes, answer);
            }
        }

        //Add the node to the answer order
        if(!answer.contains(node)){
            answer.add(node);
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
