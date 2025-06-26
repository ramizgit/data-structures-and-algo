package matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PacificAtlanticWaterFlow {

    public static void main(String[] args)
    {
        int[][] heights = {{1,2,2,3,5},{3,2,3,4,4},{2,4,5,3,1},{6,7,1,4,5},{5,1,1,2,4}};
        System.out.println(pacificAtlantic(heights)); //Output: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]

        int[][] heights2 = {{2,1},{1,2}};
        System.out.println(pacificAtlantic(heights2)); //Output: [[0,0],[0,1],[1,0],[1,1]]
    }

    public static List<List<Integer>> pacificAtlantic(int[][] heights)
    {
        int m = heights.length;
        int n = heights[0].length;

        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];

        for(int i=0; i<m; i++){
            dfs(heights, pacific, i, 0, m, n); //left pacific
            dfs(heights, atlantic, i, n-1, m, n); //right atlantic
        }

        for(int j=0; j<n; j++){
            dfs(heights, pacific, 0, j, m, n); //top pacific
            dfs(heights, atlantic, m-1, j, m, n); //bottom atlantic
        }

        //find intersection
        List<List<Integer>> list = new ArrayList<>();
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(pacific[i][j] && atlantic[i][j]){
                    list.add(Arrays.asList(i, j));
                }
            }
        }

        return list;
    }

    private static void dfs(int[][] heights, boolean[][] ocean, int i, int j, int m, int n)
    {
        ocean[i][j] = true;

        int[][] directions = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };

        for(int[] dir : directions){
            int x = i + dir[0];
            int y = j + dir[1];

            if(x >= 0 && x < m && y >= 0 && y < n && heights[x][y] >= heights[i][j] && !ocean[x][y]){
                dfs(heights, ocean, x, y, m, n);
            }
        }
    }
}

