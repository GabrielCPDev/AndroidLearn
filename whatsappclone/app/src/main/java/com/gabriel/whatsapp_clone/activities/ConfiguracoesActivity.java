package com.gabriel.whatsapp_clone.activities;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextThemeWrapper;

import com.gabriel.whatsapp_clone.R;
import com.gabriel.whatsapp_clone.databinding.ActivityConfiguracoesBinding;
import com.gabriel.whatsapp_clone.utils.Permissao;

import java.util.Arrays;
import java.util.List;

public class ConfiguracoesActivity extends AppCompatActivity {

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
        boolean temPermissoes = Permissao.validarPermissoes(this, permissoesNecessarias, 1);
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