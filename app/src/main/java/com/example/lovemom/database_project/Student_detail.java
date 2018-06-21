package com.example.lovemom.database_project;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Student_detail extends AppCompatActivity {

    EditText roll;
    Button data;
    String Roll;
    Result_project result_project;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);
        roll = findViewById(R.id.roll);
        data = findViewById(R.id.data);
        getdata1();
    }

    public void getdata1() {

        data.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String Roll = roll.getText().toString();
                if (!Roll.isEmpty()) {
                    Cursor cursor = result_project.getdata(Roll);
                    if (cursor.getCount() == 0) {
                        showmessage("Error", "No data found");
                        return;
                    }
                    StringBuffer stringBuffer = new StringBuffer();
                    while (cursor.moveToNext()) {
                        stringBuffer.append("Roll      \t:" + cursor.getString(0) + "\n");
                        stringBuffer.append("Name   \t:" + cursor.getString(1) + "\n");
                        stringBuffer.append("Father   \t:" + cursor.getString(2) + "\n");
                        stringBuffer.append("Mother \t:" + cursor.getString(3) + "\n");
                        stringBuffer.append("Age      \t:" + cursor.getString(4) + "\n");
                        stringBuffer.append("Contact\t:" + cursor.getString(5) + "\n\n");
                    }
                    showmessage("Data", stringBuffer.toString());

                } else {

                    Toast.makeText(Student_detail.this, "Please enter Roll number ", Toast.LENGTH_SHORT).show();

                }
            }

        });
    }

    public void showmessage(String title, String message) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle(title);
        alertDialog.setCancelable(true);
        alertDialog.setMessage(message);
        alertDialog.show();


    }
}
