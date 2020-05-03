
package com.example.umniyvrach;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.umniyvrach.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {

    DatabaseReference reference;
    FirebaseAuth auth;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth=FirebaseAuth.getInstance();
    }

    public void SignIn(View view) {
        String login,password;
        final EditText getlogin = (EditText)findViewById(R.id.editText2) ;
        login=getlogin.getText().toString();

        final EditText getpassword = (EditText)findViewById(R.id.passs) ;
        password=getpassword.getText().toString();

        if(TextUtils.isEmpty(login)|TextUtils.isEmpty(password)){
            Toast.makeText(this, "Все поля должны быть заполнены!", Toast.LENGTH_SHORT).show();
        }
        else {
            auth.signInWithEmailAndPassword(login,password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                reference= FirebaseDatabase.getInstance().getReference();
                                reference.child("Users").child(auth.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        if(dataSnapshot.exists()){
                                            Intent intent = new Intent(MainActivity.this, com.example.umniyvrach.MainMenu.class);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                            startActivity(intent);
                                            finish();
                                        }
                                        else {
                                            Intent intent = new Intent(MainActivity.this, com.example.umniyvrach.MainMenu_doc.class);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                            startActivity(intent);
                                            finish();
                                        }
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });


                            }
                            else {
                                Toast.makeText(MainActivity.this, "Ошибка входа! ", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        }
//        if(login.length()==3){
//           Intent intent= new Intent(MainActivity.this, com.example.umniyvrach.MainMenu_doc.class);
//           startActivity(intent);
//
//       }
//      else if(login.equals("admin")){
//            Intent intent= new Intent(MainActivity.this, com.example.umniyvrach.MainMeny_admin.class);
//            startActivity(intent);
//
//        }
//        else {
//           Intent intent = new Intent(MainActivity.this, com.example.umniyvrach.MainMenu.class);
//           startActivity(intent);
//       }
    }

    public void SignUp(View view) {
        AlertDialog.Builder builder3 = new AlertDialog.Builder(this);
        builder3.setMessage(R.string.message1)
                .setTitle(R.string.regact);


        AlertDialog dialog = builder3.create();
        builder3.setPositiveButton(R.string. pacient, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int id) {
                Intent intent= new Intent(MainActivity.this, com.example.umniyvrach.Reg.class);
                startActivity(intent);

            }
        });
        builder3.setNegativeButton(R.string.vrach, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int id) {
                Intent intent= new Intent(MainActivity.this, com.example.umniyvrach.Reg_doc.class);
                startActivity(intent);
            }
        });

        builder3.show();


    }



}
