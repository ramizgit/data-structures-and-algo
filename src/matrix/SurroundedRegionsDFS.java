package matrix;

public class SurroundedRegionsDFS {

    public static void main(String[] args)
    {
        char[][] board = { {'x', 'x', 'x', 'x'},
                           {'x', 'o', 'x', 'x'},
                           {'x', 'x', 'o', 'x'},
                           {'x', 'o', 'o', 'x'} };
        capture(board);
        System.out.println(board);
    }

    public static void capture(char[][] board)
    {
        int rows = board.length;
        int cols = board[0].length;

        //traverse all boundaries and if 'o' found, convert to 'y', do DFS and convert all connected 'o' to 'y'
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                if( onTheBorder(rows, cols, i, j) && board[i][j] == 'o' ){
                    dfs(i, j, rows, cols, board);
                }
            }
        }

        //all safe 'o's been makrd 'y', convert remaining 'o' to 'x', and 'y' to 'o' back
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                if(board[i][j] == 'o'){
                    board[i][j] = 'x';
                }else if (board[i][j] == 'y'){
                    board[i][j] = 'o';
                }
            }
        }
        System.out.println(board);
    }

    public static void dfs(int i, int j, int rows, int cols, char[][] board)
    {
        //mark it 'y'
        board[i][j] = 'y';

        //now go all four directions
        int[][] directions = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };
        for(int[] dir : directions){
            int x = i + dir[0];
            int y = j + dir[1];

            if(x >= 0 && y >= 0 && x < rows && y < cols && board[x][y] == 'o'){
                dfs(x, y, rows, cols, board);
            }
        }
    }

    private static boolean onTheBorder(int rows, int cols, int i, int j)
    {
        return ((i ==0 || i == rows -1) || (j ==0 || j == cols-1));
    }
}
