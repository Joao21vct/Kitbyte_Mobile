package com.example.projetointerdisciplinar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.Toast;

public class Aulas extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aulas);

        webView = findViewById(R.id.webAula);
        webView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl("https://inter-7550.onrender.com/courses");

        ImageButton btHome = findViewById(R.id.btnHome);
        ImageButton btTarefas = findViewById(R.id.btnExercicios);
        ImageButton btRanking = findViewById(R.id.btnRanking);

        btHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Aulas.this, Home.class);
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
                Intent intent = new Intent(Aulas.this, Tarefas.class);
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
                Intent intent = new Intent(Aulas.this, Ranking.class);
                Intent inte = getIntent();
                String email = inte.getStringExtra("email");
                intent.putExtra("email", email);
                startActivity(intent);
                finish();
            }
        });
    }
}