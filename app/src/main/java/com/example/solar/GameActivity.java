package com.example.solar;

import android.app.Activity;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;

public class GameActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        ConstraintLayout mainLayout = findViewById(R.id.mainLayout);
        AlienSolarSystem alienSolarSystemView = new AlienSolarSystem(this);
        mainLayout.addView(alienSolarSystemView);
    }
}
