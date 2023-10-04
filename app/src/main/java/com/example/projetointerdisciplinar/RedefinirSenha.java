package com.example.projetointerdisciplinar;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RedefinirSenha extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redefinir_senha);
    }
    private static final String BASE_URL = "https://api-interdisciplinar.onrender.com";
    @SuppressLint("NotConstructor")
    public void resetPassword(View view) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        EditText editEmail = findViewById(R.id.editTextTextEmailAddress7);
        String email = editEmail.getText().toString();
        EditText editPassword = findViewById(R.id.editTextTextPassword7);
        EditText editConfirmPassword = findViewById(R.id.editTextTextPassword11);
        String senha = editPassword.getText().toString();
        String editSenha = editConfirmPassword.getText().toString();
        // implementar verificação se o email existe

        if (senha.equals(editSenha)){
            Call<Usuario> call = apiService.resetPassword(email, senha);
            call.enqueue(new Callback<Usuario>() {
                @Override
                public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                    if (response.isSuccessful()) {
                        Intent intent = new Intent(RedefinirSenha.this, Login.class);
                        startActivity(intent);
                    } else {
                        Log.e("NetworkError", "Erro na chamada de rede: " + response.code());
                    }
                }
                @Override
                public void onFailure(Call<Usuario> call, Throwable t) {
                    Log.e("NetworkError", "Erro na chamada de rede: " + t.getMessage());
                }
            });
        } else{
            Toast.makeText(this, "Campos de senha e confirmação de senha estão diferentes", Toast.LENGTH_LONG).show();
        }


    }
}