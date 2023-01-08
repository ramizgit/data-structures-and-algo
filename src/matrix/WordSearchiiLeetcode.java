package matrix;

import java.util.ArrayList;
import java.util.List;

public class WordSearchiiLeetcode {
    public static void main(String[] args)
    {
        char[][] board = {{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}};
        
        System.out.println(findWords(board, new String[]{"oath","pea","eat","rain"})); //[oath, eat]
    }

    public static List<String> findWords(char[][] board, String[] words)
    {
        int rows = board.length;
        int cols = board[0].length;
        List<String> result = new ArrayList<>();
        boolean found;

        for(String word : words){
            found = false;
            for(int i=0; i<rows; i++){
                for(int j=0; j<cols; j++){
                    boolean[][] visited = new boolean[rows][cols];
                    found = dfs(board, rows, cols, i, j, word, 0, visited);
                    if(found){
                        result.add(word);
                        break;
                    }
                }
                if(found){
                    break;
                }
            }
        }
        return result;
    }

    public static boolean dfs(char[][] board, int rows, int cols, int i, int j, String word, int w, boolean[][] visited)
    {
        if(w == word.length()){
            return true;
        }

        int[][] directions = { {0, -1}, {0, 1}, {-1, 0}, {1, 0}};

        for(int[] dir : directions){
            int x = i + dir[0];
            int y = j + dir[1];

            if(x >= 0 && y >= 0 && x < rows && y < cols && w < word.length() && board[x][y] == word.charAt(w) && !visited[x][y]){
                w++;
                visited[x][y] = true;
                return dfs(board, rows, cols, x, y, word, w, visited);
            }
        }
        return false;
    }
}
