package com.example.projetointerdisciplinar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Perfil extends AppCompatActivity {
    private static final String BASE_URL = "https://api-interdisciplinar.onrender.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        ImageButton btHome = findViewById(R.id.btnHome);
        ImageButton btaulas = findViewById(R.id.btnAulas);
        ImageButton btTarefas = findViewById(R.id.btnExercicios);
        ImageButton btRanking = findViewById(R.id.btnRanking);

        btHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Perfil.this, Home.class);
                startActivity(intent);
                finish();
            }
        });
        btaulas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Perfil.this, Aulas.class);
                startActivity(intent);
                finish();
            }
        });
        btTarefas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Perfil.this, Tarefas.class);
                startActivity(intent);
                finish();
            }
        });
        btRanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Perfil.this, Ranking.class);
                startActivity(intent);
                finish();
            }
        });

        TextView trocarImg = findViewById(R.id.trocarFoto);
        trocarImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Perfil.this, EscolherFoto.class);
                Intent inte = getIntent();
                String email = inte.getStringExtra("email");
                intent.putExtra("email", email);
                startActivity(intent);
                finish();
            }
        });


        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        TextView nome = findViewById(R.id.textView8);
        TextView usuario = findViewById(R.id.textView10);
        TextView campoEmail = findViewById(R.id.textView11);
        TextView senha = findViewById(R.id.textView14);
        TextView data_nasc = findViewById(R.id.textView16);
        ImageView foto = findViewById(R.id.imageView5);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        Intent intent = getIntent();
        String email = intent.getStringExtra("email");
        Call<Usuario> call = apiService.getUserById(email);
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (response.isSuccessful()) {
                    Usuario apiResponse = response.body();
                    nome.setText(apiResponse.getNome_real());
                    usuario.setText(apiResponse.getNome_usuario());
                    campoEmail.setText(apiResponse.getEmail());
                    senha.setText(apiResponse.getSenha());
                    data_nasc.setText(formatter.format(apiResponse.getData_nascimento()));
                    //
                    //variavel foto precisa receber a imagem do banco em 64, desencripta-la e carregar para o usuario
                } else {
                    Log.e("NetworkError", "Erro na chamada de rede2: " + response.code());
                }
            }
            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Log.e("NetworkError", "Erro na chamada de rede: " + t.getMessage());
            }
        });
    }

    public void fazerAssinatura(View view) {
        Intent intent = new Intent(this, Assinatura.class);
        startActivity(intent);
        finish();
    }
}