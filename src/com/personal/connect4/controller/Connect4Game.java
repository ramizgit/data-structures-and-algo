package com.personal.connect4.controller;

import com.personal.connect4.model.Grid;
import com.personal.connect4.model.Token;

public class Connect4Game {

    private final String color1;
    private final String color2;
    private final Grid grid;
    private boolean firstPlayersTurn;

    public Connect4Game(String color1, String color2, int rows, int cols) {
        this.color1 = color1;
        this.color2 = color2;
        this.grid = new Grid(rows, cols);

        this.firstPlayersTurn = true;
    }

    public void playRound(int column)
    {
        String color = firstPlayersTurn ? color1 : color2;
        int rowAdded = this.getGrid().addToken(column, color);

        if(rowAdded != -1){
            //check for winner
            if(this.isWinner(color)){
                if(firstPlayersTurn){
                    System.out.println("first player won");
                }else {
                    System.out.println("second player won");
                }
                //exit game
            }
            System.out.println("successfully added "+color+" at column:"+column);
            firstPlayersTurn = !firstPlayersTurn;
        }
    }

    public boolean isWinner(String winningColor)
    {
        Token[][] grid = this.getGrid().getGrid();

        //check horizontally
        for(int c=0; c<(this.getGrid().getColumns()-3); c++){
            for(int r=0; r<this.getGrid().getRows(); r++){
                if(grid[r][c].equals(winningColor) && grid[r][c+1].equals(winningColor) && grid[r][c+2].equals(winningColor) && grid[r][c+3].equals(winningColor)){
                    return true;
                }
            }
        }

        //check vertically
        for(int r=0; r<(this.getGrid().getRows()-3); r++){
            for(int c=0; c<this.getGrid().getColumns(); c++){
                if(grid[r][c].equals(winningColor) && grid[r+1][c].equals(winningColor) && grid[r+2][c].equals(winningColor) && grid[r+3][c].equals(winningColor)){
                    return true;
                }
            }
        }

        //check ascending diagonal
        for(int r=3; r<this.getGrid().getRows(); r++){
            for(int c=0; c<(this.getGrid().getColumns()-3); c++){
                if(grid[r][c].equals(winningColor) && grid[r-1][c+1].equals(winningColor) && grid[r-2][c+2].equals(winningColor) && grid[r-3][c+3].equals(winningColor)){
                    return true;
                }
            }
        }

        //check descending digaonal
        for(int r=3; r<this.getGrid().getRows(); r++){
            for(int c=3; c<this.getGrid().getColumns(); c++){
                if(grid[r][c].equals(winningColor) && grid[r-1][c-1].equals(winningColor) && grid[r-2][c-2].equals(winningColor) && grid[r-3][c-3].equals(winningColor)){
                    return true;
                }
            }
        }
        return false;
    }

    public void reset()
    {
        this.getGrid().resetGrid();
        this.firstPlayersTurn = true;
    }

    public String getColor1() {
        return color1;
    }

    public String getColor2() {
        return color2;
    }

    public Grid getGrid() {
        return grid;
    }

    public boolean isFirstPlayersTurn() {
        return firstPlayersTurn;
    }

    public static void main(String[] args)
    {
        Connect4Game connect4Game = new Connect4Game("R", "Y", 6, 7);
        connect4Game.getGrid().printGrid();

        connect4Game.playRound(1);
        connect4Game.getGrid().printGrid();

        connect4Game.playRound(1);
        connect4Game.getGrid().printGrid();

        connect4Game.playRound(5);
        connect4Game.getGrid().printGrid();

        connect4Game.playRound(5);
        connect4Game.getGrid().printGrid();

        connect4Game.reset();
        connect4Game.getGrid().printGrid();
    }
}
