package com.example.projet_tutorat_2025;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class InformationActivity extends AppCompatActivity {

    private String nomEtu;
    TextView lienAccueili, lienBilansi;
    ImageView boutonDecoi;
    private TextView nomEtudiant, prenomEtudiant, emailEtudiant, telEtudiant, adresse;
    private TextView nomMaitre, prenomMaitre, telMaitre, mailMaitre;
    private TextView nomEntreprise, adresseEntreprise;
    private TextView nomTuteur, prenomTuteur;
    private int idEtu;
    EtudiantDataSource etudiantBdd = new EtudiantDataSource(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        lienAccueili= findViewById(R.id.lienAccueili);
        lienBilansi = findViewById(R.id.lienBilani);
        boutonDecoi = findViewById(R.id.boutonDecoi);

        initialisation();
    }
    private void initialisation() {

        etudiantBdd.open();
        idEtu = getIntent().getIntExtra("idEtudiant", -1);


        nomEtudiant = findViewById(R.id.nom);
        prenomEtudiant = findViewById(R.id.prenom);
        emailEtudiant = findViewById(R.id.mail);
        telEtudiant = findViewById(R.id.tel);
        adresse=findViewById(R.id.adresse);

        nomMaitre = findViewById(R.id.nommai);
        prenomMaitre = findViewById(R.id.premai);
        telMaitre = findViewById(R.id.telmai);
        mailMaitre=findViewById(R.id.mailmai);

        nomEntreprise = findViewById(R.id.noment);
        adresseEntreprise = findViewById(R.id.adrent);

        nomTuteur=findViewById(R.id.nomtut);
        prenomTuteur=findViewById(R.id.pretut);

        Etudiant etudiant = etudiantBdd.getEtudiantById(idEtu);
        if (etudiant != null) {
            nomEtu = etudiant.getNom();
            nomEtudiant.setText(etudiant.getNom());
            prenomEtudiant.setText(etudiant.getPrenom());
            emailEtudiant.setText(etudiant.getMail());
            adresse.setText(etudiant.getAdresse() + ", " + etudiant.getCodePostal() + " " + etudiant.getVille());
            telEtudiant.setText(etudiant.getTel());

            nomMaitre.setText(etudiant.getNomMaitre());
            prenomMaitre.setText(etudiant.getPrenomMaitre());
            mailMaitre.setText(etudiant.getMailMaitre());
            telMaitre.setText(etudiant.getTelMaitre());

            nomEntreprise.setText(etudiant.getNomEntreprise());
            adresseEntreprise.setText(etudiant.getAdresseEntreprise() + ", " + etudiant.getCodePostalEntreprise() + " " + etudiant.getVilleEntreprise());

            nomTuteur.setText(etudiant.getNomTuteur());
            prenomTuteur.setText(etudiant.getPrenomTuteur());
        }
        else {
            nomEtudiant.setText("Aucune donnée à afficher");
            prenomEtudiant.setText("Aucune donnée à afficher");
            emailEtudiant.setText("Aucune donnée à afficher");
            adresse.setText("Aucune donnée à afficher");
            telEtudiant.setText("Aucune donnée à afficher");

            nomMaitre.setText("Aucune donnée à afficher");
            prenomMaitre.setText("Aucune donnée à afficher");
            mailMaitre.setText("Aucune donnée à afficher");
            telMaitre.setText("Aucune donnée à afficher");

            nomEntreprise.setText("Aucune donnée à afficher");
            adresseEntreprise.setText("Aucune donnée à afficher");

            nomTuteur.setText("Aucune donnée à afficher");
            prenomTuteur.setText("Aucune donnée à afficher");
        }

        View.OnClickListener AccueilListener = v -> {
            Intent intent = new Intent(InformationActivity.this, AccueilActivity.class);
            intent.putExtra("idEtudiant", idEtu);
            intent.putExtra("nomEtu", nomEtu);
            startActivity(intent);
        };

        View.OnClickListener bilanListener = v -> {
            Intent intent = new Intent(InformationActivity.this, NotesActivity.class);
            intent.putExtra("idEtudiant", idEtu);
            intent.putExtra("nomEtu", nomEtu);
            startActivity(intent);
        };

        // Déconnexion
        View.OnClickListener decoListener = v -> {
            Intent intent = new Intent(InformationActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        };

        lienAccueili.setOnClickListener(AccueilListener);
        lienBilansi.setOnClickListener(bilanListener);
        boutonDecoi.setOnClickListener(decoListener);

    }
}
