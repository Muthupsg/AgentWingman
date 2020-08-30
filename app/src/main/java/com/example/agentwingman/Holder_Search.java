package com.example.agentwingman;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Holder_Search extends AppCompatActivity {
DatabaseReference reff;
RecyclerView recyclerView;
ArrayList<Holderlistview> list;
HolderAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_holder__search);
        recyclerView = (RecyclerView) findViewById(R.id.recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<Holderlistview>();
        reff = FirebaseDatabase.getInstance().getReference().child("Holder");
        Intent intent = getIntent();
        final String key = intent.getStringExtra("message");

        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                {
                    Holderlistview li = dataSnapshot1.getValue(Holderlistview.class);
                    if(li.getPolicyNumber().toLowerCase().equals(key.toLowerCase()) || li.getName().toLowerCase().equals(key.toLowerCase())|| li.getPhone().toLowerCase().equals(key.toLowerCase())|| li.getEmail().toLowerCase().equals(key.toLowerCase()))
                    {
                        list.add(li);
                    }
                }
                adapter  = new HolderAdapter(Holder_Search.this, list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
