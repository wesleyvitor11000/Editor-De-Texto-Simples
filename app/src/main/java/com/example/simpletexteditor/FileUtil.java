package com.example.simpletexteditor;

import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import androidx.annotation.NonNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class FileUtil {

    private FileUtil(){

    }
    private static String rascunhoNome = "rascunho.txt";
    private static final String TAG = "FileUtil";

    public static Uri salvarRascunho(String rascunhoContent){

        String raiz = Environment.getExternalStorageDirectory() + File.separator;
        String diretorio = Environment.DIRECTORY_DOWNLOADS + File.separator;
        File file = new File(raiz + diretorio + rascunhoNome);

        FileOutputStream fluxoDeSaida = null;

        try{
            if(file.exists()){
                file.delete();
            }

            file.createNewFile();



        } catch (IOException e) {
            Log.e(TAG, "Arquivo inacessível", e);
        }

        try{
            fluxoDeSaida = new FileOutputStream(file);

        } catch (FileNotFoundException e) {
            Log.e(TAG, "Não foi possível acessar o caminho do arquivo.", e);
        }

        if(fluxoDeSaida!=null){
            try{
                fluxoDeSaida.write(rascunhoContent.getBytes());
                fluxoDeSaida.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return Uri.fromFile(file);
    }

    @NonNull
    public static String recuperarRascunho(@NonNull AssetManager assetManager){

        String rascunho = "";

        String raiz = Environment.getExternalStorageDirectory() + File.separator;
        String diretorio = Environment.DIRECTORY_DOWNLOADS + File.separator;
        File file = new File(raiz + diretorio + rascunhoNome);

        try{
            BufferedReader  leitor = new BufferedReader(new FileReader(file));

            for(String linha = ""; linha != null; linha = leitor.readLine()){
                rascunho += linha + "\n";
            }

            leitor.close();
        }catch (IOException e){
            Log.e(TAG, "erro ao abrir o rascunho", e);
        }

        if (rascunho.length() > 0) {
            return rascunho.substring(1, rascunho.length() - 1);
        }

        return rascunho;
    }

}
