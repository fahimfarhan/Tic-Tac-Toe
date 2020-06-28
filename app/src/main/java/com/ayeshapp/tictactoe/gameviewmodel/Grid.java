package com.ayeshapp.tictactoe.gameviewmodel;

public class Grid {
    private int[][] grid = {
            {0,0,0},
            {0,0,0},
            {0,0,0},
    };

    private int[][] winState = {
            {0,0,0},
            {0,0,0},
            {0,0,0},
    };

    boolean isWin;

    public Grid(){
        isWin = false;
    }
    // public apis

    public int[][] getGrid() {
        return grid;
    }
    public int[][] getState() {
        return winState;
    }

    public void setGrid(int[][] grid) {
        this.grid = grid;
    }


    public void setNumber(int i, int j, int number) {
        grid[i][j] = number;
    }

    public int getNumber(int i, int j) {
        return grid[i][j];
    }

    public void setState(int i, int j, int number) {
        winState[i][j] = number;
    }

    public int getState(int i, int j) {
        return winState[i][j];
    }

    public boolean isWin() {
        return isWin;
    }

    public void setWin(boolean win) {
        isWin = win;
    }

    public void resetGrid() {
        isWin = false;
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                grid[i][j] = 0;
            }
        }
    }
}
