package lld.snakeandladder.model;

import java.util.List;

//root object coordinating the game state
public class Game {

    private Board board;
    private Dice dice;
    private List<Player> players;

    private int currPlayerIndex;
    private boolean gameOver;

    public Game(Board board, Dice dice, List<Player> players) {
        this.board = board;
        this.dice = dice;
        this.players = players;
    }

    public Player getCurrPlayer(){
        return this.players.get(currPlayerIndex);
    }

    public void nextTurn() {
        currPlayerIndex = (currPlayerIndex + 1) % players.size();
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Dice getDice() {
        return dice;
    }

    public void setDice(Dice dice) {
        this.dice = dice;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public int getCurrPlayerIndex() {
        return currPlayerIndex;
    }

    public void setCurrPlayerIndex(int currPlayerIndex) {
        this.currPlayerIndex = currPlayerIndex;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
}
