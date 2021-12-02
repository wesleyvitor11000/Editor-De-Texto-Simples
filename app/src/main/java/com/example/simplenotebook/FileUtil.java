package com.example.simplenotebook;

import android.content.res.AssetManager;
import android.util.Log;

import androidx.annotation.NonNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileUtil {

    private FileUtil(){

    }
    private static String rascunhoNome = "rascunho.txt";
    private static final String TAG = "FileUtil";



    @NonNull
    public static String lerRascunho(@NonNull AssetManager assetManager){

        String rascunho = "";
        BufferedReader leitor;

        try{
            InputStream is = assetManager.open(rascunhoNome);
            InputStreamReader isr = new InputStreamReader(is);
            leitor = new BufferedReader(isr);

            for(String linha = ""; linha != null; linha = leitor.readLine()){
                rascunho += linha + "\n";
            }

            leitor.close();

        } catch (IOException e) {
            Log.e(TAG, "Erro ao ler arquivo.", e);
        }

        return rascunho;
    }

}
