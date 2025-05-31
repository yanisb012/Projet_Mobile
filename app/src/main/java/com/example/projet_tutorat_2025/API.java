package com.example.projet_tutorat_2025;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Field;

public interface API {
    String BASE_URL = "https://projetfsi.alwaysdata.net/vue/";

    @FormUrlEncoded
    @POST("PageAPIYanis.php")
    Call<Etudiant> verifyLoginPassword(@Field("login") String login, @Field("password") String password);

    @FormUrlEncoded
    @POST("PageAPIYanis.php")
    Call<Bilans> verifyLoginPassword1(@Field("login") String login, @Field("password") String password);


}
