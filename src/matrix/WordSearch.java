package matrix;

public class WordSearch {
    //https://leetcode.com/problems/word-search/description/

    public static void main(String[] args)
    {
        char[][] board = {{'A','B','C','E'}, {'S','F','C','S'}, {'A','D','E','E'}};
        System.out.println(exist(board, "ABCCED")); //true
        System.out.println(exist(board, "ABCCEDA")); //true
        System.out.println(exist(board, "ABCCEDAZ")); //true
    }

    private static boolean exist(char[][] board, String word)
    {
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        boolean result;

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(board[i][j] == word.charAt(0)){
                    result = dfs(board, word, i, j, visited, 1, m, n);
                    if(result){
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private static boolean dfs(char[][] board, String word, int i, int j, boolean[][] visited, int len, int m, int n)
    {
        if(len == word.length()){
            return true;
        }

        visited[i][j] = true;

        int[][] directions = { {0, -1}, {0, 1}, {1, 0}, {-1, 0} };

        for(int[] dir : directions){
            int x = i + dir[0];
            int y = j + dir[1];

            if(x >= 0 && y >= 0 && x < m && y < n && !visited[x][y] && len < word.length() && board[x][y] == word.charAt(len)){
                len++;
                return dfs(board, word, x, y, visited, len, m, n);
            }
        }
        return false;
    }
}
