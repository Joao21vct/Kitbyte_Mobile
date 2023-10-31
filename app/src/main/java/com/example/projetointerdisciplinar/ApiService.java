package com.example.projetointerdisciplinar;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;
public interface ApiService {
    @GET("api/user/verifyaccount")
    Call<Boolean> verifyAccount(@Query("email") String email, @Query("senha") String senha);

    @GET("api/user/getByEmail")
    Call<Usuario> getUserById(@Query("email") String email);

    @PATCH("api/user/password")
    Call<Usuario> resetPassword(@Query("email") String email, @Query("password") String password);

    @POST("api/user/insert")
    Call<Usuario> registerUser(@Body HashMap user);
    @GET("api/user/email")
    Call<Usuario> verifyEmailExists(@Query("email") String email);
    @PATCH("api/user/foto")
    Call<Object> fidalgo(@Body FotoBody body);
}