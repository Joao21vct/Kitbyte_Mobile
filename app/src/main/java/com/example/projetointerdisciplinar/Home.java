package com.example.projetointerdisciplinar;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;


public class Home extends AppCompatActivity {
    private WebView webView;

    @SuppressLint("JavascriptInterface")
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
//        webView.loadUrl("https://inter-7550.onrender.com/");
        webView.loadUrl("https://kitbytefinal.onrender.com");
        webView.addJavascriptInterface(this, "Android");

//        ImageButton btranking = findViewById(R.id.btnRanking);
//        ImageButton btaulas = findViewById(R.id.btnAulas);
//        ImageButton btexercicios = findViewById(R.id.btnExercicios);
//        btranking.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Home.this, Ranking.class);
//                Intent inte = getIntent();
//                String email = inte.getStringExtra("email");
//                intent.putExtra("email", email);
//                startActivity(intent);
//                finish();
//            }
//        });
//        btaulas.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Home.this, Aulas.class);
//                Intent inte = getIntent();
//                String email = inte.getStringExtra("email");
//                intent.putExtra("email", email);
//                startActivity(intent);
//                finish();
//            }
//        });
//        btexercicios.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Home.this, Tarefas.class);
//                Intent inte = getIntent();
//                String email = inte.getStringExtra("email");
//                intent.putExtra("email", email);
//                startActivity(intent);
//                finish();
//            }
//        });

    }

    public void entrarPerfil(View view) {
        Intent intent = new Intent(this, Perfil.class);
        Intent inte = getIntent();
        String email = inte.getStringExtra("email");
        intent.putExtra("email", email);
        startActivity(intent);
        finish();
    }

    @JavascriptInterface
    public String parametrosFront(){
        Intent inte = getIntent();
        String email = inte.getStringExtra("email");
        return email;
    }
}
