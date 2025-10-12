package grid;

public class FloodFill {
    //https://leetcode.com/problems/flood-fill/description/
    public static void main(String[] args)
    {
        int[][] image = {{1,1,1},{1,1,0},{1,0,1}};
        floodFill(image, 1, 1, 2);

    }

    private static void floodFill(int[][] image, int sr, int sc, int color)
    {
        int m = image.length;
        int n = image[0].length;
        int start = image[sr][sc];

        dfs(image, m, n, sr, sc, color, start);

        System.out.println(image);
    }

    private static void dfs(int[][] image, int m, int n, int i, int j, int color, int start)
    {
        image[i][j] = color;

        int[][] directions = { {0, -1}, {0, 1}, {1, 0}, {-1, 0} };
        for(int[] dir : directions){
            int x = i + dir[0];
            int y = j + dir[1];

            if(x >= 0 && y >= 0 && x < m && y < n && image[x][y] == start){
                dfs(image, m, n, x, y, color, start);
            }
        }
    }
}
