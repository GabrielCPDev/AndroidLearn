package com.gabriel.organizze.activities;

import android.content.Intent;
import android.os.Bundle;

import com.gabriel.organizze.databinding.ActivityPrincipalBinding;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.gabriel.organizze.R;

public class PrincipalActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityPrincipalBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPrincipalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        setFabAdicionaDespesa();
        setFabAdicionaReceita();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_principal);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
    }

    private void setFabAdicionaDespesa() {
        binding.menuDespesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), DespesasActivity.class));
            }
        });
    }

    private void setFabAdicionaReceita() {
        binding.menuReceita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ReceitasActivity.class));
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_principal);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}