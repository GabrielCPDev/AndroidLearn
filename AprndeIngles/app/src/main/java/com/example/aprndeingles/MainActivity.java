package com.example.aprndeingles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.aprndeingles.fragments.BichosFragment;
import com.example.aprndeingles.fragments.NumerosFragment;
import com.example.aprndeingles.fragments.VogaisFragment;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private SmartTabLayout smartTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializaCampos();

        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(),
                FragmentPagerItems.with(this)
                        .add("Bichos", BichosFragment.class)
                        .add("Numeros", NumerosFragment.class)
                        .add("Vogais", VogaisFragment.class).create()
        );

        viewPager.setAdapter(adapter);
        smartTabLayout.setViewPager(viewPager);

    }

    public void inicializaCampos(){
        viewPager = findViewById(R.id.viewPager);
        smartTabLayout = findViewById(R.id.smartTabLayout);
    }
}