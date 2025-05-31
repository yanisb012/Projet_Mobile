package com.example.projet_tutorat_2025;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class BilansDataSource {
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    public BilansDataSource(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }
    public boolean isBilanExists(int id) {
        Cursor cursor = database.rawQuery("SELECT 1 FROM " + DatabaseHelper.TABLE_BILANS + " WHERE id = ?", new String[]{String.valueOf(id)});
        boolean exists = cursor.moveToFirst();
        cursor.close();
        return exists;
    }
    public void updateBilan(Bilans bilans) {
        ContentValues values = new ContentValues();
        values.put("datBil1", bilans.getDatebilan1());
        values.put("noteEnt1", bilans.getNoteEnt1());
        values.put("noteDos1", bilans.getNoteDossier1());
        values.put("noteOral1", bilans.getNoteOral1());
        values.put("moyBil1", bilans.getMoyenne());
        values.put("remaBil1", bilans.getRemarque1());

        values.put("datBil2", bilans.getDatebilan2());
        values.put("noteDos2", bilans.getNoteDossier2());
        values.put("noteOral2", bilans.getNoteOral2());
        values.put("moyBil2", bilans.getMoyenne2());
        values.put("remaBil2", bilans.getRemarque2());
        values.put("sujMem", bilans.getSujetmemoire());


        int rowsUpdated = database.update(DatabaseHelper.TABLE_BILANS, values, "id = ?", new String[]{String.valueOf(bilans.getId())});

        if (rowsUpdated > 0) {
            Log.d("DEBUG_BDD", "Bilan1 mis à jour avec ID: " + bilans.getId());
        } else {
            Log.e("DEBUG_BDD", "Échec de la mise à jour pour Bilan1 ID: " + bilans.getId());
        }
    }


    public Bilans insertBilan(Bilans bilans) {
        ContentValues values = new ContentValues();
        values.put("id_etudiant", bilans.getIdEtu());

        values.put("datBil1", bilans.getDatebilan1());
        values.put("noteEnt1", bilans.getNoteEnt1());
        values.put("noteDos1", bilans.getNoteDossier1());
        values.put("noteOral1", bilans.getNoteOral1());
        values.put("moyBil1", bilans.getMoyenne());
        values.put("remaBil1", bilans.getRemarque1());

        values.put("datBil2", bilans.getDatebilan2());
        values.put("noteDos2", bilans.getNoteDossier2());
        values.put("noteOral2", bilans.getNoteOral2());
        values.put("moyBil2", bilans.getMoyenne2());
        values.put("remaBil2", bilans.getRemarque2());
        values.put("sujMem", bilans.getSujetmemoire());


        int id = (int) database.insert(DatabaseHelper.TABLE_BILANS, null, values);
        bilans.setId(id);
        if (id == -1) {
            Log.e("DEBUG_BDD", "Insertion échouée pour Bilan !");
        } else {
            Log.d("DEBUG_BDD", "Bilan inséré avec ID: " + id);
        }

        return bilans;
    }

    private Bilans cursorToBilan(Cursor cursor) {
        int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
        int idEtu = cursor.getInt(cursor.getColumnIndexOrThrow("id_etudiant"));
        String datBil1 = cursor.getString(cursor.getColumnIndexOrThrow("datBil1"));
        float noteEnt1 = cursor.getFloat(cursor.getColumnIndexOrThrow("noteEnt1"));
        float noteDos1 = cursor.getFloat(cursor.getColumnIndexOrThrow("noteDos1"));
        float noteOral1 = cursor.getFloat(cursor.getColumnIndexOrThrow("noteOral1"));
        float moyBil1 = cursor.getFloat(cursor.getColumnIndexOrThrow("moyBil1"));
        String remaBil1 = cursor.getString(cursor.getColumnIndexOrThrow("remaBil1"));

        String datBil2 = cursor.getString(cursor.getColumnIndexOrThrow("datBil2"));
        float noteDos2 = cursor.getFloat(cursor.getColumnIndexOrThrow("noteDos2"));
        float noteOral2 = cursor.getFloat(cursor.getColumnIndexOrThrow("noteOral2"));
        float moyBil2 = cursor.getFloat(cursor.getColumnIndexOrThrow("moyBil2"));
        String remaBil2 = cursor.getString(cursor.getColumnIndexOrThrow("remaBil2"));
        String sujMem = cursor.getString(cursor.getColumnIndexOrThrow("sujMem"));
        return new Bilans(id, idEtu,datBil1, noteEnt1, noteDos1, noteOral1, moyBil1, remaBil1,
                datBil2, noteDos2, noteOral2 , moyBil2, remaBil2, sujMem);
    }

    public ArrayList<Bilans> getAllBilans() {
        ArrayList<Bilans> bilans = new ArrayList<>();
        Cursor cursor = database.query(true, DatabaseHelper.TABLE_BILANS, new String[]{
                        "id", "id_etudiant", "datBil1", "noteEnt1", "noteDos1", "noteOral1", "moyBil1", "remaBil1",
                        "datBil2", "noteDos2", "noteOral2", "moyBil2", "remaBil2", "sujMem"},
                null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            Bilans bilan = cursorToBilan(cursor);
            bilans.add(bilan);
        }
        cursor.close();
        return bilans;
    }

    public Bilans getBilanByEtudiantId(int idEtudiant) {
        Bilans bilans = null;
        Cursor cursor = database.query(true, DatabaseHelper.TABLE_BILANS, new String[]{
                        "id", "id_etudiant", "datBil1", "noteEnt1", "noteDos1", "noteOral1", "moyBil1", "remaBil1",
                        "datBil2", "noteDos2", "noteOral2", "moyBil2", "remaBil2", "sujMem"},
                "id_etudiant = ?", new String[]{String.valueOf(idEtudiant)}, null, null, null, null);

        if (cursor.moveToFirst()) {
            bilans = cursorToBilan(cursor);
        }
        cursor.close();
        return bilans;
    }

    public void deleteBilan(Bilans bilans) {
        int id = bilans.getId();
        database.delete(DatabaseHelper.TABLE_BILANS, "id = ?", new String[]{String.valueOf(id)});
    }

}
