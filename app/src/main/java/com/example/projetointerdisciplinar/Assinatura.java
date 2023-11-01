package com.example.projetointerdisciplinar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Assinatura extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assinatura);

        ImageButton btHome = findViewById(R.id.btnHome);
        ImageButton btaulas = findViewById(R.id.btnAulas);
        ImageButton btTarefas = findViewById(R.id.btnExercicios);
        ImageButton btRanking = findViewById(R.id.btnRanking);

        btHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Assinatura.this, Home.class);
                Intent inte = getIntent();
                String email = inte.getStringExtra("email");
                intent.putExtra("email", email);
                startActivity(intent);
                finish();
            }
        });
        btaulas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Assinatura.this, Aulas.class);
                Intent inte = getIntent();
                String email = inte.getStringExtra("email");
                intent.putExtra("email", email);
                startActivity(intent);
                finish();
            }
        });
        btTarefas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Assinatura.this, Tarefas.class);
                Intent inte = getIntent();
                String email = inte.getStringExtra("email");
                intent.putExtra("email", email);
                startActivity(intent);
                finish();
            }
        });
        btRanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Assinatura.this, Ranking.class);
                Intent inte = getIntent();
                String email = inte.getStringExtra("email");
                intent.putExtra("email", email);
                startActivity(intent);
                finish();
            }
        });
    }

    public void voltar(View view) {
        Intent intent = new Intent(this, Perfil.class);
        Intent inte = getIntent();
        String email = inte.getStringExtra("email");
        intent.putExtra("email", email);
        startActivity(intent);
        finish();
    }
}