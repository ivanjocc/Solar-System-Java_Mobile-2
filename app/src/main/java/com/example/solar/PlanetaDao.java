package com.example.solar;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class PlanetaDao {
    private SolarSystemDBHelper dbHelper;

    public PlanetaDao(Context context) {
        this.dbHelper = new SolarSystemDBHelper(context);
    }

    public List<AstreCeleste> getAllPlanets() {
        List<AstreCeleste> planetList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                SolarSystemC.PlanetaEntry._ID,
                SolarSystemC.PlanetaEntry.COLUMN_NAME_NOM_ASTRE,
                SolarSystemC.PlanetaEntry.COLUMN_NAME_TAILLE_ASTRE,
                SolarSystemC.PlanetaEntry.COLUMN_NAME_COULEUR_ASTRE,
                SolarSystemC.PlanetaEntry.COLUMN_NAME_STATUS_ASTRE,
                SolarSystemC.PlanetaEntry.COLUMN_NAME_NOM_IMAGE_ASTRE
        };

        Cursor cursor = db.query(
                SolarSystemC.PlanetaEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            AstreCeleste planet = new AstreCeleste();
            planet.setId(cursor.getInt(cursor.getColumnIndexOrThrow(SolarSystemC.PlanetaEntry._ID)));
            planet.setName(cursor.getString(cursor.getColumnIndexOrThrow(SolarSystemC.PlanetaEntry.COLUMN_NAME_NOM_ASTRE)));
            planet.setSize(cursor.getInt(cursor.getColumnIndexOrThrow(SolarSystemC.PlanetaEntry.COLUMN_NAME_TAILLE_ASTRE)));
            planet.setColor(cursor.getString(cursor.getColumnIndexOrThrow(SolarSystemC.PlanetaEntry.COLUMN_NAME_COULEUR_ASTRE)));
            planet.setState(cursor.getInt(cursor.getColumnIndexOrThrow(SolarSystemC.PlanetaEntry.COLUMN_NAME_STATUS_ASTRE)));
            planet.setImageName(cursor.getString(cursor.getColumnIndexOrThrow(SolarSystemC.PlanetaEntry.COLUMN_NAME_NOM_IMAGE_ASTRE)));
            planetList.add(planet);
        }
        cursor.close();

        return planetList;
    }
}
