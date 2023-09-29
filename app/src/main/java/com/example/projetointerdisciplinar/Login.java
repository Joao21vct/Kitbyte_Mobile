package com.example.projetointerdisciplinar;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends AppCompatActivity {

    private static final String BASE_URL = "https://api-interdisciplinar.onrender.com";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }

    @SuppressLint("NotConstructor")
    public void Login(View view) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        EditText editEmail = findViewById(R.id.editTextTextEmailAddress3);
        String email = editEmail.getText().toString();
        EditText editPassword = findViewById(R.id.editTextTextPassword3);
        String senha = editPassword.getText().toString();

        Call<Boolean> call = apiService.verifyAccount(email, senha);

        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful()) {
                    boolean apiResponse = response.body();
                    if(apiResponse){
                        Intent intent = new Intent(Login.this, Perfil.class);
                        intent.putExtra("email", email);
                        startActivity(intent);
                    }

                } else {
                    Log.e("NetworkError", "Erro na chamada de rede: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.e("NetworkError", "Erro na chamada de rede: " + t.getMessage());
            }
        });
    }

    public void Register(View view) {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
        finish();
    }
}
