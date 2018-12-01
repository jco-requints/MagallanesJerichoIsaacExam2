package com.magallanes.jerichoisaac;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import java.io.FileInputStream;

public class Activity2 extends AppCompatActivity {
    EditText tname, tpass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);
        tname= findViewById(R.id.toName);
        tpass = findViewById(R.id.topassword);
    }

    public void previous(View v){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void loadData(View v){
        SharedPreferences sp = getSharedPreferences("Data1",MODE_PRIVATE);
        String user = sp.getString("user",null);
        String password = sp.getString("password",null);
        tname.setText(user);
        tpass.setText(password);
    }

    public void loadInternal(View v){
        try {
            FileInputStream fis = openFileInput("Data2.txt");
            StringBuffer buffer = new StringBuffer();
            int read=0;
            while ((read = fis.read())!=-1){
                buffer.append((char)read);

            }
            tname.setText(buffer.toString());
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }


    }


}
