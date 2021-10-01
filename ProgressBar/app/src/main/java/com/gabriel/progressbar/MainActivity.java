package com.gabriel.progressbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBarHorizontal;
    private ProgressBar progressBarVertical;
    private int progresso = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBarHorizontal = findViewById(R.id.progress_horizontal);
        progressBarVertical = findViewById(R.id.progress_circular);
    }

    public void carregarProgressBar(View view){

        // progress bar horizontal
        this.progresso = this.progresso += 1;
        progressBarHorizontal.setProgress(this.progresso);

        //progress bar circular
        progressBarVertical.setVisibility(View.VISIBLE);
        if(this.progresso == 10){
            progressBarVertical.setVisibility(View.GONE);
        }


    }
}