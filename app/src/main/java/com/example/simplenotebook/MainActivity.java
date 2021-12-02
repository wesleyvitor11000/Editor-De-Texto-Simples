package com.example.simplenotebook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /* Toolbar topBar = findViewById(R.id.top_bar);

        Button carregarRascunhoButton = findViewById(R.id.carregar_rascunho);
        EditText texto = findViewById(R.id.text_editor);

        setSupportActionBar(topBar);

        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle("Editor De Textos");
        }



        carregarRascunhoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                texto.setText(FileUtil.lerRascunho(getAssets()));
            }
        });*/
    }
}