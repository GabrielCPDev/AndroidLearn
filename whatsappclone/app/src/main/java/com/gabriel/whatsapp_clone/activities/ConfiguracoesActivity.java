package com.gabriel.whatsapp_clone.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.ContextThemeWrapper;
import android.view.View;

import com.gabriel.whatsapp_clone.R;
import com.gabriel.whatsapp_clone.databinding.ActivityConfiguracoesBinding;
import com.gabriel.whatsapp_clone.utils.Permissao;

import java.util.Arrays;
import java.util.List;

public class ConfiguracoesActivity extends AppCompatActivity {

    private static final int SELECAO_CAMERA = 100;
    private static final int SELECAO_GALERIA = 200;
    private ActivityConfiguracoesBinding binding;
    private Toolbar toolbar;
    private List<String> permissoesNecessarias =
            Arrays.asList(
                          Manifest.permission.READ_EXTERNAL_STORAGE,
                          Manifest.permission.CAMERA);

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityConfiguracoesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        configToolbar();
        configClikCameraOuGaleria();
        Permissao.validarPermissoes(this, permissoesNecessarias, 1);

    }

    private void configClikCameraOuGaleria() {
        binding.btnCamera.setOnClickListener(view -> {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (temPackageManager(intent)) startActivityForResult(intent, SELECAO_CAMERA);
        });
        binding.btnGaleria.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_PICK,
                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            if (temPackageManager(intent)) startActivityForResult(intent, SELECAO_GALERIA);
        });
    }
    private boolean temPackageManager(Intent i){
        return i.resolveActivity(getPackageManager()) != null  ? true : false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            Bitmap imagem = null;
            try {
                switch (requestCode){
                    case SELECAO_CAMERA:
                        imagem = (Bitmap) data.getExtras().get("data");
                        if(imagem != null) setImageOnProfile(imagem);
                        break;
                    case SELECAO_GALERIA:
                        Uri localImagemSelecionada = data.getData();
                        imagem = MediaStore.Images.Media.getBitmap(getContentResolver(), localImagemSelecionada);
                        if(imagem != null) setImageOnProfile(imagem);
                        break;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    private void setImageOnProfile(Bitmap imageBitmap) {
        binding.circleImageViewFotoPerfil.setImageBitmap(imageBitmap);
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for (int permissaoResultado : grantResults) {
            if (permissaoResultado == PackageManager.PERMISSION_DENIED){
                alertaValidacaoPermissao();
            }
        }
    }

    private void alertaValidacaoPermissao(){
        AlertDialog.Builder builder = new AlertDialog
                .Builder(new ContextThemeWrapper(this, androidx.appcompat.R.style.Theme_AppCompat_NoActionBar)
        );
        builder.setTitle(R.string.permissaoNegada);
        builder.setMessage(R.string.permissaoNegadaMessage);
        builder.setCancelable(false);

        builder.setPositiveButton(R.string.btnConfirmarPermissao, (dialog, which) -> {
            finish();
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void configToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.configuracoes);
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setDisplayHomeAsUpEnabled(true);
    }
}