package com.example.projetointerdisciplinar;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
public interface ApiService {
    @GET("api/user/verifyaccount")
    Call<Boolean> verifyAccount(@Query("email") String email, @Query("senha") String senha);

    @GET("api/user/getByEmail")
    Call<Usuario> getUserById(@Query("email") String email);
}