package com.gabriel.organizze.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.gabriel.organizze.components.AdapterMovimentacao;
import com.gabriel.organizze.configs.FirebaseConfig;
import com.gabriel.organizze.databinding.ActivityPrincipalBinding;
import com.gabriel.organizze.helper.Base64Custom;
import com.gabriel.organizze.models.Movimentacao;
import com.gabriel.organizze.models.Usuario;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gabriel.organizze.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class PrincipalActivity extends AppCompatActivity {

    private MaterialCalendarView calendarView;
    private TextView textSaudacao;
    private TextView textSaldo;
    private RecyclerView recyclerView;
    private AdapterMovimentacao adapterMovimentacao;
    private ActivityPrincipalBinding binding;
    private Usuario usuario;
    private String mesAnoSelecionado;
    private List<Movimentacao> movimentacoes = new ArrayList<>();
    private Double saldoTotal = 0.0;

    private FirebaseAuth autenticacao = FirebaseConfig.getFirebaseAutenticacao();
    private DatabaseReference database = FirebaseConfig.getFirebaseDatabase();
    private DatabaseReference usuariosRef = database.child("usuarios").child(Base64Custom.codificarBase64(autenticacao.getCurrentUser().getEmail()));
    private DatabaseReference movimentacoesRef;
    private ValueEventListener valueEventListenerUsuario;
    private ValueEventListener valueEventListenerMovimentacoes;

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
        setRecyclerViewConfig();
        swipe();
    }
    @Override
    protected void onResume() {
        super.onResume();
        recuperaResumo();
        recuperaMovimentacoes();
    }
    @Override
    protected void onStop() {
        super.onStop();
        usuariosRef.removeEventListener(valueEventListenerUsuario);
        movimentacoesRef.removeEventListener(valueEventListenerMovimentacoes);
    }

    private void recuperaMovimentacoes() {
        movimentacoesRef = database.child("movimentacao")
                                   .child(Base64Custom.codificarBase64(autenticacao.getCurrentUser().getEmail()))
                                    .child(mesAnoSelecionado);
        valueEventListenerMovimentacoes = movimentacoesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                movimentacoes.clear();
                for(DataSnapshot dados : snapshot.getChildren()){
                    Movimentacao movimentacao = dados.getValue(Movimentacao.class);
                    movimentacao.setId(dados.getKey());
                    movimentacoes.add(movimentacao);
                }
                adapterMovimentacao.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }
    private void setAppbarConfig() {
        binding.toolbar.setTitle("");
    }

    private void recuperaResumo() {
        valueEventListenerUsuario = usuariosRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                usuario = snapshot.getValue(Usuario.class);
                textSaudacao.setText("Olá, " + usuario.getNome());
                saldoTotal = usuario.getReceitaTotal() - usuario.getDespesaTotal();
                DecimalFormat decimalFormat = new DecimalFormat("0.##");
                textSaldo.setText("R$ " + decimalFormat.format(saldoTotal));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuSair: {
                autenticacao.signOut();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void swipe(){
        ItemTouchHelper.Callback itemTouch = new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                int dragFlags = ItemTouchHelper.ACTION_STATE_IDLE;
                int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
                return makeMovementFlags(dragFlags, swipeFlags);
            }

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                excluirMovimentacao(viewHolder);
            }
        };
        new ItemTouchHelper(itemTouch).attachToRecyclerView(recyclerView);
    }

    private void excluirMovimentacao(RecyclerView.ViewHolder viewHolder){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog alert;

        builder.setTitle(R.string.title_excluir_movimentacao_dialog);
        builder.setMessage(R.string.message_excluir_movimentacao_dialog);
        builder.setCancelable(false);

        setEventToClickOnDialog(builder, viewHolder);
        alert = builder.create();
        alert.show();
    }

    private void setEventToClickOnDialog(AlertDialog.Builder alertDialog, RecyclerView.ViewHolder viewHolder) {
        alertDialog.setPositiveButton(R.string.btn_positive_excluir_movimentacao_dialog, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int position = viewHolder.getAdapterPosition();
                Movimentacao movimentacaoSelecionada = movimentacoes.get(position);
                excluirMovimentacaoDoBanco(movimentacaoSelecionada);
                adapterMovimentacao.notifyItemRemoved(position);
                Toast.makeText(PrincipalActivity.this, "Item excluído", Toast.LENGTH_SHORT).show();

            }
        });
        alertDialog.setNegativeButton(R.string.btn_negative_excluir_movimentacao_dialog, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(PrincipalActivity.this, "Cancelado", Toast.LENGTH_SHORT).show();
                adapterMovimentacao.notifyDataSetChanged();
            }
        });
    }

    private void excluirMovimentacaoDoBanco(Movimentacao movimentacaoSelecionada) {
        movimentacoesRef.child(movimentacaoSelecionada.getId()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(PrincipalActivity.this,
                            "Item excluído",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(
                            PrincipalActivity.this,
                            "Item excluído",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setBindings() {
        calendarView = findViewById(R.id.calendarView);
        recyclerView = findViewById(R.id.recyclerMovimentacoes);
        textSaudacao = findViewById(R.id.textSaudacao);
        textSaldo = findViewById(R.id.textSaldo);
    }

    private void setCalendarConfig() {
        CalendarDay dataAtual = calendarView.getCurrentDate();
        mesAnoSelecionado = getMesFormatado(dataAtual.getMonth()) + dataAtual.getYear();
        calendarView.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
                mesAnoSelecionado = getMesFormatado(date.getMonth()) + date.getYear();
                movimentacoesRef.removeEventListener(valueEventListenerMovimentacoes);
                recuperaMovimentacoes();
            }
        });
    }

    private String getMesFormatado(int month) {
        return month < 10 ? "0" + String.valueOf(month + 1) : String.valueOf(month + 1);
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

    private void setRecyclerViewConfig() {
        adapterMovimentacao = new AdapterMovimentacao(movimentacoes, getApplicationContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapterMovimentacao);
    }
}