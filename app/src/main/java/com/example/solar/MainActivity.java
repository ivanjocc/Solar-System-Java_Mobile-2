package com.example.solar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // verificar primera vez que se ejecuta la aplicacion
        SharedPreferences prefs = getSharedPreferences("com.example.solar.prefs", MODE_PRIVATE);
        boolean isFirstRun = prefs.getBoolean("isFirstRun", true);

        if (isFirstRun) {
            insertPlanetData();

            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("isFirstRun", false);
            editor.apply();
        }
    }

    private void insertPlanetData() {
        SolarSystemDBHelper dbHelper = new SolarSystemDBHelper(this);

        dbHelper.insertPlanet("Mercury", 40, "Grey", 1, "mercury");
        dbHelper.insertPlanet("Venus", 60, "Yellow", 1, "venus");
        dbHelper.insertPlanet("Earth", 70, "Blue", 1, "earth");
        dbHelper.insertPlanet("Mars", 50, "Red", 1, "mars");
        dbHelper.insertPlanet("Jupiter", 140, "Brown", 1, "jupiter");
        dbHelper.insertPlanet("Saturn", 120, "Yellow", 1, "saturn");
        dbHelper.insertPlanet("Uranus", 100, "LightBlue", 1, "uranus");
        dbHelper.insertPlanet("Neptune", 100, "Blue", 1, "neptune");
        dbHelper.insertPlanet("Pluto", 30, "Brown", 0, "pluto");

    }
}