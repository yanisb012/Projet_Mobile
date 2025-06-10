package com.example.projet_tutorat_2025;

import static com.example.projet_tutorat_2025.DatabaseHelper.TABLE_BILANS;
import static com.example.projet_tutorat_2025.DatabaseHelper.TABLE_ETUDIANT;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class EtudiantDataSource {
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    // Constructeur : instancie le helper de base de données
    public EtudiantDataSource(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    // Ouvre la base de données en mode écriture
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    // Ferme la base de donnéees
    public void close() {
        dbHelper.close();
    }

    public Etudiant insertEtudiant(Etudiant etudiant) {
        ContentValues values = new ContentValues();
        values.put("id", etudiant.getId());
        values.put("nomEtu", etudiant.getNom());
        values.put("preEtu", etudiant.getPrenom());
        values.put("mailEtu", etudiant.getMail());
        values.put("telEtu", etudiant.getTel());
        values.put("adrEtu", etudiant.getAdresse());
        values.put("cpEtu", etudiant.getCodePostal());
        values.put("vilEtu", etudiant.getVille());
        values.put("logEtu", etudiant.getLogin());

        values.put("nomMaitre", etudiant.getNomMaitre());
        values.put("preMaitre", etudiant.getPrenomMaitre());
        values.put("telMaitre", etudiant.getTelMaitre());
        values.put("mailMaitre", etudiant.getMailMaitre());

        values.put("nomEnt", etudiant.getNomEntreprise());
        values.put("adrEnt", etudiant.getAdresseEntreprise());
        values.put("cpEnt", etudiant.getCodePostalEntreprise());
        values.put("vilEnt", etudiant.getVilleEntreprise());

        values.put("nomTut", etudiant.getNomTuteur());
        values.put("preTut", etudiant.getPrenomTuteur());
        values.put("telTut", etudiant.getTelTuteur());

        // Exécute la mise à jour
        int id = (int) database.insert(TABLE_ETUDIANT, null, values);
        etudiant.setId(id);

        return etudiant;
    }
    public boolean isEtudiantExists(int idEtudiant) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_ETUDIANT + " WHERE id = ?", new String[]{String.valueOf(idEtudiant)});
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }

    public void updateEtudiant(Etudiant etudiant) {
        ContentValues values = new ContentValues();
        values.put("mailEtu", etudiant.getMail());




        int rowsUpdated = database.update(TABLE_ETUDIANT, values, "id = ?",
                new String[]{String.valueOf(etudiant.getId())});

        if (rowsUpdated > 0) {
            Log.d("DEBUG_BDD", "Étudiant mis à jour avec ID: " + etudiant.getId());
        } else {
            Log.e("DEBUG_BDD", "Échec de la mise à jour pour l'étudiant ID: " + etudiant.getId());
        }
    }

    // Convertit un curseur SQLite en objet Bilans Java
    private Etudiant cursorToEtudiant(Cursor cursor) {
        int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
        String nomEtu = cursor.getString(cursor.getColumnIndexOrThrow("nomEtu"));
        String preEtu = cursor.getString(cursor.getColumnIndexOrThrow("preEtu"));
        String mailEtu = cursor.getString(cursor.getColumnIndexOrThrow("mailEtu"));
        String telEtu = cursor.getString(cursor.getColumnIndexOrThrow("telEtu"));
        String adrEtu = cursor.getString(cursor.getColumnIndexOrThrow("adrEtu"));
        String cpEtu = cursor.getString(cursor.getColumnIndexOrThrow("cpEtu"));
        String vilEtu = cursor.getString(cursor.getColumnIndexOrThrow("vilEtu"));
        String logEtu = cursor.getString(cursor.getColumnIndexOrThrow("logEtu"));

        String nomMaitre = cursor.getString(cursor.getColumnIndexOrThrow("nomMaitre"));
        String prenMaitre = cursor.getString(cursor.getColumnIndexOrThrow("preMaitre"));
        String telMaitre = cursor.getString(cursor.getColumnIndexOrThrow("telMaitre"));
        String mailMaitre = cursor.getString(cursor.getColumnIndexOrThrow("mailMaitre"));

        String nomEnt = cursor.getString(cursor.getColumnIndexOrThrow("nomEnt"));
        String adrEnt = cursor.getString(cursor.getColumnIndexOrThrow("adrEnt"));
        String cpEnt = cursor.getString(cursor.getColumnIndexOrThrow("cpEnt"));
        String vilEnt = cursor.getString(cursor.getColumnIndexOrThrow("vilEnt"));

        String nomTut = cursor.getString(cursor.getColumnIndexOrThrow("nomTut"));
        String preTut = cursor.getString(cursor.getColumnIndexOrThrow("preTut"));
        String telTut = cursor.getString(cursor.getColumnIndexOrThrow("telTut"));



        return new Etudiant(id, nomEtu, preEtu, mailEtu, telEtu, adrEtu, cpEnt, vilEtu, logEtu,
                nomEnt, adrEnt, cpEnt, vilEnt,
                nomMaitre, prenMaitre, telMaitre, mailMaitre,nomTut, preTut,telTut, true);
    }

    public ArrayList<Etudiant> getAllEtudiants() {
        ArrayList<Etudiant> etudiants = new ArrayList<>();
        Cursor cursor = database.query(true, TABLE_ETUDIANT, new String[]{
                        "id", "nomEtu", "preEtu", "mailEtu", "telEtu", "adrEtu", "cpEtu", "vilEtu", "logEtu",
                        "nomMaitre", "preMaitre", "telMaitre", "mailMaitre","nomTut","preTut","telTut",
                        "nomEnt", "adrEnt", "cpEnt", "vilEnt"},
                null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            Etudiant etudiant = cursorToEtudiant(cursor);
            etudiants.add(etudiant);
        }
        cursor.close();
        return etudiants;
    }

    public Etudiant getEtudiantById(int id) {
        Etudiant etudiant = null;
        Cursor cursor = database.query(true, TABLE_ETUDIANT, new String[]{
                        "id", "nomEtu", "preEtu", "mailEtu", "telEtu", "adrEtu", "cpEtu", "vilEtu", "logEtu",
                        "nomMaitre", "preMaitre", "telMaitre", "mailMaitre","nomTut","preTut","telTut",
                        "nomEnt", "adrEnt", "cpEnt", "vilEnt"},
                "id = ?", new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor.moveToFirst()) {
            etudiant = cursorToEtudiant(cursor);
        }
        cursor.close();
        return etudiant;
    }

    public void deleteEtudiant(Etudiant etudiant) {
        int id = etudiant.getId();
        database.delete(TABLE_ETUDIANT, "id = ?", new String[]{String.valueOf(id)});
    }
}
