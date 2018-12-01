package com.magallanes.jerichoisaac;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    EditText eName;
    EditText ePass;
    EditText eLName;
    EditText eExam1;
    EditText eAverage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eName= findViewById(R.id.firstName);
        eLName = findViewById(R.id.lastName);
        ePass = findViewById(R.id.password); //Exam2 var
        eExam1= findViewById(R.id.exam1); //Exam1 var
        eAverage = findViewById(R.id.average); //Average var

    }

    public void next(View v) {
        Intent i = new Intent(this,Activity2.class);
        startActivity(i);

    }

    public void saveData(View v){
        String uname = eName.getText().toString();
        String uLname = eLName.getText().toString();
        String pass = ePass.getText().toString();
        String uExam1 = eExam1.getText().toString();
        String uAverage = eAverage.getText().toString();
        SharedPreferences sp = getSharedPreferences("Data1", MODE_PRIVATE);
        SharedPreferences.Editor writer = sp.edit();
        writer.putString("user",uname);
        writer.putString("last",uLname);
        writer.putString("exam1",uExam1);
        writer.putString("exam2",pass);
        writer.putString("average",uAverage);
        writer.commit();
        Toast.makeText(this,"Data Saved...",Toast.LENGTH_LONG).show();


    }

    public void saveInternal(View v) throws IOException {
        String uname = eName.getText().toString();
        String uLname = eLName.getText().toString();
        String pass = ePass.getText().toString();
        String uExam1 = eExam1.getText().toString();
        String uAverage = eAverage.getText().toString();
        int num1 = Integer.parseInt(uExam1);
        int num2 = Integer.parseInt(String.valueOf(pass));
        double calculate = (num1 + num2) /2;
        double result = calculate;
        String uresult = Double.toString(result);
        eAverage.setText(" " + result);
        FileOutputStream fos = null;
        try {
            fos = openFileOutput("Data2.txt",MODE_PRIVATE);
            fos.write(uname.getBytes());
            fos.write(uLname.getBytes());
            fos.write(uExam1.getBytes());
            fos.write(pass.getBytes());
            fos.write(uresult.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Toast.makeText(this, "Saved in "+getFilesDir(),Toast.LENGTH_LONG).show();
    }
}