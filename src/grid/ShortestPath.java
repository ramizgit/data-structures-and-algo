package grid;

import java.util.*;

public class ShortestPath {

    //PRINT SHRORTEST PATH
    public List<Coordinates> shortestPathInGrid(int[][] grid)
    {
        int m = grid.length;
        int n = grid[0].length;

        List<List<Coordinates>> result = new ArrayList<>();

        //queue for bfs logic
        Queue<Coordinates> queue = new ArrayDeque<>();
        queue.add(new Coordinates(0, 0, 0));

        //visited matrix to avoid infinite loop
        boolean[][] visited = new boolean[m][n];
        visited[0][0] = true;

        //keep track of parent cells
        Coordinates[][] parent = new Coordinates[m][n];

        int[][] directions = { {0, 1}, {0, -1}, {1, 0}, {-1, 0} };

        while(!queue.isEmpty()){
            Coordinates curr = queue.poll();

            //breaking condition
            if(curr.row == m-1 && curr.col == n-1){
                //reached end, collect output here
                System.out.println("shortest path len : " + curr.dist);

                //collect answer
                return buildPath(parent, curr);
            }

            for(int[] dir : directions){
                int x = curr.row + dir[0];
                int y = curr.col + dir[1];

                if(x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 0 && !visited[x][y]){
                    queue.add(new Coordinates(x, y, curr.dist + 1));
                    visited[x][y] = true;
                    parent[x][y] = curr;
                }
            }
        }

        return Collections.emptyList();
    }

    private List<Coordinates> buildPath(Coordinates[][] parent, Coordinates end) {
        List<Coordinates> path = new ArrayList<>();
        Coordinates curr = end;

        while (curr != null) {
            path.add(curr);
            curr = parent[curr.row][curr.col];
        }

        Collections.reverse(path);
        return path;
    }
}

class Coordinates{
    int row;
    int col;
    int dist;

    public Coordinates(int row, int col, int dist) {
        this.row = row;
        this.col = col;
        this.dist = dist;
    }

    public Coordinates(int row, int col) {
        this.row = row;
        this.col = col;
    }
}
