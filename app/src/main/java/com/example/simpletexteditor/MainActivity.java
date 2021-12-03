package com.example.simpletexteditor;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    EditText texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar topBar = findViewById(R.id.top_bar);

        Button carregarRascunhoButton = findViewById(R.id.carregar_rascunho);
        Button salvarRascunhoButton = findViewById(R.id.salvar_rascunho);
        Button salvarArquivoButton = findViewById(R.id.salvar_arquivo);
        texto = findViewById(R.id.text_editor);

        setSupportActionBar(topBar);
        if(getSupportActionBar() != null){
             getSupportActionBar().setTitle("");
        }


        salvarRascunhoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FileUtil.salvarRascunho(texto.getText().toString());
            }
        });

        carregarRascunhoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                texto.setText(FileUtil.recuperarRascunho(getAssets()));
            }
        });

        salvarArquivoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fileNameDialog();
            }
        });
    }

    private void fileNameDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Salvar");
        builder.setMessage("Insira um nome para o arquivo:");

        final EditText fileName = new EditText(this);
        builder.setView(fileName);

        builder.setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                FileUtil.salvarArquivo(fileName.getText().toString(), texto.getText().toString(), MainActivity.this);
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        builder.show();
    }
}