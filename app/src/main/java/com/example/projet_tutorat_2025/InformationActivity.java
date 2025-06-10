package com.example.projet_tutorat_2025;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class InformationActivity extends AppCompatActivity {

    private String nomEtu;
    private int idEtu;
    TextView lienAccueili, lienBilansi;
    ImageView boutonDecoi;
    private Button BoutonEnregistrer;
    private TextView nomEtudiant, prenomEtudiant, telEtudiant, adresse;
    private EditText emailEtudiant;
    private TextView nomMaitre, prenomMaitre, telMaitre, mailMaitre;
    private TextView nomEntreprise, adresseEntreprise;
    private TextView nomTuteur, prenomTuteur,telTuteur;
    EtudiantDataSource etudiantBdd = new EtudiantDataSource(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        lienAccueili= findViewById(R.id.lienAccueili);
        lienBilansi = findViewById(R.id.lienBilani);
        boutonDecoi = findViewById(R.id.boutonDecoi);
        BoutonEnregistrer=findViewById(R.id.BoutonEnregistrer);

        initialisation();
    }
    private void initialisation() {

        etudiantBdd.open();
        //Récupère les données envoyés par MainActivity avec Intent
        idEtu = getIntent().getIntExtra("idEtudiant", -1);


        //Lie les id de l'ihm aux attribus définis pour lier les données
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
        telTuteur=findViewById(R.id.teltut);

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
            telTuteur.setText(etudiant.getTelTuteur());
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
            telTuteur.setText("Aucune donnée à afficher");
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

        View.OnClickListener btnEnregistrer = v -> {
            String nouveauMail=emailEtudiant.getText().toString();
            //Récupére l'objet etudiant
            Etudiant etudiantUpdate =etudiantBdd.getEtudiantById(idEtu);
            //Maj l'étudiant si pas vide
            if (etudiantUpdate !=null) {
                etudiantUpdate.setMail(nouveauMail);
                etudiantBdd.updateEtudiant(etudiantUpdate);
                Toast.makeText(InformationActivity.this, "Adresse mail mise à jour", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(InformationActivity.this, "Erreur dans la modification du mail", Toast.LENGTH_SHORT).show();
            }
        };

        lienAccueili.setOnClickListener(AccueilListener);
        lienBilansi.setOnClickListener(bilanListener);
        boutonDecoi.setOnClickListener(decoListener);
        BoutonEnregistrer.setOnClickListener(btnEnregistrer);

    }
}
