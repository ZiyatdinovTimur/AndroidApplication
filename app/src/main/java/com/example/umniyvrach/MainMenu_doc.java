package com.example.umniyvrach;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainMenu_doc extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu_doc);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent= new Intent(MainMenu_doc
                .this,MainActivity.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }
    public void Profile(View view) {
        Intent intent= new Intent(MainMenu_doc.this,Profile_doc.class);
        startActivity(intent);
    }
    public void Chat(View view) {
        Intent intent= new Intent(MainMenu_doc.this,Clients.class);
        startActivity(intent);
    }
    public void Info(View view) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);


        builder.setMessage(R.string.message2)
                .setTitle(R.string.info);



        AlertDialog dialog = builder.create();
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });

        builder.show();
    }

    public void Contacts(View view) {
        AlertDialog.Builder builder2 = new AlertDialog.Builder(this);

        builder2.setMessage(R.string.message3)
                .setTitle(R.string.dop);


        AlertDialog dialog = builder2.create();
        builder2.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });
        builder2.show();

    }

    public void makeCard(View view) {
            Intent intent= new Intent(MainMenu_doc.this,Card.class);
            startActivity(intent);

    }
}
