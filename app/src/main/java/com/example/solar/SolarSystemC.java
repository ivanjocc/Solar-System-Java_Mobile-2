package com.example.solar;

import android.provider.BaseColumns;

//este archivo es el esquema de la base de datos
public class SolarSystemC {
    private SolarSystemC() {}

    public static class PlanetaEntry implements BaseColumns {
        public static final String TABLE_NAME = "planets";
        public static final String COLUMN_NAME_NOM_ASTRE = "name";
        public static final String COLUMN_NAME_TAILLE_ASTRE = "size";
        public static final String COLUMN_NAME_COULEUR_ASTRE = "color";
        public static final String COLUMN_NAME_STATUS_ASTRE = "state";
        public static final String COLUMN_NAME_NOM_IMAGE_ASTRE = "name-image";
    }
}
