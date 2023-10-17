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

public class Tarefas extends AppCompatActivity {
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarefas);

        webView = findViewById(R.id.webTarefa);
        webView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl("https://kitbyte-frontend.onrender.com/exercises");

        ImageButton btHome = findViewById(R.id.btnHome);
        ImageButton btaulas = findViewById(R.id.btnAulas);
        ImageButton btRanking = findViewById(R.id.btnRanking);

        btHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Tarefas.this, Home.class);
                startActivity(intent);
                finish();
            }
        });
        btaulas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Tarefas.this, Aulas.class);
                startActivity(intent);
                finish();
            }
        });
        btRanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Tarefas.this, Ranking.class);
                startActivity(intent);
                finish();
            }
        });

    }
}