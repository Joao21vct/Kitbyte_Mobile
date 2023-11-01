package com.example.projetointerdisciplinar;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.util.Base64;
import android.util.Log; // Importe a classe Log para uso no logcat
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EscolherFoto extends AppCompatActivity {
    ImageView verImagem;
    Button salvarFoto;

    private static final String BASE_URL = "https://api-interdisciplinar.onrender.com/";
    private int rotationAngle = 0;
    private int rotationAngle2 = 0;

    private String imagemBase64 = null; // Variável para armazenar a imagem como string base64

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolher_foto);

        salvarFoto = findViewById(R.id.salvarfoto);

        verImagem = findViewById(R.id.verImg);
        ImageButton camera = findViewById(R.id.imgCamera);
        ImageButton galeria = findViewById(R.id.imgGaleria);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);

        Intent intent = getIntent();
        String email = intent.getStringExtra("email");
        Call<Usuario> callUser = apiService.getUserById(email);
        callUser.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> callUser, Response<Usuario> response) {
                if (response.isSuccessful()) {
                    Usuario apiResponse = response.body();
                    verImagem.setImageBitmap(base64ToBitmap(apiResponse.getFoto_perfil()));

                } else {
                    Log.e("NetworkError", "Erro na chamada de rede2: " + response.code());
                }
            }
            @Override
            public void onFailure(Call<Usuario> callUser, Throwable t) {
                Log.e("NetworkError", "Erro na chamada de rede: " + t.getMessage());
            }
        });

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
                //Diminuindo img
                imagemBitmap = Bitmap.createScaledBitmap(imagemBitmap,300,300,false);
                verImagem.setImageBitmap(imagemBitmap);

                // Converter a imagem em uma string base64 e armazená-la na variável imagemBase64
                imagemBase64 = bitmapToBase64(imagemBitmap);
                System.out.println("teste");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    });
    public void salvarFoto(View view) {
        //Toast.makeText(this, imagemBase64, Toast.LENGTH_SHORT).show();

        if (imagemBase64 != null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            ApiService apiService = retrofit.create(ApiService.class);
            Intent intent1 = getIntent();
            String email = intent1.getStringExtra("email");
            FotoBody body = new FotoBody(email, imagemBase64);
            Call<Object> call = apiService.fidalgo(body);
            call.enqueue(new Callback<Object>() {
                @Override
                public void onResponse(Call<Object> call, Response<Object> response) {
//                    Log.e( "Email:", email);
//                    Log.e( "Arquivo" , imagemBase64);
//                    Log.e( "Code:" , response.body().toObject());
                    if (response.isSuccessful()) {
                        Log.e("Foto", "Foto salva com succeso");
                    } else {
                        Log.e("NetworkError", "Erro na chamada de rede2 foto : " + response.code());
                    }
                }
                @Override
                public void onFailure(Call<Object> call, Throwable t) {
                    Log.e("NetworkError", "Erro na chamada de rede: " + t.getMessage());
                }
            });
        } else {
            System.out.println("Erro");
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

    public Bitmap base64ToBitmap(String base64String) {
        if(base64String == null){
            return null;
        }else{
            byte[] decodedBytes = Base64.decode(base64String, Base64.DEFAULT);
            return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
        }
    }
}
