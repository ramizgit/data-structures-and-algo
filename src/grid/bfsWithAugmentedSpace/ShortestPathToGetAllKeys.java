package grid.bfsWithAugmentedSpace;

import java.util.*;

public class ShortestPathToGetAllKeys {

    //https://leetcode.com/problems/shortest-path-to-get-all-keys/description/

    public int shortestPathAllKeys(String[] grid)
    {
        //input validation
        if(grid == null || grid.length == 0){
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length();

        //find starting cell, and number of keys to collect
        int startRow = 0;
        int startCol = 0;
        int expectedKeysMask = 0;

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                char ch = grid[i].charAt(j);
                if(ch == '@'){
                    startRow = i;
                    startCol = j;
                }

                if(ch >= 'a' && ch <= 'f'){
                    expectedKeysMask |= (1 << (ch - 'a'));
                }
            }
        }

        //bfs queue
        Queue<State> queue = new ArrayDeque<>();
        queue.offer(new State(startRow, startCol, 0, 0));

        //visited tracking : visited[row][col][mask]
        //important note : in Dijkstra/BFS, the PQ/BFS state and dist/visited state must represent the SAME state space.
        boolean[][][] visited = new boolean[m][n][64]; //maximum keys = 6, hence Number of possible masks : 2^6=64
        visited[startRow][startCol][0] = true;

        //four directions
        int[][] directions = { {0, 1}, {0, -1}, {1, 0}, {-1, 0} };

        while(!queue.isEmpty()){
            State curr = queue.poll();

            //early exit
            if(curr.keysMask == expectedKeysMask){
                return curr.dist;
            }

            //explore neighbours
            for(int[] dir : directions){
                int x = dir[0] + curr.row;
                int y = dir[1] + curr.col;

                //boundary check
                if(x < 0 || x >= m || y < 0 || y >= n){
                    continue;
                }

                char ch = grid[x].charAt(y);

                //wall check
                if(ch == '#'){
                    continue;
                }

                int newMask = curr.keysMask;

                //update mask if key found
                if(ch >= 'a' && ch <= 'f'){
                    newMask |= (1 << (ch - 'a'));
                }

                //lock check
                if(ch >= 'A' && ch <= 'F'){
                    int requiredKeyBit = ch - 'A';
                    if((newMask & (1 << requiredKeyBit)) == 0){
                        continue; //don't have key
                    }
                }

                //visited check
                if(visited[x][y][newMask]){
                    continue;
                }
                visited[x][y][newMask] = true;

                //enqueue
                queue.offer(new State(x, y, newMask, curr.dist + 1));
            }

        }

        return -1;
    }

    class State{
        int row;
        int col;
        int keysMask;
        int dist;

        public State(int row, int col, int keysMask, int dist) {
            this.row = row;
            this.col = col;
            this.keysMask = keysMask;
            this.dist = dist;
        }
    }
}
