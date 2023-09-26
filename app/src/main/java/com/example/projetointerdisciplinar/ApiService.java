package com.example.projetointerdisciplinar;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
public interface ApiService {
    @GET("api/user/verifyaccount")
    Call<Boolean> getAllUsers(@Query("email") String email, @Query("senha") String senha);
}