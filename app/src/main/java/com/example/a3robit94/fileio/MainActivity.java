package com.example.a3robit94.fileio;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_save, menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == R.id.saveFile){
            try{
                EditText textmsg = (EditText)findViewById(R.id.editText);
                String outputText = textmsg.getText().toString();
                PrintWriter pw = new PrintWriter(new FileWriter(Environment.getExternalStorageDirectory().getAbsolutePath()+"/textedit.txt"));
                pw.println(outputText);
                pw.close();
            }
            catch(IOException e){
                new AlertDialog.Builder(this).setMessage("ERROR: " + e).setPositiveButton("OK", null).show();
            }
        }
        if(item.getItemId() == R.id.loadFile){
            try{
                EditText textmsg = (EditText)findViewById(R.id.editText);
                FileReader fr = new FileReader(Environment.getExternalStorageDirectory().getAbsolutePath() + "/textedit.txt");
                BufferedReader reader = new BufferedReader(fr);
                String line = null;
                String full = "";
                while((line = reader.readLine()) != null){
                    full += line + "\n";
                }
                if(full != ""){
                    textmsg.setText(full);
                }
                reader.close();
            }
            catch(IOException e){
                new AlertDialog.Builder(this).setMessage("ERROR: " + e).setPositiveButton("OK", null).show();
            }
        }
        return false;
    }


}
