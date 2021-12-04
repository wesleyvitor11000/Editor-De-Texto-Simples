package com.example.simpletexteditor;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

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


        salvarRascunhoButton.setOnClickListener(view -> FileUtil.salvarRascunho(texto.getText().toString(), MainActivity.this));
        carregarRascunhoButton.setOnClickListener(view -> texto.setText(FileUtil.recuperarRascunho(MainActivity.this)));
        salvarArquivoButton.setOnClickListener(view -> writePermission());

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            fileNameDialog();
        }
    }

    public void writePermission(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return;
            }
        }
        fileNameDialog();
    }

    private void fileNameDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Salvar em Downloads");
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