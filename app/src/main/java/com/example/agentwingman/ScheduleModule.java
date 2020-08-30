package com.example.agentwingman;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ScheduleModule extends AppCompatActivity {
DatabaseReference reff;
RecyclerView recyclerView;
ArrayList<ListView> list;
ScheduleAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_module);
        recyclerView =(RecyclerView) findViewById(R.id.recycle1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<ListView>();
        reff = FirebaseDatabase.getInstance().getReference().child("Client");
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
        Date date = new Date();
        final String dat = dateFormat.format(date);
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    ListView i = dataSnapshot1.getValue(ListView.class);
                    if(i.getDate().equals(dat)) {
                        list.add(i);
                    }
                }
                adapter = new ScheduleAdapter(ScheduleModule.this, list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
