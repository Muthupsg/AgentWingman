package com.example.agentwingman;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Main extends AppCompatActivity {
DatabaseReference reff;
RecyclerView recyclerView;
ArrayList<ListView> list;
MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        recyclerView =(RecyclerView) findViewById(R.id.recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<ListView>();
        Intent intent = getIntent();
        final String message =intent.getStringExtra("search");
        reff = FirebaseDatabase.getInstance().getReference().child("Client");
        reff.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    ListView li = dataSnapshot1.getValue(ListView.class);
                    if (li.getPolicyName().toLowerCase().equals(  message.toLowerCase())||li.getName().toLowerCase().equals( message.toLowerCase())||li.getEmail().toLowerCase().equals( message.toLowerCase())||li.getPhone().toLowerCase().equals( message.toLowerCase())){
                        list.add(li);
                }
                }
                adapter = new MyAdapter(Main.this, list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
