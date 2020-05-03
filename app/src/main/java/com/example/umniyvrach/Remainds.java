package com.example.umniyvrach;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class Remainds extends AppCompatActivity {

    FirebaseUser firebaseUser;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remainds);
        ActionBar actionBar =getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);

                 TextView text1 = (TextView)findViewById(R.id.show_message4);
                 TextView text2 = (TextView)findViewById(R.id.show_message2);
                 TextView text3 = (TextView)findViewById(R.id.show_message3);
                text2.setText(user.getDayRemainds());
                text1.setText(user.getMorningRemainds());
                text3.setText(user.getEveningRemainds());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu3, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.men3:
                showRemaindsUDialog();
                return true;
            case R.id.men4:
                showRemaindsDDialog();
                return true;
            case R.id.men5:
                showRemaindsVDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showRemaindsDDialog() {
        {
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
            LayoutInflater inflater = this.getLayoutInflater();
            final View dialogView = inflater.inflate(R.layout.dialog3, null);
            dialogBuilder.setView(dialogView);
            // dialogBuilder.setTitle("Введите дополнительную информацию о себе");
            final EditText edt = (EditText) dialogView.findViewById(R.id.add);
            final TextView text = (TextView)findViewById(R.id.show_message2);
            dialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    assert firebaseUser != null;
                    String userid=firebaseUser.getUid();
                    reference= FirebaseDatabase.getInstance().getReference("Users").child(userid);
                    Map<String,Object> Map= new HashMap<>();
                    String step="День: "+edt.getText().toString();
                    Map.put("dayRemainds",step);
                    text.setText("День: "+step);
                    reference.updateChildren(Map);
                }
            });
            dialogBuilder.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                }
            });
            AlertDialog b = dialogBuilder.create();
            b.show();
        }

    }



    private void showRemaindsVDialog() {
        {
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
            LayoutInflater inflater = this.getLayoutInflater();
            final View dialogView = inflater.inflate(R.layout.dialog3, null);
            dialogBuilder.setView(dialogView);
            // dialogBuilder.setTitle("Введите дополнительную информацию о себе");
            final EditText edt = (EditText) dialogView.findViewById(R.id.add);
            final TextView text = (TextView)findViewById(R.id.show_message3);
            dialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    assert firebaseUser != null;
                    String userid=firebaseUser.getUid();
                    reference= FirebaseDatabase.getInstance().getReference("Users").child(userid);
                    Map<String,Object> Map= new HashMap<>();
                    String step="Вечер: "+edt.getText().toString();
                    Map.put("eveningRemainds",step);
                    text.setText("Вечер: "+step);
                    reference.updateChildren(Map);
                }
            });
            dialogBuilder.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                }
            });
            AlertDialog b = dialogBuilder.create();
            b.show();
        }
    }

    private void showRemaindsUDialog() {
         AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
            LayoutInflater inflater = this.getLayoutInflater();
            final View dialogView = inflater.inflate(R.layout.dialog3, null);
            dialogBuilder.setView(dialogView);
            // dialogBuilder.setTitle("Введите дополнительную информацию о себе");
            final EditText edt = (EditText) dialogView.findViewById(R.id.add);
            final TextView text = (TextView)findViewById(R.id.show_message4);
            dialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    assert firebaseUser != null;
                    String userid=firebaseUser.getUid();
                    reference= FirebaseDatabase.getInstance().getReference("Users").child(userid);
                    Map<String,Object> Map= new HashMap<>();
                    String step="Утро: "+edt.getText().toString();
                    Map.put("morningRemainds",step);
                    text.setText("Утро: "+step);
                    reference.updateChildren(Map);
                }
            });
            dialogBuilder.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                }
            });
            AlertDialog b = dialogBuilder.create();
            b.show();
        }
//            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
//            LayoutInflater inflater = this.getLayoutInflater();
//            final View dialogView = inflater.inflate(R.layout.dialog3, null);
//            dialogBuilder.setView(dialogView);
//            // dialogBuilder.setTitle("Введите дополнительную информацию о себе");
//            final EditText edt = (EditText) dialogView.findViewById(R.id.add);
//            final TextView text = (TextView)findViewById(R.id.show_message1);
//            dialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialog, int whichButton) {
//                    assert firebaseUser != null;
//                    String userid=firebaseUser.getUid();
//                    reference= FirebaseDatabase.getInstance().getReference("Users").child(userid);
//                    Map<String,Object> Map= new HashMap<>();
//                    String step="Утро: "+edt.getText().toString();
//                    Map.put("moriningRemainds",step);
//                    text.setText("Утро: "+step);
//                    reference.updateChildren(Map);
//                }
//            });
//            dialogBuilder.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialog, int whichButton) {
//                }
//            });
//            AlertDialog b = dialogBuilder.create();
//            b.show();
//        }
//
    }



