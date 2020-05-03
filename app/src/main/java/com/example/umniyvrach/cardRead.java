package com.example.umniyvrach;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class cardRead extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Context mContext;
    String card;
    FirebaseUser fuser;

    public cardRead(Context mContext, String card)  {
        this.mContext = mContext;
        this.card =card;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        fuser= FirebaseAuth.getInstance().getCurrentUser();

            View view = LayoutInflater.from(mContext).inflate(R.layout.chat_item_right, parent, false);
            return new cardRead.ViewHolder(view);

         //   View view = LayoutInflater.from(mContext).inflate(R.layout.chat_item_left, parent, false);
          //  return new MessageAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder( RecyclerView.ViewHolder holder, int position) {


        ViewHolder myHolder = (ViewHolder) holder;
        myHolder.show_message.setText(card);

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class  ViewHolder extends RecyclerView.ViewHolder{
        public TextView show_message;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            show_message=itemView.findViewById(R.id.show_message);
        }
    }

    @Override
    public int getItemViewType(int position) {
        fuser= FirebaseAuth.getInstance().getCurrentUser();
        return 0;
    }
}
