package com.gabriel.organizze.activities;

import android.content.Intent;
import android.os.Bundle;

import com.gabriel.organizze.configs.FirebaseConfig;
import com.gabriel.organizze.databinding.ActivityPrincipalBinding;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import com.gabriel.organizze.R;
import com.google.firebase.auth.FirebaseAuth;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

public class PrincipalActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private MaterialCalendarView calendarView;
    private TextView textSaudacao;
    private TextView textSaldo;
    private RecyclerView recyclerView;
    private ActivityPrincipalBinding binding;

    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPrincipalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        setBindings();
        setAppbarConfig();

        setSupportActionBar(binding.toolbar);
        setFabAdicionaDespesa();
        setFabAdicionaReceita();
        setCalendarConfig();
    }

    private void setAppbarConfig() {
        binding.toolbar.setTitle("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuSair: {
                autenticacao = FirebaseConfig.getFirebaseAutenticacao();
                autenticacao.signOut();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void setBindings() {
        calendarView = findViewById(R.id.calendarView);
        recyclerView = findViewById(R.id.recyclerMovimentacoes);
        textSaudacao = findViewById(R.id.textSaudacao);
        textSaldo = findViewById(R.id.textSaldo);
    }

    private void setCalendarConfig() {
        calendarView.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
                Log.i("TAG", "onMonthChanged: " + date.getMonth());
            }
        });
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
}