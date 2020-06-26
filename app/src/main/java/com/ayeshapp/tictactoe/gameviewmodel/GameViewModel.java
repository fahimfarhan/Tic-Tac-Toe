package com.ayeshapp.tictactoe.gameviewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

public class GameViewModel extends AndroidViewModel {

    MutableLiveData<Grid> gridLiveData;

    public GameViewModel(@NonNull Application application) {
        super(application);
        init();
    }

    private void init() {
        if(gridLiveData == null) {
            gridLiveData = new MutableLiveData<>();
            Grid grid = new Grid();
            gridLiveData.setValue(grid);
        }
    }

    public MutableLiveData<Grid> getGridLiveData() { return gridLiveData; }
    public void setValue(int i, int j, int number) {
        gridLiveData.getValue().setNumber(i,j,number);
    }
}
