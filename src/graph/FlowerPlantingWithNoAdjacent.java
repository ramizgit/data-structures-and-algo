package graph;

import java.util.*;

public class FlowerPlantingWithNoAdjacent {

    public static void main(String[] args)
    {
        int[] ans = gardenNoAdj(3, new int[][]{{1, 2}, {2, 3}, {3, 1}});
        printarr(ans);

        int[] ans2 = gardenNoAdj(4, new int[][]{{1, 2}, {3, 4}});
        printarr(ans2);

        int[] ans3 = gardenNoAdj(4, new int[][]{{1,2},{2,3},{3,4},{4,1},{1,3},{2,4}});
        printarr(ans3);

        int[] ans4 = gardenNoAdj(5, new int[][]{});
        printarr(ans4);

        int[] ans5 = gardenNoAdj(6, new int[][]{{1,2},{1,3},{1,4},{4,5},{4,6}});
        printarr(ans5);
    }

    private static int[] gardenNoAdj(int n, int[][] paths)
    {
        //create graph using hash map
        Map<Integer, List<Integer>> graph = createGraph(paths);
        int[] colors = new int[n];
        for(int i=0;i<n;i++){
            colors[i] = 0;
        }

        for(int i=0; i<n; i++){
            int c = 1;
            if(colors[i] == 0){
                //this is either first node, or completely disconnected node, assign first color
                colors[i] = c++;

                //now do bfs
                Queue<Integer> queue = new LinkedList<>();
                queue.add(i+1);

                while (!queue.isEmpty()){
                    Integer current = queue.poll();

                    List<Integer> neighbours = graph.getOrDefault(current, new ArrayList<>());

                    for(int neighbour : neighbours){
                        if(colors[neighbour-1] == 0){
                            colors[neighbour-1] = c++;
                            queue.add(neighbour);
                        }

                        if(c > 4){
                            c = 1; //rest color to 1
                        }
                    }
                }
            }
        }

        return colors;
    }

    private static Map<Integer, List<Integer>> createGraph(int[][] paths)
    {
        Map<Integer, List<Integer>> graph = new HashMap<>();

        for(int[] path : paths){
            List<Integer> neighbours = graph.getOrDefault(path[0], new ArrayList<>());
            neighbours.add(path[1]);
            graph.put(path[0], neighbours);

            //since its bidirectional
            List<Integer> neighbours2 = graph.getOrDefault(path[1], new ArrayList<>());
            neighbours2.add(path[0]);
            graph.put(path[1], neighbours2);
        }

        return graph;
    }

    private static void printarr(int[] arr)
    {
        for(int e : arr){
            System.out.print(e+",");
        }
        System.out.println();
    }
}


