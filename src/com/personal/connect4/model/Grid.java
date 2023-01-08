package com.personal.connect4.model;

public class Grid {

    private Token[][] grid;
    private int rows;
    private int columns;

    public Grid(int rows, int cols) {
        this.rows = rows;
        this.columns = cols;

        //initialize grid to null
        grid = new Token[rows][cols];
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                grid[i][j] = null;
            }
        }
    }

    public void printGrid()
    {
        for(int i=0; i<this.rows; i++){
            for(int j = 0; j<this.columns; j++){
                if(grid[i][j] == null){
                    System.out.print(". ");
                }else {
                    System.out.print(grid[i][j].getColor()+ " ");
                }
            }
            System.out.println();
        }
    }

    public int addToken(int column, String color)
    {
        //return false if column is out of range
        if(column < 0 || column > this.columns){
            System.out.println("Column is out of range, cant add !");
            return -1;
        }

        //return false if the column is already full
        if(this.grid[0][column] != null){
            System.out.println("Column is already full, cant add !");
            return -1;
        }

        for(int r=rows-1; r >= 0; r--){
            if(grid[r][column] == null){
                grid[r][column] = new Token(color);
                return r;
            }
        }
        return -1;
    }

    public void resetGrid()
    {
        for(int i=0; i<this.rows; i++){
            for(int j=0; j<this.columns; j++){
                grid[i][j] = null;
            }
        }
        System.out.println("Reset successful !");
    }

    //setters and getters
    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public Token[][] getGrid() {
        return grid;
    }

    public void setGrid(Token[][] grid) {
        this.grid = grid;
    }
}
