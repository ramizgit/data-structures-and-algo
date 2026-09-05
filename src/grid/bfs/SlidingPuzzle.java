package grid.bfs;

import java.util.*;

public class SlidingPuzzle {

    //https://leetcode.com/problems/sliding-puzzle/description/

    public int slidingPuzzle(int[][] board)
    {
        int m = board.length;
        int n = board[0].length;

        String start = boardToString(board);
        String target = "123450";

        Queue<State> bfsQueue = new ArrayDeque<>();
        bfsQueue.offer(new State(start, 0)); //starting state

        Set<String> visited = new HashSet<>();
        visited.add(start);

        while(!bfsQueue.isEmpty()){

            State curr = bfsQueue.poll();

            if(curr.boardStr.equals(target)){
                return curr.moves;
            }

            //explore neighbours
            for(String neighbour : getNeighbours(curr.boardStr, m, n)){

                if(!visited.contains(neighbour)){
                    visited.add(neighbour);
                    bfsQueue.offer(new State(neighbour, curr.moves + 1));
                }
            }
        }

        return -1;
    }

    private List<String> getNeighbours(String boardStr, int m, int n)
    {
        /*
        Find 0
        ↓
        Find cells adjacent to 0
          ↓
        Swap 0 with each
          ↓
        Each swap creates a NEW BOARD STATE
         */

        List<String> neighbours = new ArrayList<>();

        int zeroIdx = boardStr.indexOf('0');

        int zeroRow = zeroIdx / n;
        int zeroCol = zeroIdx % n;

        int[][] directions = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} }; //all four adjacent directions

        for(int[] dir : directions){

            int newRow = zeroRow + dir[0];
            int newCol = zeroCol + dir[1];

            //boundary check
            if(newRow < 0 || newRow >= m || newCol < 0 || newCol >= n){
              continue; //out of boundary
            }

            int nextIdx = newRow * n + newCol;

            char[] chars = boardStr.toCharArray(); //creates a fresh copy for every neighbor

            //swap
            char temp = chars[zeroIdx];
            chars[zeroIdx] = chars[nextIdx];
            chars[nextIdx] = temp;

            neighbours.add(new String(chars));
        }

        return neighbours;
    }

    private String boardToString(int[][] board)
    {
        int m = board.length;
        int n = board[0].length;

        StringBuilder sb = new StringBuilder();

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                sb.append(board[i][j]);
            }
        }

        return sb.toString();
    }

    static class State{
        String boardStr;
        int moves;

        public State(String boardStr, int moves) {
            this.boardStr = boardStr;
            this.moves = moves;
        }
    }
}
