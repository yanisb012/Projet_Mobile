    package com.example.projet_tutorat_2025;



    import android.content.Context;
    import android.database.sqlite.SQLiteDatabase;
    import android.database.sqlite.SQLiteOpenHelper;
    import android.util.Log;

    public class DatabaseHelper extends SQLiteOpenHelper {

        public static final String TABLE_ETUDIANT = "Etudiant";
        public static final String TABLE_BILANS = "Bilans";
        private static final String DATABASE_NAME = "FSI.db";
        private static final int DATABASE_VERSION = 29; //Incrémenter de 1 pour maj de la structure


        private static final String DATABASE_CREATE_ETUDIANT = "CREATE TABLE IF NOT EXISTS "
                + TABLE_ETUDIANT + "(id INTEGER PRIMARY KEY , "
                + "nomEtu TEXT NOT NULL, "
                + "preEtu TEXT NOT NULL, "
                + "mailEtu TEXT, "
                + "telEtu TEXT, "
                + "adrEtu TEXT, "
                + "cpEtu TEXT, "
                + "vilEtu TEXT, "
                + "logEtu TEXT,"
                + "nomMaitre TEXT, "
                + "preMaitre TEXT, "
                + "telMaitre TEXT, "
                + "mailMaitre TEXT, "
                + "nomEnt TEXT, "
                + "adrEnt TEXT, "
                + "cpEnt TEXT, "
                + "vilEnt TEXT,"
                + "nomTut TEXT, "
                + "preTut TEXT,"
                + "telTut TEXT);";


        private static final String DATABASE_CREATE_BILAN = "CREATE TABLE IF NOT EXISTS "
                + TABLE_BILANS + "(id INTEGER PRIMARY KEY , "
                + "id_etudiant INTEGER, "
                + "datBil1 TEXT, "
                + "noteEnt1 REAL, "
                + "noteDos1 REAL, "
                + "noteOral1 REAL, "
                + "moyBil1 REAL, "
                + "remaBil1 TEXT, "
                + "datBil2 TEXT, "
                + "noteDos2 REAL, "
                + "noteOral2 REAL, "
                + "moyBil2 REAL, "
                + "remaBil2 TEXT, "
                + "sujMem TEXT, "
                + "FOREIGN KEY(id_etudiant) REFERENCES " + TABLE_ETUDIANT + "(id) ON DELETE CASCADE);";


        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        public void clearAllData() {
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL("DELETE FROM " + TABLE_BILANS);
            db.execSQL("DELETE FROM " + TABLE_ETUDIANT);
            Log.d("BDD", "Toutes les données ont été supprimées !");
        }


        @Override
        public void onCreate(SQLiteDatabase database) {
            database.execSQL(DATABASE_CREATE_ETUDIANT);
            database.execSQL(DATABASE_CREATE_BILAN);
        }

        //Appelé quand on augmente la version de la BDD et recréer les tables à jour
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(DatabaseHelper.class.getName(),
                    "Mise à niveau de la base de données de la version " + oldVersion + " à "
                            + newVersion + ", ce qui supprimera toutes les anciennes données");

            db.execSQL("DROP TABLE IF EXISTS " + TABLE_BILANS);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_ETUDIANT);
            onCreate(db);
        }
    }


