package grid.bfs;

import java.util.*;

public class MinKnightMove {

    //https://www.youtube.com/watch?v=L8tY9gSfHz4

    /*
    Problem:
    You are given an N × N chessboard.
    Knight starts at (sx, sy)
    Destination is (dx, dy)
    Bishop is fixed at (bx, by)
    Knight moves normally (8 possible L-shaped moves)
    Knight cannot enter any square attacked by the bishop
    Find the minimum number of knight moves to reach destination.
    Return -1 if impossible.

    The trick is that bishop never moves, only the knight moves.

    sample:-
    int n = 8;
    int[] knight = {0, 0};
    int[] bishop = {3, 3};
    int[] destination = {7, 7};
     */

    public int minKnightMoves(
            int n,
            int[] knight,
            int[] bishop,
            int[] destination)
    {
        //populate blocked cells
        boolean[][] blocked = new boolean[n][n];

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                int rowDiff = Math.abs(i - bishop[0]);
                int colDiff = Math.abs(j - bishop[1]);

                if(rowDiff == colDiff){
                    blocked[i][j] = true; //bishop range
                }
            }
        }

        //edge case - knight starts on bishop attack
        if (blocked[knight[0]][knight[1]]) {
            return -1;
        }

        //edge case - Destination itself is attacked
        if (blocked[destination[0]][destination[1]]) {
            return -1;
        }

        //bfs logic
        Queue<State> bfsQueue = new ArrayDeque<>();
        bfsQueue.offer(new State(knight[0], knight[1], 0)); //starting cell with 0 move

        boolean[][] visited = new boolean[n][n];
        visited[knight[0]][knight[1]] = true; //mark starting cell visited

        //all feasible L shaped directions knight can move
        int[][] directions = {
                {-2, -1},
                {-2,  1},

                { 2, -1},
                { 2,  1},

                {-1, -2},
                { 1, -2},

                {-1,  2},
                { 1,  2}
        };

        while(!bfsQueue.isEmpty()){

            State curr = bfsQueue.poll();

            //exit condition
            if(curr.row == destination[0] && curr.col == destination[1]){
                return curr.moves; //destination reached
            }

            //explore neighbours
            for(int[] dir : directions){
                int newRow = curr.row + dir[0];
                int newCol = curr.col + dir[1];

                //boundary check
                if(newRow < 0 || newRow >= n || newCol < 0 || newCol >= n){
                    continue; //out of boundary
                }

                //constraint check
                if(visited[newRow][newCol] //visited check
                        || blocked[newRow][newCol] //bishop range check
                )
                {
                    continue;
                }

                //important : blocked cell check we can also do on the fly, and avoid extra space allocated
                /*if (Math.abs(newRow - bishop[0]) == Math.abs(newCol - bishop[1])) {
                    continue;
                }*/

                visited[newRow][newCol] = true; //mark visited
                bfsQueue.offer(new State(newRow, newCol, curr.moves + 1)); //enqueue
            }
        }

        return -1; //impossible to reach destination
    }

    static class State{
        int row;
        int col;
        int moves;

        public State(int row, int col, int moves) {
            this.row = row;
            this.col = col;
            this.moves = moves;
        }
    }
}
