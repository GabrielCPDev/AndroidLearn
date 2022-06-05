package com.gabriel.organizze.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;

import com.gabriel.organizze.databinding.ActivityReceitasBinding;
import com.gabriel.organizze.helper.DateUtil;
import com.google.android.gms.common.util.DataUtils;

public class ReceitasActivity extends AppCompatActivity {

    private ActivityReceitasBinding bindig;

    @Override
    @RequiresApi(api = Build.VERSION_CODES.O)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindig = ActivityReceitasBinding.inflate(getLayoutInflater());
        setContentView(bindig.getRoot());
        setDataAtual();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setDataAtual() {
        bindig.editData.setText(DateUtil.dataAtual());
    }
}