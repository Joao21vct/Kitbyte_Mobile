package com.example.projetointerdisciplinar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;


public class Home extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

//        View vi = (View) findViewById(R.id.footer).findViewById(R.id.btnRanking);
//
//        vi.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(Home.this, "AULAS interno rank", Toast.LENGTH_SHORT).show();
//            }
//        });
        webView = findViewById(R.id.webHome);
        webView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl("https://kitbyte-frontend.onrender.com/");

        ImageButton btranking = findViewById(R.id.btnRanking);
        ImageButton btaulas = findViewById(R.id.btnAulas);
        ImageButton btexercicios = findViewById(R.id.btnExercicios);
        btranking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(Home.this, "AULAS ok RANK ", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Home.this, Ranking.class);
                startActivity(intent);
                finish();
            }
        });
        btaulas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Aulas.class);
                startActivity(intent);
                finish();
            }
        });
        btexercicios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Tarefas.class);
                startActivity(intent);
                finish();
            }
        });

    }

    public void entrarPerfil(View view) {
        Intent intent = new Intent(this, Perfil.class);
        Intent inte = getIntent();
        String email = inte.getStringExtra("email");
        intent.putExtra("email", email);
        startActivity(intent);
        finish();
    }
}