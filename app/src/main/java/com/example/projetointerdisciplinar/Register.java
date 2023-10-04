package com.example.projetointerdisciplinar;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Register extends AppCompatActivity {
    private static final String BASE_URL = "https://api-interdisciplinar.onrender.com";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
    }
    @SuppressLint("NotConstructor")
    public void Cadastrar(View view) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        EditText editNome = findViewById(R.id.editTextTextEmailAddress3);
        String nome = editNome.getText().toString();
        EditText editUsuario = findViewById(R.id.editTextTextPassword3);
        String usuario = editUsuario.getText().toString();
        EditText editEmail = findViewById(R.id.editTextTextPassword311);
        String email = editEmail.getText().toString();
        EditText editNascimento = findViewById(R.id.editTextTextPassword333);
        String nascimento = editNascimento.getText().toString();
        EditText editSenha = findViewById(R.id.editTextTextPassword366);
        String senha = editSenha.getText().toString();
        EditText editConfirmSenha = findViewById(R.id.editTextTextPassword323);
        String confirmSenha = editConfirmSenha.getText().toString();
        // implementar verificação se o email existe

        if (senha.equals(confirmSenha)){
            Map<String, String> hashMap = new HashMap<>();
            hashMap.put("nome_real", nome);
            hashMap.put("nome_usuario", usuario);
            hashMap.put("email", email);
            hashMap.put("nascimento", nascimento);
            hashMap.put("senha", senha);
            Call<Usuario> call = apiService.registerUser((HashMap) hashMap);
            call.enqueue(new Callback<Usuario>() {
                @Override
                public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                    if (response.isSuccessful()) {
                        Intent intent = new Intent(Register.this, Login.class);
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