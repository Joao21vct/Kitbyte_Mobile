package com.example.projetointerdisciplinar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class Aulas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aulas);

        ImageButton btHome = findViewById(R.id.btnHome);
        ImageButton btTarefas = findViewById(R.id.btnExercicios);
        ImageButton btRanking = findViewById(R.id.btnRanking);

        btHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  Intent intent = new Intent(Aulas.this, Home.class);
                startActivity(intent);
                finish();
            }
        });
        btTarefas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Aulas.this, Tarefas.class);
                startActivity(intent);
                finish();
            }
        });
        btRanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Aulas.this, Ranking.class);
                startActivity(intent);
                finish();
            }
        });
    }
}