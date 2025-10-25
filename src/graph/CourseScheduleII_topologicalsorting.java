package graph;

import java.util.*;

public class CourseScheduleII_topologicalsorting {
    public static void main(String[] args)
    {
        int[][] input = { {1, 0} };
        System.out.println(findOrder(2, input)); //[0, 1]

        int[][] input2 = { {1, 0}, {0, 1} };
        System.out.println(findOrder(2, input2)); //[]

        int[][] input3 = { {1, 0}, {2,1}, {1,2} };
        System.out.println(findOrder(3, input3)); //[]

        int[][] input4 = { {1, 0}, {2,1} };
        System.out.println(findOrder(5, input4)); //[0, 1, 2, 3, 4] OR [0, 3, 4, 1, 2]

        int[][] input5 = { {1,0}, {2,0}, {1,2}  };
        System.out.println(findOrder(5, input5)); //[0, 2, 1, 3, 4] OR [0, 3, 4, 2, 1]

        int[][] input6 = {{1, 0}, {2, 0}, {3, 1}, {3, 2}, {1, 3}};
        System.out.println(findOrder(4, input6)); //[]
    }

    private static List<Integer> findOrder(int numCourses, int[][] prerequisites)
    {
        //build graph and indegree
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> indegree = new HashMap<>();

        //build graph for each node
        for(int i=0; i<numCourses; i++){
            graph.put(i, new ArrayList<>());
            indegree.put(i, 0);
        }

        //update greaph and indegree as per prerequisites
        for(int[] p : prerequisites){
            int src = p[1];
            int des = p[0];

            graph.get(src).add(des);
            indegree.put(des, indegree.get(des) + 1);
        }

        //BFS
        Queue<Integer> queue = new ArrayDeque<>();
        //Queue<Integer> queue = new PriorityQueue<>(); //NOTE(***) : USE PRIORITY QUEUE IF U NEED ANSWER IN SORTED ORDER WHEREVER POSSIBLE
        List<Integer> result = new ArrayList<>();
        for(Map.Entry<Integer, Integer> entry : indegree.entrySet()){
            if(entry.getValue() == 0){
                queue.offer(entry.getKey());
            }
        }

        while(!queue.isEmpty()){
            int node = queue.poll();
            result.add(node);

            List<Integer> neighbours = graph.getOrDefault(node, new ArrayList<>());
            for(int neighbour : neighbours){
                indegree.put(neighbour, indegree.get(neighbour) - 1);
                if(indegree.get(neighbour) == 0){
                    queue.add(neighbour);
                }
            }
        }

        // if we couldn't schedule all courses, cycle detected
        if(result.size() != numCourses){
            return new ArrayList<>(); // return empty list
        }

        return result;
    }
}
