package com.example.lovemom.database_project;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class Data2 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Button register, button,ROLL;
    EditText name, father, mother, age, contact,roll;
    ProgressBar progressBar;


    Result_project result_project;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        progressBar=findViewById(R.id.progress);
        roll=findViewById(R.id.roll);
        button = findViewById(R.id.button);
        name = findViewById(R.id.name);
        father = findViewById(R.id.father);
        mother = findViewById(R.id.mother);
        age = findViewById(R.id.age);
        contact = findViewById(R.id.contact);
        register = findViewById(R.id.register);
        progressBar.setVisibility(View.INVISIBLE);
        fetch();
        add();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        result_project = new Result_project(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.student_detail) {
            Intent intent = new Intent(Data2.this, Student_detail.class);
            startActivity(intent);
        } else if (id == R.id.Result) {

            Toast.makeText(this, "Result detail", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.manage) {


            Toast.makeText(this, "Manage detail", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_share) {


        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void add() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setVisibility(View.VISIBLE);

                        boolean result = result_project.insert(name.getText().toString(), father.getText().toString(), mother.getText().toString()
                                , age.getText().toString(), contact.getText().toString());

                        if (result == true) {
                            final Thread thread=new Thread(new Runnable() {
                                @Override
                                public void run() {

                                }
                            });

                            Toast.makeText(Data2.this, "Data succesfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Data2.this, "Data error", Toast.LENGTH_SHORT).show();
                        }


                    }
                }, 2000);

            }
        });
    }

    public void fetch() {
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Roll=roll.getText().toString();
                if(!Roll.isEmpty()){
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

                }
                else {

                    Toast.makeText(Data2.this, "Please enter Roll number ", Toast.LENGTH_SHORT).show();

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
