package com.example.umniyvrach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainMeny_admin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_meny_admin);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent= new Intent(MainMeny_admin
                .this,MainActivity.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }
    public void listspec(View view) {
        Intent intent= new Intent(MainMeny_admin.this,List_of_spec.class);
        startActivity(intent);
    }

    public void Reques(View view) {
        Intent intent= new Intent(MainMeny_admin.this,Requests.class);
        startActivity(intent);
    }
}
