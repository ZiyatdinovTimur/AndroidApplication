package com.example.umniyvrach;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddRecord extends AppCompatActivity {
    TextView show;
    FirebaseUser fuser;
    DatabaseReference reference;
    Intent intent;
    ImageButton btn_send2;
    EditText text_send2;
    MessageAdapter messageAdapter;
    List<Messgs> messgs;
    //RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);
        ActionBar actionBar =getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
//      getSupportActionBar().setTitle("");
//      getSupportActionBar().setDefaultDisplayHomeAsUpEnabled();
    //    recyclerView = findViewById(R.id.recycle_view2);
    //    recyclerView.setHasFixedSize(true);
        show=findViewById(R.id.show_message);
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
    //    recyclerView.setLayoutManager(linearLayoutManager);
        btn_send2=findViewById(R.id.btn_send2);
        text_send2=findViewById(R.id.text_send2);
        intent=getIntent();
        final String userid=intent.getStringExtra("userid");
        fuser= FirebaseAuth.getInstance().getCurrentUser();
        reference= FirebaseDatabase.getInstance().getReference("Users").child(userid);
        btn_send2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String msg = text_send2.getText().toString();

                if(!msg.equals("")){
                    reference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            User user=dataSnapshot.getValue(User.class);
                            String record="Запись:";
                            record+='\n';
                            record+=msg;
                            user.setCard(record);
                            show.setText(user.getCard());
                            reference= FirebaseDatabase.getInstance().getReference("Users").child(userid);
                            Map<String,Object> Map= new HashMap<>();
                            Map.put("card",user.getCard());
                            reference.updateChildren(Map);

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                else {
                    Toast.makeText(AddRecord.this,"Вы не можете записать пустую информацию!",Toast.LENGTH_SHORT).show();
                }
                text_send2.setText("");
            }
        });




        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user=dataSnapshot.getValue(User.class);
                setTitle(user.getName()+" "+user.getSurname());
                show.setText(user.getCard());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
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


}
