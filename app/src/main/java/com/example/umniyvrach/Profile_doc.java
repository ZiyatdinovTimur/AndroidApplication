package com.example.umniyvrach;

import androidx.annotation.NonNull;
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

public class Profile_doc extends AppCompatActivity {
    FirebaseUser firebaseUser;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_doc);
        final  TextView text = (TextView)findViewById(R.id.textView8);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Specialists").child(firebaseUser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Doctors doctors = dataSnapshot.getValue(Doctors.class);
                TextView nam= (TextView)findViewById(R.id.textView3);
                nam.setText(doctors.getName());
                TextView surnam= (TextView)findViewById(R.id.textView5);
                surnam.setText(doctors.getSurname());
                TextView spec= (TextView)findViewById(R.id.textView);
                spec.setText(doctors.getSpec());
                TextView text = (TextView)findViewById(R.id.textView8);
                text.setText(doctors.getInfo());
                TextView eml= (TextView)findViewById(R.id.textView9);
                eml.setText(doctors.getEmail());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
//        TextView nam= (TextView)findViewById(R.id.textView3);
//        nam.setText(Reg_doc.name);
//        TextView surnam= (TextView)findViewById(R.id.textView5);
//        surnam.setText(Reg_doc.surname);
//        TextView ag= (TextView)findViewById(R.id.textView);
//        ag.setText(Reg_doc.spec);
//        TextView eml= (TextView)findViewById(R.id.textView9);
//        eml.setText(Reg_doc.email);
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
                reference= FirebaseDatabase.getInstance().getReference("Specialists").child(userid);
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
