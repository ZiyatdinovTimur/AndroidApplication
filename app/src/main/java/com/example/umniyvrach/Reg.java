package com.example.umniyvrach;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.content.Intent;
import android.drm.DrmStore;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Reg extends AppCompatActivity {

    public static String name;
    public  static String surname;
    public static String age;
    public static String email;
    public static boolean male;
    public static String password;

    FirebaseAuth auth;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        ActionBar actionBar =getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        auth=FirebaseAuth.getInstance();
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void Sign(View view) {
        final EditText getname = (EditText)findViewById(R.id.editText5) ;
        final EditText getsurname = (EditText)findViewById(R.id.editText6) ;
        final EditText getage = (EditText)findViewById(R.id.editText8) ;
        final EditText getemail = (EditText)findViewById(R.id.editText) ;
        final EditText getpassword = (EditText)findViewById(R.id.editText7) ;
        final CheckBox getmale = (CheckBox)findViewById(R.id.checkBox);
        name=getname.getText().toString();
        surname=getsurname.getText().toString();
        age=getage.getText().toString();
        email=getemail.getText().toString();

        password=getpassword.getText().toString();
        male=getmale.isChecked();
        if(TextUtils.isEmpty(name)|TextUtils.isEmpty(surname)|TextUtils.isEmpty(age)|TextUtils.isEmpty(email)|
               TextUtils.isEmpty(password))
            Toast.makeText(this, "Пожалуйста, заполните все поля!", Toast.LENGTH_SHORT).show();

        else if(password.length()<6)
            Toast.makeText(this, "Пароль должен быть не короче 6 символов", Toast.LENGTH_SHORT).show();

        else
            register(name,surname,age,email,password,male);

//        Intent intent= new Intent(Reg.this,MainMenu.class);
//        startActivity(intent);
    }


    public void register( final String name, final String surname, final String age,final String email,
                           final String password  ,final boolean male){

        auth.createUserWithEmailAndPassword(email,password).
                addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser firebaseUser = auth.getCurrentUser();
                            assert firebaseUser != null;
                            String userid=firebaseUser.getUid();
                            reference= FirebaseDatabase.getInstance().getReference("Users").child(userid);

                            HashMap<String,String> hashMap= new HashMap<>();
                            hashMap.put("id",userid);
                            hashMap.put("name",name);
                            hashMap.put("surname",surname);
                            hashMap.put("age",age);
                            hashMap.put("email",email);
                            final String info= "Сюда вы можете добавить дополнительную информацию о себе";
                            hashMap.put("info",info);
                            final String card= " ";
                            final String mr= "Добавте сюда ваш прием лекарств на утро";
                            final String d= "Добавте сюда ваш прием лекарств на день ";
                            final String e= "Добавте сюда ваш прием лекарств на вечер";
                            hashMap.put("card",card);
                            hashMap.put("morningRemainds",mr);
                            hashMap.put("dayRemainds",d);
                            hashMap.put("eveningRemainds",e);
                            final String m;
                            if(male)
                                m="Мужской";
                            else
                                m="Женский";
                            hashMap.put("male",m);

                            reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Intent intent= new Intent(Reg.this,MainMenu.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            });
                        }
                        else {
                            Toast.makeText(Reg.this, "Вы не можете зарегистрироваться с данным логином или паролем",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });


    }
}
