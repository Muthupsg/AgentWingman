package com.example.agentwingman;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.Myviewholder> {
    DatabaseReference reff;
Context context;
        ArrayList<ListView> profile;

    public ScheduleAdapter(Context context, ArrayList<ListView> profile) {
        this.context = context;
        this.profile = profile;
    }

    @NonNull
    @Override
    public Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Myviewholder(LayoutInflater.from(context).inflate(R.layout.listview2, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Myviewholder holder, final int position) {
        holder.name.setText(profile.get(position).getName());
        holder.mobile.setText(profile.get(position).getPhone());
        holder.policy.setText(profile.get(position).getPolicyName());
        holder.met.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String met = profile.get(position).getName();
                reff = FirebaseDatabase.getInstance().getReference().child("Client").child(met);
                reff.removeValue();
                Toast.makeText(context, "Data Deleted", Toast.LENGTH_SHORT).show();

            }
        });
        holder.notmeet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = profile.get(position).getName();
                String mobile = profile.get(position).getPhone();
                String policy = profile.get(position).getPolicyName();
                String email = profile.get(position).getEmail();
                Intent i = new Intent(context, UpdateModule.class);
                i.putExtra("name", name);
                i.putExtra("mobile", mobile);
                i.putExtra("policy", policy);
                i.putExtra("email", email);
                context.startActivity(i);

            }
        });

        holder.mark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String     name = profile.get(position).getName();
                String policy = profile.get(position).getPolicyName();
                Intent i = new Intent(context, HolderUpload.class);
                i.putExtra("name", name);
                i.putExtra("policy", policy);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return profile.size();
    }

    class Myviewholder extends RecyclerView.ViewHolder{
        Button met, notmeet, mark;
        TextView name,mobile,email,policy;
        public Myviewholder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.head1);
            mobile = (TextView) itemView.findViewById(R.id.no1);
            email = (TextView) itemView.findViewById(R.id.e_mail1);
            policy = (TextView) itemView.findViewById(R.id.pno1);
            met = (Button) itemView.findViewById(R.id.met);
            notmeet = (Button) itemView.findViewById(R.id.notmet);
            mark = (Button) itemView.findViewById(R.id.mark);
        }
    }
}
