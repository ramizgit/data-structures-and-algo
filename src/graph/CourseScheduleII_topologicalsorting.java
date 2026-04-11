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
        //initialize graph and indegree map
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> indegree = new HashMap<>();
        for(int i=0; i<numCourses; i++){
            graph.put(i, new ArrayList<>());
            indegree.put(i, 0);
        }

        //populate graph and indegree as per prerequisites
        for(int[] p : prerequisites){
            int src = p[1];
            int des = p[0];

            graph.get(src).add(des);
            indegree.put(des, indegree.get(des) + 1);
        }

        Queue<Integer> queue = new ArrayDeque<>(); //for bfs logic
        //Queue<Integer> queue = new PriorityQueue<>(); //NOTE(***) : USE PRIORITY QUEUE IF U NEED ANSWER IN SORTED ORDER WHEREVER POSSIBLE
        List<Integer> result = new ArrayList<>();

        //collect all starting vertices with 0 indegree in the bfs queue
        for(Map.Entry<Integer, Integer> entry : indegree.entrySet()){
            if(entry.getValue() == 0){
                queue.offer(entry.getKey());
            }
        }

        //bfs logic
        while(!queue.isEmpty()){
            int node = queue.poll();
            result.add(node);

            for(int neighbour : graph.get(node)){
                indegree.put(neighbour, indegree.get(neighbour) - 1);
                if(indegree.get(neighbour) == 0){
                    queue.add(neighbour); //add to bfs queue once indegree becomes 0
                }
            }
        }

        if(result.size() != numCourses){
            return new ArrayList<>(); // return empty list as we couldn't schedule all courses, cycle detected
        }

        return result;
    }
}
