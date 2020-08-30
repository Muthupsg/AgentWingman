package com.example.agentwingman;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.Myviewholder> {
    public MyAdapter(Context context, ArrayList<ListView> profiles) {
        this.context = context;
        this.profiles = profiles;
    }

    Context context;
    ArrayList<ListView> profiles;
    @NonNull
    @Override
    public Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Myviewholder(LayoutInflater.from(context).inflate(R.layout.listview,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Myviewholder holder, int position) {
        holder.name.setText(profiles.get(position).getName());
        holder.mobile.setText(profiles.get(position).getPhone());
        holder.email.setText(profiles.get(position).getEmail());
        holder.policy.setText(profiles.get(position).getPolicyName());
    }

    @Override
    public int getItemCount() {
        return profiles.size();
    }

    class Myviewholder extends RecyclerView.ViewHolder{
        TextView name,mobile,email,policy,date;
        public Myviewholder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.head);
            mobile = (TextView) itemView.findViewById(R.id.no);
            email = (TextView) itemView.findViewById(R.id.e_mail);
            policy = (TextView) itemView.findViewById(R.id.pno);
        }
    }
}