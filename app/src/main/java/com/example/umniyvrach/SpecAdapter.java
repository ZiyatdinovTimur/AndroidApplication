package com.example.umniyvrach;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpecAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    static String imref;
    FirebaseStorage storage;
    StorageReference storageReference;
    private Context mContext;
    ImageView imageView;
    private List<Doctors> mUsers;
    public SpecAdapter(Context mContext, List<Doctors> mUsers) {
        this.mContext = mContext;
        this.mUsers = mUsers;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.user_item,parent,false);
        return new SpecAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( RecyclerView.ViewHolder holder, int position) {
     final Doctors doc=mUsers.get(position);
        ViewHolder myHolder = (ViewHolder) holder;
        myHolder.username.setText(doc.getName()+" "+doc.getSurname() +" - "+doc.getSpec());
        myHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext,Chat.class);
                intent.putExtra("userid",doc.getId());
                mContext.startActivity(intent);
            }
        });
        myHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(mContext);
                LayoutInflater inflater = LayoutInflater.from(mContext);
                final View dialogView = inflater.inflate(R.layout.dialog_signin2, null);
                dialogBuilder.setView(dialogView);
                 dialogBuilder.setTitle(doc.getName()+" "+doc.getSurname());
                 dialogBuilder.setMessage("Специальность: "+doc.getSpec()+'\n'+
                 "Email: "+doc.getEmail()+'\n'+'\n'+
                         "О себе: "+doc.getInfo()
                 );
                dialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                    }
                });
                dialogBuilder.setNegativeButton("Медицинский диплом", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        storage = FirebaseStorage.getInstance();
                        storageReference = storage.getReference();
                        String imageName=doc.getEmail()+".jpg";
                        StorageReference imageRef = storageReference.child("images").child(imageName);
                        imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                           imref= uri.toString();
                                Intent intent= new Intent(mContext,Diplom.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                mContext.startActivity(intent);
                            }
                        });
                    }
                });
                AlertDialog b = dialogBuilder.create();
                b.show();

                return true;
            }
        }

        );
          }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public class  ViewHolder extends RecyclerView.ViewHolder{
        public  TextView username;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            username=itemView.findViewById(R.id.username);
        }
    }
}
