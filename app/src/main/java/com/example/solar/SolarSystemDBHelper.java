package com.example.solar;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//este archivo incluye los metodos para gestionar la base de datos
public class SolarSystemDBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "SolarSystem.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + SolarSystemC.PlanetaEntry.TABLE_NAME + " (" +
                    SolarSystemC.PlanetaEntry._ID + " INTEGER PRIMARY KEY," +
                    SolarSystemC.PlanetaEntry.COLUMN_NAME_NOM_ASTRE + " TEXT," +
                    SolarSystemC.PlanetaEntry.COLUMN_NAME_TAILLE_ASTRE + " INTEGER," +
                    SolarSystemC.PlanetaEntry.COLUMN_NAME_COULEUR_ASTRE + " TEXT," +
                    SolarSystemC.PlanetaEntry.COLUMN_NAME_STATUS_ASTRE + " INTEGER," +
                    SolarSystemC.PlanetaEntry.COLUMN_NAME_NOM_IMAGE_ASTRE + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + SolarSystemC.PlanetaEntry.TABLE_NAME;

    public SolarSystemDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void insertPlanet(String name, int size, String color, int state, String imageName) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SolarSystemC.PlanetaEntry.COLUMN_NAME_NOM_ASTRE, name);
        values.put(SolarSystemC.PlanetaEntry.COLUMN_NAME_TAILLE_ASTRE, size);
        values.put(SolarSystemC.PlanetaEntry.COLUMN_NAME_COULEUR_ASTRE, color);
        values.put(SolarSystemC.PlanetaEntry.COLUMN_NAME_STATUS_ASTRE, state);
        values.put(SolarSystemC.PlanetaEntry.COLUMN_NAME_NOM_IMAGE_ASTRE, imageName);

        long newRowId = db.insert(SolarSystemC.PlanetaEntry.TABLE_NAME, null, values);

        db.close();
    }
}
