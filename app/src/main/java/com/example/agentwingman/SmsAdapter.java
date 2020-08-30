package com.example.agentwingman;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SmsAdapter extends RecyclerView.Adapter<SmsAdapter.Myviewholder> {
    Context context;
    public SmsAdapter(Context context, ArrayList<Holderlistview> profiles) {
        this.context = context;
        this.profiles = profiles;
    }

    ArrayList<Holderlistview> profiles;

    @NonNull
    @Override
    public Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Myviewholder(LayoutInflater.from(context).inflate(R.layout.sms_listview,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Myviewholder holder, int position) {
        holder.name.setText(profiles.get(position).getName());
        holder.mobile.setText(profiles.get(position).getPhone());
        holder.policy.setText(profiles.get(position).getPolicyNumber());
        holder.sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, Sending_Sms.class);

                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return profiles.size();
    }

    class Myviewholder extends RecyclerView.ViewHolder{
        TextView name,mobile,email,policy;
        Button sms;
        public Myviewholder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.head);
            mobile = (TextView) itemView.findViewById(R.id.no);
            policy = (TextView) itemView.findViewById(R.id.pno);
            sms = (Button) itemView.findViewById(R.id.sms);
        }
    }
}
