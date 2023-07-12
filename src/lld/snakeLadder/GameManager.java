package lld.snakeladder;

import java.util.Arrays;
import java.util.List;

public class GameManager {

    private Board board;
    private List<Player> players;
    private int currPlayerIdx;

    public GameManager(Board board, List<Player> players) {
        this.board = board;
        this.players = players;
        this.currPlayerIdx = 0;
    }

    public void play()
    {
        while (true){
            Player currentPlayer = this.players.get(currPlayerIdx);
            int newPosition = this.board.move(currentPlayer.getCurrentPosition(), Dice.rollDice());
            currentPlayer.setCurrentPosition(newPosition);

            if(newPosition == this.board.getSize()){
                //win
                break;
            }

            this.currPlayerIdx = (this.currPlayerIdx + 1) % this.players.size();
        }
    }

    public static void main(String[] args)
    {
        Player player1 = new Player("alice");
        Player player2 = new Player("patrick");
        List<Player> players = Arrays.asList(player1, player2);

        Board board = new Board(100);

        GameManager gameManager = new GameManager(board, players);
        gameManager.play();
    }
}
