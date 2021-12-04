package com.example.simpletexteditor;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import androidx.annotation.NonNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

public class FileUtil {

    private FileUtil(){

    }
    private static String rascunhoNome = "rascunho.ste";
    private static final String TAG = "FileUtil";


    public static void salvarRascunho(String rascunhoContent, @NonNull Context c){
        File dir = c.getFilesDir();
        File file = new File(dir, rascunhoNome);

        salvarNaMemoria(file, rascunhoContent);

    }

    @NonNull
    public static String recuperarRascunho(@NonNull AssetManager assetManager, @NonNull Context c){

        String rascunho = "";
        File dir = c.getFilesDir();
        File file = new File(dir, rascunhoNome);

        try{
            BufferedReader leitor = new BufferedReader(new FileReader(file));

            for(String linha = ""; linha != null; linha = leitor.readLine()){
                rascunho += linha + "\n";
            }

            leitor.close();

        } catch (IOException e) {
            Log.e(TAG, "erro ao abrir o rascunho", e);
        }

        if (rascunho.length() > 0) {
            return rascunho.substring(1, rascunho.length() - 1);
        }

        return rascunho;
    }

    public static void salvarArquivo(String fileName, String content, Context c) {

        String raiz = Environment.getExternalStorageDirectory() + File.separator;
        String diretorio = Environment.DIRECTORY_DOWNLOADS + File.separator;
        File file = new File(raiz + diretorio + fileName);


        //try{
        if(file.exists()){

            overwriteDialog(c, new FileCallback() {
                @Override
                public void onClick(boolean choice) {
                    if(choice){
                        System.out.println("TRUE");
                        file.delete();

                        salvarNaMemoria(file, content);

                    }else{
                        System.out.println("FALSE");
                        return;
                    }
                }
            });

        }else{
            salvarNaMemoria(file, content);
        }
    }

    private static void salvarNaMemoria(File file, String content){

        FileOutputStream fluxoDeSaida = null;

        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try{
            fluxoDeSaida = new FileOutputStream(file);

        } catch (FileNotFoundException e) {
            Log.e(TAG, "Não foi possível acessar o caminho do arquivo.", e);
        }

        if(fluxoDeSaida!=null){
            try{
                fluxoDeSaida.write(content.getBytes());
                fluxoDeSaida.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void overwriteDialog(Context c, FileCallback callback){

        final boolean[] overwrite = {false};


        DialogInterface.OnClickListener dialogOnClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i){
                    case DialogInterface.BUTTON_POSITIVE:
                        callback.onClick(true);
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        callback.onClick(false);
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("");
        builder.setMessage("O arquivo já existe, deseja sobreescrevê-lo?");

        builder.setPositiveButton("Sim", dialogOnClickListener);
        builder.setNegativeButton("Não", dialogOnClickListener);

        builder.show();

    }

}
