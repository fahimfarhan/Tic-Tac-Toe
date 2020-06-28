package com.ayeshapp.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.ayeshapp.tictactoe.gameviewmodel.GameViewModel;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {


    private Button reset;
    private boolean win = false;

    private GameViewModel gameViewModel;

    private TextView[][] textView = new TextView[3][3];

    private int turn = 0;
    private String[] symbol = {"X","0"};
    private int[] player = {1,2};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameViewModel = new ViewModelProvider(MainActivity.this).get(GameViewModel.class);

        win = gameViewModel.getGridLiveData().getValue().isWin();


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

     // todo: reset the current state
        int[][] tempGrid = gameViewModel.getGridLiveData().getValue().getGrid();
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                if(tempGrid[i][j] == 1) {
                    textView[i][j].setText("X");
                }else if(tempGrid[i][j] == 2){
                    textView[i][j].setText("0");
                }
            }
        }

        if(gameViewModel.getGridLiveData().getValue().isWin()) {
            tempGrid = gameViewModel.getGridLiveData().getValue().getState();
            for(int i=0; i<3; i++) {
                for(int j=0; j<3; j++) {
                    if(tempGrid[i][j] == 1) {
                        textView[i][j].setBackgroundColor(Color.parseColor("#00C853"));
                    }
                }
            }
        }

    }

    public boolean move(final int x, final int y) {
        if(!win && gameViewModel.getGridLiveData().getValue().getNumber(x,y) == 0) {
            gameViewModel.getGridLiveData().getValue().setNumber(x,y, player[turn]);
//            grid[x][y] = player[turn];
            if (turn == 0) {
                Animation animation = AnimationUtils.loadAnimation(this, R.anim.fadein);
                textView[x][y].setText(symbol[turn]);
                checkWin(x,y);
                turn = 1 - turn;
                if (turn == 1) moveComputer();
                textView[x][y].startAnimation(animation);
            } else {
                Handler hd = new Handler();
                hd.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        textView[x][y].setText(symbol[turn]);
                        checkWin(x,y);
                        turn = 1 - turn;
                        if (turn == 1) moveComputer();
                    }
                }, 500);
            }
            /*checkWin(x,y);
            turn = 1 - turn;
            if (turn == 1) moveComputer();*/
            return true;
        }
        return false;
    }

    public void moveComputer() {
        ArrayList<Pair<Integer,Integer>> empty = new ArrayList<>();
        int cnt = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
//                if (grid[i][j] == 0) {
                if(gameViewModel.getGridLiveData().getValue().getNumber(i,j) == 0){
                    cnt++;
                    Pair<Integer,Integer> p = new Pair<>(i,j);
//                    grid[i][j] = player[turn];
                    gameViewModel.getGridLiveData().getValue().setNumber(i,j, player[turn]);
                    if (checkWin(i,j, player[turn])) {
//                        grid[i][j] = 0;
                        gameViewModel.getGridLiveData().getValue().setNumber(i,j, 0);
                        move(i,j);
                        return;
                    }
//                    grid[i][j] = player[1-turn];
                    gameViewModel.getGridLiveData().getValue().setNumber(i,j, player[1-turn]);
                    if (checkWin(i,j, player[1-turn])) {
//                        grid[i][j] = 0;
                        gameViewModel.getGridLiveData().getValue().setNumber(i,j, 0);
                        move(i,j);
                        return;
                    }
//                    grid[i][j] = 0;
                    gameViewModel.getGridLiveData().getValue().setNumber(i,j, 0);
                    empty.add(p);
                }
            }
        }
        if (cnt == 0) return;
        Random rn = new Random();
        int index = rn.nextInt(cnt);
        Pair<Integer,Integer> p = empty.get(index);

        move(p.first,p.second);
    }


    public void resetGrid() {
        win = false;
        turn = 0;
        gameViewModel.getGridLiveData().getValue().resetGrid();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
//                grid[i][j] = 0;
                textView[i][j].setText("");
                textView[i][j].setBackgroundColor(Color.parseColor("#E0E0E0"));
            }
        }

    }

    private boolean checkWin(int x, int y, int find) {
        boolean flag = false;
        //horizonta
        for (int i = 0; i < 3; i++) {
//            if (grid[x][i] != find) {
            if(gameViewModel.getGridLiveData().getValue().getNumber(x,i) != find) {
                flag = true;
                break;
            }
        }
        if (flag) {
            flag = false;
        } else {
            //gameViewModel.getGridLiveData().getValue().setWin(true);
            return true;
        }

        //vertical
        for (int i = 0; i < 3; i++) {
//            if (grid[i][y] != find) {
            if(gameViewModel.getGridLiveData().getValue().getNumber(i,y) != find) {
                flag = true;
                break;
            }
        }

        if (flag) {
            flag = false;
        } else {
            //gameViewModel.getGridLiveData().getValue().setWin(true);
            return true;
        }

        //diagonal
        if (x == y) {
            for (int i = 0; i < 3; i++) {
//                if (grid[i][i] != find) {
                if(gameViewModel.getGridLiveData().getValue().getNumber(i,i) != find) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                flag = false;
            } else {
                //gameViewModel.getGridLiveData().getValue().setWin(true);
                return true;
            }
        }



        //antidiagonal
        if (x + y == 2) {
            for (int i = 0; i < 3; i++) {
//                if (grid[i][2-i] != find) {
                if(gameViewModel.getGridLiveData().getValue().getNumber(i,(2 - i) )!= find) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                flag = false;
            } else {
                //gameViewModel.getGridLiveData().getValue().setWin(true);
                return true;
            }
        }
        return false;
    }

    private void checkWin(int x, int y) {
//        int find = grid[x][y];
        int find = gameViewModel.getGridLiveData().getValue().getNumber(x,y);
        boolean flag = false;
        //horizonta
        for (int i = 0; i < 3; i++) {
//            if (grid[x][i] != find) {
            if(gameViewModel.getGridLiveData().getValue().getNumber(x,i) != find) {
                flag = true;
                break;
            }
        }
        if (flag) {
            flag = false;
        } else {
            gameViewModel.getGridLiveData().getValue().setWin(true);
            win = true;
            for (int i = 0; i < 3; i++) {
                textView[x][i].setBackgroundColor(Color.parseColor("#00C853"));
                gameViewModel.getGridLiveData().getValue().setState(x,i,1);
            }
        }

        //vertical
        for (int i = 0; i < 3; i++) {
//            if (grid[i][y] != find) {
            if(gameViewModel.getGridLiveData().getValue().getNumber(i,y) != find) {
                flag = true;
                break;
            }
        }

        if (flag) {
            flag = false;
        } else {
            win = true;
            gameViewModel.getGridLiveData().getValue().setWin(true);
            for (int i = 0; i < 3; i++) {
                textView[i][y].setBackgroundColor(Color.parseColor("#00C853"));
                gameViewModel.getGridLiveData().getValue().setState(i,y,1);
            }
        }

        //diagonal
        if (x == y) {
            for (int i = 0; i < 3; i++) {
//                if (grid[i][i] != find) {
                if(gameViewModel.getGridLiveData().getValue().getNumber(i,i) != find) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                flag = false;
            } else {
                win = true;
                gameViewModel.getGridLiveData().getValue().setWin(true);
                for (int i = 0; i < 3; i++) {
                    textView[i][i].setBackgroundColor(Color.parseColor("#00C853"));
                    gameViewModel.getGridLiveData().getValue().setState(i,i,1);
                }
            }
        }



        //antidiagonal
        if (x + y == 2) {
            for (int i = 0; i < 3; i++) {
//                if (grid[i][2-i] != find) {
                if(gameViewModel.getGridLiveData().getValue().getNumber(i, (2-i) ) != find) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                flag = false;
            } else {
                win = true;
                gameViewModel.getGridLiveData().getValue().setWin(true);
                for (int i = 0; i < 3; i++) {
                    textView[i][2-i].setBackgroundColor(Color.parseColor("#00C853"));
                    gameViewModel.getGridLiveData().getValue().setState(i,2-i,1);
                }
            }
        }
    }
}
