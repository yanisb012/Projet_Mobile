package com.example.projet_tutorat_2025;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class NotesActivity extends AppCompatActivity {
    private String nomEtu;
    private int idEtu;
    TextView lienAccueil, lienInfos;
    private TextView datebilan1,noteEnt1, noteDossier1, noteOral1, moyenne1,remarque1;
    private TextView datebilan2,noteDossier2, noteOral2, moyenne2, remarque2, sujetmemoire;
    private TextView moyennetotal;
    ImageView boutonDeco;
    EtudiantDataSource etudiantBdd = new EtudiantDataSource(this);
    BilansDataSource bilanBdd = new BilansDataSource(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        lienAccueil= findViewById(R.id.lienAccueiln);
        lienInfos = findViewById(R.id.lienInfosn);
        boutonDeco = findViewById(R.id.boutonDecon);

        initialisation();
    }

    private void initialisation() {
        etudiantBdd.open();
        bilanBdd.open();
        idEtu = getIntent().getIntExtra("idEtudiant", -1);

        datebilan1 = findViewById(R.id.dateBilan1);
        noteEnt1 = findViewById(R.id.noteEnt1);
        noteDossier1 = findViewById(R.id.noteDossier1);
        noteOral1 = findViewById(R.id.noteOral1);
        moyenne1 = findViewById(R.id.moyenne1);
        remarque1 = findViewById(R.id.remarque1);

        datebilan2 = findViewById(R.id.dateBilan2);
        noteDossier2 = findViewById(R.id.noteDossier2);
        noteOral2 = findViewById(R.id.noteOral2);
        moyenne2 = findViewById(R.id.moyenne2);
        remarque2 = findViewById(R.id.remarque2);
        sujetmemoire = findViewById(R.id.sujetMemoire);

        moyennetotal=findViewById(R.id.moyennetotale);

        Bilans bilans = bilanBdd.getBilanByEtudiantId(idEtu);

        if (bilans != null) {
            datebilan1.setText(bilans.getDatebilan1());
            noteEnt1.setText(String.valueOf(bilans.getNoteEnt1()));
            noteDossier1.setText(String.valueOf(bilans.getNoteDossier1()));
            noteOral1.setText(String.valueOf(bilans.getNoteOral1()));
            moyenne1.setText(String.format(Locale.getDefault(), "%.2f", bilans.getMoyenne()));
            remarque1.setText(bilans.getRemarque1());

            datebilan2.setText(bilans.getDatebilan2());
            noteDossier2.setText(String.valueOf(bilans.getNoteDossier2()));
            noteOral2.setText(String.valueOf(bilans.getNoteOral2()));
            moyenne2.setText(String.format(Locale.getDefault(), "%.2f", bilans.getMoyenne2()));
            sujetmemoire.setText(bilans.getSujetmemoire());
            remarque2.setText(bilans.getRemarque2());


            double moyenne1=bilans.getMoyenne();
            double moyenne2=bilans.getMoyenne2();
            double moyennetotale=(moyenne1*4+moyenne2*6)/10;
            moyennetotal.setText(String.format(Locale.getDefault(), "%.2f", moyennetotale));
        }
        else {
            datebilan1.setText("Aucune donnée à afficher");
            noteEnt1.setText("Aucune donnée à afficher");
            noteDossier1.setText("Aucune donnée à afficher");
            noteOral1.setText("Aucune donnée à afficher");
            moyenne1.setText("Aucune donnée à afficher");
            remarque1.setText("Aucune donnée à afficher");

            datebilan2.setText("Aucune donnée à afficher");
            noteDossier2.setText("Aucune donnée à afficher");
            noteOral2.setText("Aucune donnée à afficher");
            moyenne2.setText("Aucune donnée à afficher");
            sujetmemoire.setText("Aucune donnée à afficher");
            remarque2.setText("Aucune donnée à afficher");

            moyennetotal.setText("Aucune donnée à afficher");
        }



        View.OnClickListener AccueilListeners = v -> {
            Intent intent = new Intent(NotesActivity.this, AccueilActivity.class);
            intent.putExtra("idEtudiant", idEtu);
            intent.putExtra("nomEtu", nomEtu);
            startActivity(intent);
        };

        View.OnClickListener InfoListener = v -> {
            Intent intent = new Intent(NotesActivity.this, InformationActivity.class);
            intent.putExtra("idEtudiant", idEtu);
            intent.putExtra("nomEtu", nomEtu);
            startActivity(intent);
        };

        View.OnClickListener deconListener = v -> {
            Intent intent = new Intent(NotesActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        };

        lienAccueil.setOnClickListener(AccueilListeners);
        lienInfos.setOnClickListener(InfoListener);
        boutonDeco.setOnClickListener(deconListener);
        }
}
