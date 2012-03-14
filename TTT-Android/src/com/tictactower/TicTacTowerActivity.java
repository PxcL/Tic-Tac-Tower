package com.tictactower;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;

public class TicTacTowerActivity extends AndroidApplication {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize(Game.getInstance(), false);
    }
}