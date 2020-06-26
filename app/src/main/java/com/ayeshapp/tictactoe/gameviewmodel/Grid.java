package com.ayeshapp.tictactoe.gameviewmodel;

public class Grid {
    private int[][] grid = {
            {0,0,0},
            {0,0,0},
            {0,0,0},
    };

    public Grid(){}
    // public apis

    public int[][] getGrid() {
        return grid;
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

    public void resetGrid() {
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                grid[i][j] = 0;
            }
        }
    }
}
