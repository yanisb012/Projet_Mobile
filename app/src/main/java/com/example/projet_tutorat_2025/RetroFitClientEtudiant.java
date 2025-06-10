package com.example.projet_tutorat_2025;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroFitClientEtudiant {
    // Singleton : une seule instance retrofit
    private static RetroFitClientEtudiant instance = null;
    // Interface Retrofit avec les méthodes API
    private API myApi;

    //Constructeur
    private RetroFitClientEtudiant() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.BASE_URL)// URL définie dans l'interface API
                .addConverterFactory(GsonConverterFactory.create())// Conversion JSON -> objets Java
                .build();
        myApi = retrofit.create(API.class); // Création de l’implémentation réelle de l’interface API
    }

    // Singleton : on renvoie toujours la même instance
    public static RetroFitClientEtudiant getInstance() {
        if (instance == null) {
            instance = new RetroFitClientEtudiant();
        }
        return instance;
    }
    
    // Retourne l'API prête à être utilisée
    public API getMyApi() {
        return myApi;
    }
}
