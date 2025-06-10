package com.example.projet_tutorat_2025;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Field;

public interface API {
    String BASE_URL = "https://projetfsi.alwaysdata.net/vue/";

    @FormUrlEncoded  // Envoie login + mot de passe à l'API et reçoit un objet Etudiant
    @POST("PageAPIYanis.php")
    Call<Etudiant> verifyLoginPassword(@Field("login") String login, @Field("password") String password);

    @FormUrlEncoded  // Envoie login + mot de passe à l'API et reçoit un objet Bilan
    @POST("PageAPIYanis.php")
    Call<Bilans> verifyLoginPassword1(@Field("login") String login, @Field("password") String password);


}
