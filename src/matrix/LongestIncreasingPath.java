package matrix;

public class LongestIncreasingPath {
    public static void main(String[] args)
    {
        int[][] matrix = {{9,9,4},{6,6,8},{2,1,1}};
        System.out.println(getLongestIncreasingPath(matrix)); //4

        int[][] matrix2 = {{3,4,5},{3,2,6},{2,2,1}};
        System.out.println(getLongestIncreasingPath(matrix2)); //4

        int[][] matrix3 = { {9,9,8,7}, {3,2,1,6}, {4,5,6,7} };
        System.out.println(getLongestIncreasingPath(matrix3)); //7
    }

    public static int getLongestIncreasingPath(int[][] matrix)
    {
        int m = matrix.length;
        int n = matrix[0].length;
        int maxCount = 0;
        int[][] memo = new int[m][n];

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                maxCount = Math.max(maxCount, memo, dfs(matrix, new boolean[m][n], m, n, i, j));
            }
        }

        return maxCount;
    }

    public static int dfs(int[][] matrix, int[][] memo, boolean[][] visited, int rows, int cols, int i, int j)
    {
        if(memo[i][j] != 0){
            return memo[i][j];
        }

        int max = 1;
        int[][] directions = { {0, -1}, {0, 1}, {-1, 0}, {1, 0}};

        visited[i][j] = true;

        for(int[] dir : directions){
            int x = i + dir[0];
            int y = j + dir[1];

            if(x >= 0 && y >= 0 && x < rows && y < cols && matrix[x][y] > matrix[i][j] && !visited[x][y]){
                max = Math.max(max, 1 + dfs(matrix, memo, visited, rows, cols, x, y));
            }
        }

        //store for memoiztion
        memo[i][j] = max;
        return max;
    }
}
