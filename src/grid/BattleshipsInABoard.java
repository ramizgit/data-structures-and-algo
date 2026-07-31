package grid;

public class BattleshipsInABoard {

    //https://leetcode.com/problems/battleships-in-a-board/description/?envType=problem-list-v2&envId=7p55wqm

    // Time: O(m * n)
    // Space: O(1)
    public int countBattleships(char[][] board)
    {
        /*
        Approach:-
        // Count only the first (top-left) cell of each battleship.
        // A cell is the start of a new battleship iff:
        // 1. It is 'X'.
        // 2. There is no 'X' above it.
        // 3. There is no 'X' to its left.
         */

        //input validation
        if(board == null || board.length == 0){
            return 0;
        }

        int m = board.length;
        int n = board[0].length;

        int count = 0;

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){

                if(board[i][j] == '.'){
                    continue;
                }

                //if top is 'X', current cell is part of an existing vertical ship
                if(i > 0 && board[i-1][j] == 'X'){
                    continue;
                }

                //if left is 'X', current cell is part of an existing horizontal ship
                if(j > 0 && board[i][j-1] == 'X'){
                    continue;
                }

                count++;
            }
        }

        return count;
    }
}
