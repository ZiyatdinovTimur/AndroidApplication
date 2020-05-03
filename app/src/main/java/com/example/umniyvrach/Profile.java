package com.example.umniyvrach;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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

public class Profile extends AppCompatActivity {
    public static String st;

    FirebaseUser firebaseUser;
    DatabaseReference reference;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
//        ActionBar actionBar =getSupportActionBar();
//        actionBar.setHomeButtonEnabled(true);
//        actionBar.setDisplayHomeAsUpEnabled(true);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            User user = dataSnapshot.getValue(User.class);

                TextView nam= (TextView)findViewById(R.id.textView3);
                nam.setText(user.getName());
                TextView surnam= (TextView)findViewById(R.id.textView5);
                surnam.setText(user.getSurname());
                TextView ag= (TextView)findViewById(R.id.textView7);
                ag.setText(user.getAge());
                TextView mal= (TextView)findViewById(R.id.textView11);
                mal.setText(user.getMale());
                TextView text = (TextView)findViewById(R.id.textView8);
                text.setText(user.getInfo());
                TextView eml= (TextView)findViewById(R.id.textView9);
                eml.setText(user.getEmail());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        //String m;
//        TextView nam= (TextView)findViewById(R.id.textView3);
//        nam.setText(Reg.name);
//        TextView surnam= (TextView)findViewById(R.id.textView5);
//        surnam.setText(Reg.surname);
//        TextView ag= (TextView)findViewById(R.id.textView7);
//        ag.setText(Reg.age);
//        TextView mal= (TextView)findViewById(R.id.textView11);
////       if(Reg.male)
////           m="Мужской";
////       else
////           m="Женский";
//        mal.setText(m);
//        TextView eml= (TextView)findViewById(R.id.textView9);
//        eml.setText(Reg.email);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu2, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        showChangeLangDialog();
        return true;
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                this.finish();
//                return true;
       //     default:
              //  return super.onOptionsItemSelected(item);
        //}
    }

    public void showChangeLangDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_signin, null);
        dialogBuilder.setView(dialogView);
       // dialogBuilder.setTitle("Введите дополнительную информацию о себе");
        final EditText edt = (EditText) dialogView.findViewById(R.id.abme);
        final  TextView text = (TextView)findViewById(R.id.textView8);
        dialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                assert firebaseUser != null;
                String userid=firebaseUser.getUid();
                reference= FirebaseDatabase.getInstance().getReference("Users").child(userid);
                Map<String,Object> Map= new HashMap<>();
                 String step=edt.getText().toString();
                Map.put("info",step);
                text.setText(step);
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
