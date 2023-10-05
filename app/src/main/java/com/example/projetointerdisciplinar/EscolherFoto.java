package com.example.projetointerdisciplinar;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.IOException;

public class EscolherFoto extends AppCompatActivity {
    ImageView verImagem;
    private int rotationAngle = 90;
    private int rotationAngle2 = 270;

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
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    });

    public void voltar(View view) {
        Intent intent = new Intent(this, Perfil.class);
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

}
