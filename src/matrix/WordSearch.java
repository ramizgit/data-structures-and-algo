package grid;

public class WordSearch {

    public static void main(String[] args)
    {
        char[][] board = {{'A','B','C','E'},
                          {'S','F','C','S'},
                          {'A','D','E','E'}};
        System.out.println(wordSearch(board, "ABCCED")); //true
        System.out.println(wordSearch(board, "ABCCEDA")); //true
        System.out.println(wordSearch(board, "ABCCEDAZ")); //false
    }

    private static boolean wordSearch(char[][] grid, String word)
    {
        int m = grid.length;
        int n = grid[0].length;

        boolean[][] visited = new boolean[m][n];

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j] == word.charAt(0)){
                    if(dfs(grid, visited, m, n, i, j, 1, word)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    private static boolean dfs(char[][] grid, boolean[][] visited, int m, int n, int i, int j, int len, String word)
    {
        visited[i][j] = true;

        if(len == word.length()){
            return true;
        }

        int[][] directions = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };

        for(int[] dir : directions){
            int x = i + dir[0];
            int y = j + dir[1];

            if(x >= 0 && y >= 0 && x < m && y < n && !visited[x][y] && grid[x][y] == word.charAt(len)){
                if(dfs(grid, visited, m, n, x, y, len + 1, word)){
                    return true;
                }
            }
        }

        //backtrack
        visited[i][j] = false;
        return false;
    }
}
