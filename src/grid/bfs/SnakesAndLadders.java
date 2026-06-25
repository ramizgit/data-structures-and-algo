package consistenthashing.grid.bfs;

import java.util.*;

public class SnakesAndLadders {

    //https://leetcode.com/problems/snakes-and-ladders/description/

    //Time Complexity: O(n²)
    //Space Complexity: O(n²)
    public int snakesAndLadders(int[][] board)
    {
        //input validation
        if(board == null || board.length == 0){
            return -1;
        }

        int n = board.length;

        Queue<State> bfsQueue = new ArrayDeque<>();
        bfsQueue.offer(new State(1, 0)); //starting cell

        // the visited array tracks the final destination square after applying any snake/ladder.
        boolean[] visited = new boolean[n*n + 1];
        visited[1] = true;

        while(!bfsQueue.isEmpty()){

            State curr = bfsQueue.poll();

            //exit condition
            if(curr.square == n*n){
                return curr.rolls; //target reached
            }

            //explore neighbours
            for(int i=1; i<=6; i++){

                int nextSquare = curr.square + i;

                //out of boundary check
                if(nextSquare > n * n){
                    break;
                }

                //find row/col
                int[] nextCell = getRowCol(nextSquare, n);

                int nextRow = nextCell[0];
                int nextCol = nextCell[1];

                int destination = board[nextRow][nextCol] == -1 ? nextSquare : board[nextRow][nextCol];

                if(visited[destination]){
                    continue; //already visited
                }

                visited[destination] = true;
                bfsQueue.offer(new State(destination, curr.rolls + 1));
            }
        }

        return -1;
    }

    private int[] getRowCol(int square, int n)
    {
        // convert to 0-based square number
        int squareNum = square - 1;

        // row index counted from bottom of board
        int rowFromBottom = squareNum / n;

        // column within that row
        int col = squareNum % n;

        // reverse direction for odd rows (zig-zag pattern)
        if(rowFromBottom % 2 == 1){
            col = n - 1 - col;
        }

        // convert bottom-based row to actual matrix row
        int row = n - 1 - rowFromBottom;

        return new int[]{row, col};
    }

    static class State{
        int square;
        int rolls;

        public State(int square, int rolls) {
            this.square = square;
            this.rolls = rolls;
        }
    }
}
