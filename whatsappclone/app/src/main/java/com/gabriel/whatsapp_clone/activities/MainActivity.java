package com.gabriel.whatsapp_clone.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.gabriel.whatsapp_clone.R;
import com.gabriel.whatsapp_clone.configuration.FirebaseConfig;
import com.gabriel.whatsapp_clone.databinding.ActivityMainBinding;
import com.gabriel.whatsapp_clone.fragments.ContatosFragment;
import com.gabriel.whatsapp_clone.fragments.ConversaFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private Toolbar toolbar;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth = FirebaseConfig.getFirebaseAuth();
        configToolbar();
        configAbas();
    }

    private void configAbas() {
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(),
                FragmentPagerItems.with(getApplicationContext())
                        .add(R.string.conversas, ConversaFragment.class)
                        .add(R.string.contatos, ContatosFragment.class)
                        .create()
        );
        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        SmartTabLayout viewPagerTab = findViewById(R.id.viewPagerTab);
        viewPagerTab.setViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuSair:
                signoutUser();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
                break;
            case R.id.menuConfiguracoes:
                openConfigurations();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void openConfigurations() {
        Intent intent = new Intent(getApplicationContext(), ConfiguracoesActivity.class);
        startActivity(intent);
    }

    private void signoutUser() {
        try {
            auth.signOut();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void configToolbar() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);
    }
}