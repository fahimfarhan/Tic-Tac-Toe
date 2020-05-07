package com.ayeshapp.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private Button reset;
    private boolean win = false;

    private int[][] grid = {
            {0,0,0},
            {0,0,0},
            {0,0,0},
    };

    private TextView[][] textView = new TextView[3][3];

    private int turn = 0;
    private String[] symbol = {"X","0"};
    private int[] player = {1,2};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView[0][0] = findViewById(R.id.field_00);
        textView[0][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move(0,0);
            }
        });

        textView[0][1] = findViewById(R.id.field_01);
        textView[0][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move(0,1);
            }
        });

        textView[0][2] = findViewById(R.id.field_02);
        textView[0][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move(0,2);
            }
        });

        textView[1][0] = findViewById(R.id.field_10);
        textView[1][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move(1,0);
            }
        });

        textView[1][1] = findViewById(R.id.field_11);
        textView[1][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move(1,1);
            }
        });

        textView[1][2] = findViewById(R.id.field_12);
        textView[1][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move(1,2);
            }
        });

        textView[2][0] = findViewById(R.id.field_20);
        textView[2][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move(2,0);
            }
        });

        textView[2][1] = findViewById(R.id.field_21);
        textView[2][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move(2,1);
            }
        });

        textView[2][2] = findViewById(R.id.field_22);
        textView[2][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move(2,2);
            }
        });

        reset = findViewById(R.id.reset_button);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGrid();
            }
        });

    }

    public boolean move(int x,int y) {
        if(!win && grid[x][y] == 0) {
            grid[x][y] = player[turn];
            textView[x][y].setText(symbol[turn]);
            checkWin(x,y);
            turn = 1 - turn;
            return true;
        }
        return false;
    }


    public void resetGrid() {
        win = false;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = 0;
                textView[i][j].setText("");
                textView[i][j].setBackgroundColor(Color.parseColor("#E0E0E0"));
            }
        }

    }

    private void checkWin(int x, int y) {
        int find = grid[x][y];
        boolean flag = false;
        //horizonta
        for (int i = 0; i < 3; i++) {
            if (grid[x][i] != find) {
                flag = true;
                break;
            }
        }
        if (flag) {
            flag = false;
        } else {
            win = true;
            for (int i = 0; i < 3; i++) {
                textView[x][i].setBackgroundColor(Color.parseColor("#00C853"));
            }
        }

        //vertical
        for (int i = 0; i < 3; i++) {
            if (grid[i][y] != find) {
                flag = true;
                break;
            }
        }

        if (flag) {
            flag = false;
        } else {
            win = true;
            for (int i = 0; i < 3; i++) {
                textView[i][y].setBackgroundColor(Color.parseColor("#00C853"));
            }
        }

        //diagonal
        if (x == y) {
            for (int i = 0; i < 3; i++) {
                if (grid[i][i] != find) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                flag = false;
            } else {
                win = true;
                for (int i = 0; i < 3; i++) {
                    textView[i][i].setBackgroundColor(Color.parseColor("#00C853"));
                }
            }
        }



        //antidiagonal
        if (x + y == 2) {
            for (int i = 0; i < 3; i++) {
                if (grid[i][2-i] != find) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                flag = false;
            } else {
                win = true;
                for (int i = 0; i < 3; i++) {
                    textView[i][2-i].setBackgroundColor(Color.parseColor("#00C853"));
                }
            }
        }


    }
}
