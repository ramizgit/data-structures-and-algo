package lld.snakeandladder.driver;

import lld.snakeandladder.model.*;
import lld.snakeandladder.service.GameService;

import java.util.List;
import java.util.Map;

public class SnakeAndLadderApplication {

    public static void main(String[] args) {

        // Create snakes
        Map<Integer, Snake> snakes = Map.of(
                17, new Snake(17, 7),
                54, new Snake(54, 34),
                93, new Snake(93, 73)
        );

        // Create ladders
        Map<Integer, Ladder> ladders = Map.of(
                3, new Ladder(3, 22),
                8, new Ladder(8, 26),
                20, new Ladder(20, 38)
        );

        // Create board
        Board board = new Board(100, snakes, ladders);

        // Create players
        Player alice = new Player(1, "Alice", 1);
        Player bob = new Player(2, "Bob", 1);

        List<Player> players = List.of(alice, bob);

        // Create dice
        Dice dice = new Dice(6);

        // Create game
        Game game = new Game(board, dice, players);

        // Create service
        GameService gameService = new GameService(game);

        // Play until someone wins
        while (!game.isGameOver()) {
            gameService.playTurn();
        }
    }
}
