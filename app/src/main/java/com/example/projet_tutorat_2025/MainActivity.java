package com.example.projet_tutorat_2025;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText loginField, passwordField;
    private Button loginButton;

    // Accès aux sources de données locales (SQLite)
    EtudiantDataSource etudiantDS;
    BilansDataSource bilanDS;
    private boolean isInputSafe(String input) {
        return !input.contains("'") && !input.contains("\"") && !input.contains(";") && !input.contains("=")&& !input.contains("{")&& !input.contains("(");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        loginField = findViewById(R.id.Identifiant);
        passwordField = findViewById(R.id.motdepasse);
        loginButton = findViewById(R.id.boutonConnexion);

        // Initialisation des bases de données locales
        etudiantDS = new EtudiantDataSource(this);
        bilanDS = new BilansDataSource(this);
        etudiantDS.open();
        bilanDS.open();

        initialisation();
    }

    public void initialisation() {
        // Action quand l'utilisateur clique sur Connexion
        loginButton.setOnClickListener(v -> {
            String login = loginField.getText().toString();
            String password = passwordField.getText().toString();


            if (!login.isEmpty() && !password.isEmpty()) {
                if (isInputSafe(login) && isInputSafe(password)) {
                    // Appel à la méthode pour vérifier
                    verifyLoginPassword(login, password);
                } else {
                    Toast.makeText(this, "Caractères interdits dans les identifiants", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Veuillez entrer votre identifiant et mot de passe", Toast.LENGTH_SHORT).show();
            }

        });
    }
    private void verifyLoginPassword(String login, String password) {
        // Appel API pour vérifier login/password
        RetroFitClientEtudiant.getInstance().getMyApi().verifyLoginPassword(login, password).enqueue(new Callback<Etudiant>() {
            @Override
            public void onResponse(Call<Etudiant> call, Response<Etudiant> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getId() > 0) {
                    Etudiant etudiant = response.body();

                    int idEtu = etudiant.getId();
                    String nomEtu = etudiant.getNom();

                    // Insertion ou mise à jour en base locale
                    if (!etudiantDS.isEtudiantExists(idEtu)) {
                        etudiantDS.insertEtudiant(etudiant);

                    } else {
                        etudiantDS.updateEtudiant(etudiant);
                    }
                    //Appel la méthode FetchBilan
                    fetchBilan(idEtu, login, password);

                    // Redirection à l'accueil puisque la connexion est bonne
                    Intent intent = new Intent(MainActivity.this, AccueilActivity.class);
                    intent.putExtra("idEtudiant", idEtu);
                    intent.putExtra("nomEtu", nomEtu);
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(MainActivity.this, "Identifiants incorrects", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Etudiant> call, Throwable t) {
                Log.e("API", "Erreur API : " + t.getMessage(), t);
                Toast.makeText(MainActivity.this, "Erreur de connexion", Toast.LENGTH_SHORT).show();
            }
        });


    }

    // Appel API pour récupérer les bilans après la connexion
    private void fetchBilan(int idEtu, String login, String password) {
        RetroFitClientEtudiant.getInstance().getMyApi().verifyLoginPassword1(login, password)
                .enqueue(new Callback<Bilans>() {
                    @Override
                    public void onResponse(Call<Bilans> call, Response<Bilans> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            Bilans bilans = response.body();

                            bilans.setMoyenne((bilans.getNoteEnt1() + bilans.getNoteOral1() + bilans.getNoteDossier1()) / 3);
                            bilans.setMoyenne2((bilans.getNoteOral2() + bilans.getNoteDossier2())/ 2);

                            // Insertion ou mise à jour dans la base locale
                            if (bilanDS.getBilanByEtudiantId(idEtu) != null) {
                                bilans.setId(bilanDS.getBilanByEtudiantId(idEtu).getId()); // Pour update via ID
                                bilanDS.updateBilan(bilans);
                            } else {
                                bilanDS.insertBilan(bilans);
                            }

                        }
                    }

                    @Override
                    public void onFailure(Call<Bilans> call, Throwable t) {
                        Log.e("API", "Erreur API Bilan : " + t.getMessage(), t);
                    }
                });
    }

}