package com.example.projet_tutorat_2025;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AccueilActivity extends AppCompatActivity {
    EtudiantDataSource dataSource;
    private String nomEtu;
    private int idEtu;

    private TextView affichagenom;
    private Button boutonInfo;
    private Button boutonNotes;
    private ImageView boutonDeco;
    private TextView lienBilan, lienInfos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        nomEtu = getIntent().getStringExtra("nomEtu");
        idEtu = getIntent().getIntExtra("idEtudiant", -1);

        Log.d("AccueilActivity", "Nom de l'utilisateur : " + nomEtu +" | ID : " + idEtu);
        dataSource = new EtudiantDataSource(this);
        dataSource.open();

        initialisation();
    }

    private void initialisation() {
        affichagenom = findViewById(R.id.nometu);
        boutonNotes = findViewById(R.id.Boutonnotes);
        boutonInfo = findViewById(R.id.Boutoninfo);
        boutonDeco = findViewById(R.id.boutonDeco);
        lienBilan = findViewById(R.id.lienBilan);
        lienInfos = findViewById(R.id.lienInfos);

        // Aller à la page des notes
        View.OnClickListener notesListener = v -> {
            Intent intent = new Intent(AccueilActivity.this, NotesActivity.class);
            intent.putExtra("idEtudiant", idEtu);
            intent.putExtra("nomEtu", nomEtu);
            startActivity(intent);
        };

        // Aller à la page des informations personnelles
        View.OnClickListener infoListener = v -> {
            Intent intent = new Intent(AccueilActivity.this, InformationActivity.class);
            intent.putExtra("idEtudiant", idEtu);
            intent.putExtra("nomEtu", nomEtu);
            startActivity(intent);
        };

        // Déconnexion
        View.OnClickListener decoListener = v -> {
            Intent intent = new Intent(AccueilActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        };

        View.OnClickListener notesheaderListener = v -> {
            Intent intent = new Intent(AccueilActivity.this, NotesActivity.class);
            intent.putExtra("idEtudiant", idEtu);
            intent.putExtra("nomEtu", nomEtu);
            startActivity(intent);
        };

        View.OnClickListener infoheaderListener = v -> {
            Intent intent = new Intent(AccueilActivity.this, InformationActivity.class);
            intent.putExtra("idEtudiant", idEtu);
            intent.putExtra("nomEtu", nomEtu);
            startActivity(intent);
        };


        // Associer les listeners
        boutonNotes.setOnClickListener(notesListener);
        boutonInfo.setOnClickListener(infoListener);
        lienBilan.setOnClickListener(notesheaderListener);
        lienInfos.setOnClickListener(infoheaderListener);
        boutonDeco.setOnClickListener(decoListener);
    }
}