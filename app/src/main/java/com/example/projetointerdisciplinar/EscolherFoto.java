package com.example.projetointerdisciplinar;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.util.Base64;
import android.util.Log; // Importe a classe Log para uso no logcat

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EscolherFoto extends AppCompatActivity {
    ImageView verImagem;
    private static final String BASE_URL = "https://api-interdisciplinar.onrender.com";
    private int rotationAngle = 90;
    private int rotationAngle2 = 270;

    private String imagemBase64 = null; // Variável para armazenar a imagem como string base64

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolher_foto);

        verImagem = findViewById(R.id.verImg);
        ImageButton camera = findViewById(R.id.imgCamera);
        ImageButton galeria = findViewById(R.id.imgGaleria);

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                resultadoIntent.launch(intent);
            }
        });

        galeria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                resultadoIntentGallery.launch(intent);
            }
        });
    }

    ActivityResultLauncher<Intent> resultadoIntent = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == RESULT_OK) {
            Bundle extras = result.getData().getExtras();
            Bitmap imagemBitmap = (Bitmap) extras.get("data");

            imagemBitmap = rotateBitmap(imagemBitmap, rotationAngle);

            verImagem.setImageBitmap(imagemBitmap);

            // Converter a imagem em uma string base64 e armazená-la na variável imagemBase64
            imagemBase64 = bitmapToBase64(imagemBitmap);
        }
    });

    ActivityResultLauncher<Intent> resultadoIntentGallery = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == RESULT_OK) {
            Bundle extras = result.getData().getExtras();
            Uri localImgSelecionada = result.getData().getData();
            try {
                Bitmap imagemBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), localImgSelecionada);

                imagemBitmap = rotateBitmap2(imagemBitmap, rotationAngle2);

                verImagem.setImageBitmap(imagemBitmap);

                // Converter a imagem em uma string base64 e armazená-la na variável imagemBase64
                imagemBase64 = bitmapToBase64(imagemBitmap);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    });

    public void salvarFoto(View view) {
        if (imagemBase64 != null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            ApiService apiService = retrofit.create(ApiService.class);
            Intent intent = getIntent();
            String email = intent.getStringExtra("email");
            Call<String> call = apiService.fidalgo(email, imagemBase64);
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.isSuccessful()) {
                        Log.e("Foto", "Foto salva com succeso");
                    } else {
                        Log.e("NetworkError", "Erro na chamada de rede2 foto : " + response.code());
                    }
                }
                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Log.e("NetworkError", "Erro na chamada de rede: " + t.getMessage());
                }
            });
        } else {
            // Trate o caso em que nenhuma imagem foi selecionada
        }

        // Retorne para a tela de perfil ou faça outras ações necessárias
        Intent intent = new Intent(this, Perfil.class);
        Intent inte = getIntent();
        String email = inte.getStringExtra("email");
        intent.putExtra("email", email);
        startActivity(intent);
        finish();
    }

    private Bitmap rotateBitmap(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
    }
    private Bitmap rotateBitmap2(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
    }

    public String bitmapToBase64(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
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
