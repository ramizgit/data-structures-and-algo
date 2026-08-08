package lld.snakeandladder.service;

import lld.snakeandladder.model.Game;
import lld.snakeandladder.model.Player;

public class GameService {

    private final Game game;

    public GameService(Game game) {
        this.game = game;
    }

    public void playTurn()
    {
        //get current player
        Player player = this.game.getCurrPlayer();

        //roll dice
        int roll = this.game.getDice().roll();

        System.out.println(player.getName() + " rolled " + roll);

        int currPos = player.getPosition();
        int newPos = currPos + roll;

        // Check if move is valid
        if (newPos > game.getBoard().getSize()) {
            System.out.println(player.getName() + " cannot move.");
            game.nextTurn();
            return;
        }

        //resolve snake/ladder
        newPos = this.game.getBoard().getFinalPosition(newPos);

        //update player position
        player.setPosition(newPos);

        // Check winner
        if (newPos == game.getBoard().getSize()) {
            System.out.println(player.getName() + " wins!");
            game.setGameOver(true);
            return;
        }

        // Next player's turn
        game.nextTurn();

    }
}
