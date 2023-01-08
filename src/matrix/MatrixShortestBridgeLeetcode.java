package matrix;

import javafx.util.Pair;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class MatrixShortestBridgeLeetcode {

    public static int[][] directions = {{0,-1},{0,1}, {-1,0}, {1,0} };

    public static void main(String[] args)
    {
        int[][] matrix = { {1, 1, 1, 1},
                           {1, 1, 1, 1},
                           {0, 0, 0, 0},
                           {0, 0, 0, 0},
                           {0, 0, 0, 0},
                           {1, 1, 1, 1}};

        System.out.println("count : "+getShortestBridgeDistance(matrix));
    }

    public static int getShortestBridgeDistance(int[][] matrix)
    {
        int rows = matrix.length;
        int cols = matrix[0].length;
        Set<Pair<Integer, Integer>> visitedSet = new HashSet<>();
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();

        //1. find first island, and add all coordinates in the island into visited set and a queue
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                if(matrix[i][j] == 1){
                    dfs(matrix, rows, cols, i, j, visitedSet, queue);
                    return bfs(matrix, rows, cols, visitedSet, queue);

                    //---------------------------bfs2 is just another way to get shortest path using weigh in queue itself--------------------------------
                    //return bfs2(matrix, rows, cols, visitedSet, queue);
                }
            }
        }

        return 0;
    }

    public static void dfs(int[][] matrix, int rows, int cols, int i, int j, Set<Pair<Integer, Integer>> visitedSet, Queue<Pair<Integer, Integer>> queue)
    {
        visitedSet.add(new Pair<>(i, j));
        queue.add(new Pair<>(i, j));

        for(int[] dir : directions){
            int x = i + dir[0];
            int y = j + dir[1];

            if(x >= 0 && y >= 0 && x < rows && y < cols && matrix[x][y] == 1 && !visitedSet.contains(new Pair<>(x, y))){
                dfs(matrix, rows, cols, x, y, visitedSet, queue);
            }
        }
    }

    public static int bfs(int[][] matrix, int rows, int cols, Set<Pair<Integer, Integer>> visitedSet, Queue<Pair<Integer, Integer>> queue)
    {
        int count = 0;
        while (!queue.isEmpty()){
            int len = queue.size();
            for(int i=0; i<len; i++){
                Pair<Integer, Integer> pair = queue.poll();

                for(int[] dir : directions){
                    int x = pair.getKey() + dir[0];
                    int y = pair.getValue() + dir[1];

                    if(x >= 0 && y >= 0 && x < rows && y < cols && !visitedSet.contains(new Pair<>(x, y))){
                        if(matrix[x][y] == 1){
                            return count;
                        }else {
                            visitedSet.add(new Pair<>(x, y));
                            queue.add(new Pair<>(x, y));
                        }
                    }
                }
            }
            count++;
        }

        return 0;
    }

    //---------------------------Just another way to get shortest path using weigh in queue itself--------------------------------
    public static int bfs2(int[][] matrix, int rows, int cols, Set<Pair<Integer, Integer>> visitedSet, Queue<Pair<Integer, Integer>> queue)
    {
        Queue<Coordinate> coordinateQueue = new LinkedList<>();
        while(!queue.isEmpty()){
            Pair<Integer, Integer> poll = queue.poll();
            coordinateQueue.add(new Coordinate(poll.getKey(), poll.getValue(), 0));
        }

        while (!coordinateQueue.isEmpty()){
            Coordinate poll = coordinateQueue.poll();

            for(int[] dir : directions){
                int x = poll.row + dir[0];
                int y = poll.col + dir[1];

                if(x >= 0 && y >= 0 && x < rows && y < cols && !visitedSet.contains(new Pair<>(x, y))){
                    if(matrix[x][y] == 1){
                        return poll.weight;
                    }
                    visitedSet.add(new Pair<>(x, y));
                    coordinateQueue.add(new Coordinate(x, y, poll.weight+1));
                }
            }
        }

        return 0;
    }

    static class Coordinate{
        int row;
        int col;
        int weight;

        public Coordinate(int row, int col, int weight) {
            this.row = row;
            this.col = col;
            this.weight = weight;
        }
    }
}


