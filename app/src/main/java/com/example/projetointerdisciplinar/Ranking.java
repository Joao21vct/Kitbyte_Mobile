package com.example.projetointerdisciplinar;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.Toast;

public class Ranking extends AppCompatActivity {

    private WebView webView;
    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        webView = findViewById(R.id.webRanking);
        webView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl("https://inter-7550.onrender.com/ranking");
        webView.addJavascriptInterface(this, "Android");

        ImageButton btHome = findViewById(R.id.btnHome);
        ImageButton btaulas = findViewById(R.id.btnAulas);
        ImageButton btTarefas = findViewById(R.id.btnExercicios);

        btHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Ranking.this, Home.class);
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
                Intent intent = new Intent(Ranking.this, Aulas.class);
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
                Intent intent = new Intent(Ranking.this, Tarefas.class);
                Intent inte = getIntent();
                String email = inte.getStringExtra("email");
                intent.putExtra("email", email);
                startActivity(intent);
                finish();
            }
        });
    }
    @JavascriptInterface
    public String parametrosFront(){
        Intent inte = getIntent();
        String email = inte.getStringExtra("email");
        return email;
    }
}