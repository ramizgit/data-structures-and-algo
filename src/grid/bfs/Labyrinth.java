package grid.bfs;

import java.util.*;

public class Labyrinth {

    //https://cses.fi/problemset/task/1193

    /*
    You are given a map of a labyrinth, and your task is to find a path from start to end. You can walk left, right, up and down.
    Input
    The first input line has two integers n and m: the height and width of the map.
    Then there are n lines of m characters describing the labyrinth. Each character is . (floor), # (wall), A (start), or B (end). There is exactly one A and one B in the input.
    Output
    First print "YES", if there is a path, and "NO" otherwise.
    If there is a path, print the length of the shortest such path and its description as a string consisting of characters L (left), R (right), U (up), and D (down). You can print any valid solution.
    Constraints

    1 \le n,m \le 1000

    Example
    Input:
    5 8
    ########
    #.A#...#
    #.##.#B#
    #......#
    ########

    Output:
    YES
    9
    LDDRRRRRU
     */

    public Result findPath(char[][] grid)
    {
        Result result = new Result();

        //input validation
        if(grid == null || grid.length == 0){
            return result;
        }

        //scan the grid to find source coordinates
        int m = grid.length;
        int n = grid[0].length;

        int startRow = -1;
        int startCol = -1;
        int endRow = -1;
        int endCol = -1;

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j] == 'A'){
                    startRow = i;
                    startCol = j;
                }else if(grid[i][j] == 'B'){
                    endRow = i;
                    endCol = j;
                }
            }
        }

        //run bfs from source to target to check feasibility and collect answers
        Queue<State> bfsQueue = new ArrayDeque<>();
        bfsQueue.offer(new State(startRow, startCol, 0)); //starting cell

        boolean[][] visited = new boolean[m][n];
        visited[startRow][startCol] = true; //starting cell

        State[][] parent = new State[m][n];
        char[][] move = new char[m][n];

        int[][] directions = {
                {0, 1, 1}, //R
                {0, -1, 2}, //L
                {1, 0, 3}, //D
                {-1, 0, 4} //U
        };

        Map<Integer, Character> choice = new HashMap<>();
        choice.put(1, 'R');
        choice.put(2, 'L');
        choice.put(3, 'D');
        choice.put(4, 'U');

        while(!bfsQueue.isEmpty()){

            State curr = bfsQueue.poll();

            //exit condition
            if(grid[curr.row][curr.col] == 'B'){
                result.found = true;
                result.length = curr.length;
                break;
            }

            //explore neighbours
            for(int[] dir : directions){

                int newRow = curr.row + dir[0];
                int newCol = curr.col + dir[1];

                //boundary check
                if(newRow < 0 || newRow >= m || newCol < 0 || newCol >= n){
                    continue; //out of boundary
                }

                if(visited[newRow][newCol]){
                    continue; //already visited
                }

                if(grid[newRow][newCol] == '#'){
                    continue; //hit a wall
                }

                visited[newRow][newCol] = true;
                bfsQueue.offer(new State(newRow, newCol, curr.length + 1));

                //track parent
                parent[newRow][newCol] = curr;
                move[newRow][newCol] = choice.get(dir[2]);
            }
        }

        if (!result.found) {
            return result; //no path exists
        }

        //populate path
        StringBuilder sb = new StringBuilder();

        int row = endRow;
        int col = endCol;

        while (!(row == startRow && col == startCol)) {

            sb.append(move[row][col]);

            State p = parent[row][col];
            row = p.row;
            col = p.col;
        }

        result.path = sb.reverse().toString();

        return result;
    }

    static class State{
        int row;
        int col;
        int length;

        public State(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public State(int row, int col, int length) {
            this.row = row;
            this.col = col;
            this.length = length;
        }
    }

    static class Result {
        boolean found;
        int length;
        String path;
    }
}
